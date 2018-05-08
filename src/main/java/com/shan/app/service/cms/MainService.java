package com.shan.app.service.cms;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.shan.app.domain.Menu;
import com.shan.app.repository.cms.MenuRepository;
import com.shan.app.service.util.SecurityUtil;

@Service("cmsMainService")
@Transactional
public class MainService {
	
	private final Logger logger = LoggerFactory.getLogger(MainService.class);
	
	@Resource(name="cmsMenuRepository")
	private MenuRepository menuRepository;
	

	public String getSystemRedirectUrl(Long systemMenuId) {
		
		String authority = "";
		List<Menu> menus = menuRepository.findQueryDslHierarchicalMenuById(systemMenuId);
		
		User user = SecurityUtil.getUser();
		Iterator iterator = user.getAuthorities().iterator();
		while(iterator.hasNext()) {
			GrantedAuthority grantedAuthority = (GrantedAuthority) iterator.next();
			authority = grantedAuthority.getAuthority();
		}
		
		
		
		return null;
	}

	
}
