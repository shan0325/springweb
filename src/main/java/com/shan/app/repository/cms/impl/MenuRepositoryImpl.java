package com.shan.app.repository.cms.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.JPQLQuery;
import com.shan.app.domain.Menu;
import com.shan.app.domain.QMenu;
import com.shan.app.repository.cms.MenuRepositoryCustom;

@Repository("cmsMenuRepositoryImpl")
public class MenuRepositoryImpl extends QueryDslRepositorySupport implements MenuRepositoryCustom {

	private final Logger logger = LoggerFactory.getLogger(MenuRepositoryImpl.class);
	
	public MenuRepositoryImpl() {
		super(Menu.class);
	}
	
	@Override
	public List<Menu> findAllByQueryDsl() {
		QMenu menu = QMenu.menu;
		
		JPQLQuery<Menu> query = from(menu);
		return query.fetch();
	}

}
