package com.shan.app.repository.cms;

import java.util.List;

import com.shan.app.domain.Menu;

public interface MenuRepositoryCustom {

	List<Menu> findDefaultHierarchicalMenu();
	
	List<Menu> findQueryDslHierarchicalMenu();
	
	List<Menu> findQueryDslHierarchicalMenuById(Long systemMenuId);
}
