package com.shan.app.repository.cms.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Order;
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
		QMenu qMenu = QMenu.menu;
		
		JPQLQuery<Menu> query = from(qMenu);
		List<Menu> menus = query.fetch();
		
		JPQLQuery<Menu> query2 = from(qMenu);
		List<Menu> parMenus = query2.where(qMenu.depth.eq(0)).orderBy(qMenu.ord.asc(), qMenu.id.asc()).fetch();
		
		List<Menu> hirMenus = new ArrayList<Menu>();
		for(Menu parMenu : parMenus) {
			hirMenus.add(parMenu);
			
			Menu execMenu = parMenu;
			int lastOrd = 0;
			boolean ing = true;
			int stat = 1;
			while(ing) {
				Menu child = null;
				List<Menu> childs = new ArrayList<Menu>();
				
				if(stat == 2) {
					for(Menu menu : menus) {
						if(execMenu.getId() == menu.getParentId()) {
							childs.add(menu);
						}
					}
				} else {
					for(Menu menu : menus) {
						if(execMenu.getId() == menu.getParentId() && lastOrd < menu.getOrd()) {
							childs.add(menu);
						}
					}
				}
				logger.debug("childs = " + childs);
				
				lastOrd = 0;
				if(childs.size() > 0) {
					child = childs.get(0);
					
					
					execMenu = child;
					hirMenus.add(child);
				} else {
					//현재 메뉴의 부모 메뉴 찾기
					for(Menu menu : menus) {
						if(execMenu.getParentId() == menu.getId()) {
							lastOrd = execMenu.getOrd();
							execMenu = menu;
						}
					}
					
					continue;
				}
				
				int breakCnt = 0;
				if(execMenu.getParentId() == 0) {
					for(Menu menu : menus) {
						if(execMenu.getId() == menu.getParentId()) {
							if(lastOrd < menu.getOrd()) {
								breakCnt++;
							}
						}
					}
					
					if(breakCnt == 0) ing = false;
				}
			}
			
		}
		
		logger.debug("hirMenus = " + hirMenus);
		return hirMenus;
	}

}
