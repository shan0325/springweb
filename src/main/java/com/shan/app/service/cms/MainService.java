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
import com.shan.app.service.util.SecurityUtil;

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
			authorityName = grantedAuthority.getAuthority();
		}
		
		if(StringUtils.isEmpty(authorityName)) {
			return "";
		} else {
			Authority authority = authorityRepository.findOneByAuthority(authorityName);
			
			for(Menu menu : menus) {
				
			}
		}
		
		return null;
	}

	
}
