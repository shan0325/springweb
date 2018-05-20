package com.shan.app.service.cms;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.shan.app.domain.Authority;
import com.shan.app.domain.Menu;
import com.shan.app.repository.cms.AuthorityRepository;
import com.shan.app.repository.cms.MenuRepository;
import com.shan.app.security.cms.SecurityAdminUser;
import com.shan.app.service.util.SecurityUtil;
import com.shan.app.web.errors.AuthorityNotExistException;
import com.shan.app.web.errors.MenuNotExistException;

@Service("cmsMainService")
@Transactional
public class MainService {
	
	private final Logger logger = LoggerFactory.getLogger(MainService.class);
	
	@Resource(name="cmsMenuRepository")
	private MenuRepository menuRepository;
	
	@Resource(name="cmsAuthorityRepository")
	private AuthorityRepository authorityRepository;
	

	public String getSystemRedirectUrl(Long systemMenuId) {
		List<Menu> menus = menuRepository.findQueryDslHierarchicalMenuById(systemMenuId);
		
		String authorityName = "";
		User user = SecurityUtil.getUser();
		Iterator<GrantedAuthority> iterator = user.getAuthorities().iterator();
		while(iterator.hasNext()) {
			GrantedAuthority grantedAuthority = iterator.next();
			authorityName = grantedAuthority.getAuthority().replace(SecurityAdminUser.ROLE_PREFIX, "");
		}
		
		Authority authority = authorityRepository.findOneByAuthority(authorityName);
		if(authority == null) {
			throw new AuthorityNotExistException("권한이 존재하지 않습니다.");
		} else {
			List<Menu> existMenus = authority.getMenus();
			
			Menu redirectMenu = null;
			for(Menu menu : menus) {
				boolean next = true;
				Long menuId = menu.getId();
				
				if("LIST".equals(menu.getMenuType())) continue;
				
				for(Menu emenu : existMenus) {
					Long emenuId = emenu.getId();
					if(menuId == emenuId) {
						next = false;
						break;
					}
				}
				
				if(!next) {
					redirectMenu = menu;
					break;
				}
			}
			
			if(redirectMenu == null) {
				throw new MenuNotExistException("이동할 메뉴가 존재하지 않습니다.");
			}
			
			logger.debug("cmsUrl = " + redirectMenu.getCmsUrl());
			return redirectMenu.getCmsUrl();
		}
	}

	
}
