package com.shan.app.service.cms;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.shan.app.domain.Menu;
import com.shan.app.repository.cms.MenuRepository;

@Service("cmsMainService")
@Transactional
public class MainService {
	
	@Resource(name="cmsMenuRepository")
	private MenuRepository menuRepository;

	public String getSystemRedirectUrl(Long systemMenuId) {
		
		List<Menu> menus = menuRepository.findQueryDslHierarchicalMenuById(systemMenuId);
		
		return null;
	}

	
}
