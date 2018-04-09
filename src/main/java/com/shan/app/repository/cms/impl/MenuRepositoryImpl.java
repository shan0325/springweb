package com.shan.app.repository.cms.impl;

import java.util.ArrayList;
import java.util.List;

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
		Menu execMenu = new Menu();
		boolean ing = true;
		for(Menu parMenu : parMenus) {
			hirMenus.add(parMenu);
			
			execMenu = parMenu;
			int curOrd = 0;
			int tempOrd = 0;
			ing = true;
			while(ing) {
				Menu child = null;
				int index = 0;
				for(Menu menu : menus) {
					if(execMenu.getId() == menu.getParentId() &&  menu.getOrd() > curOrd) {
						if (index == 0) {
							tempOrd = menu.getOrd();
							child = menu;
						}
						
						if(menu.getOrd() < tempOrd) {
							child = menu;
						}
						index++;
					}
				}
				logger.debug("child = " + child);
				curOrd = 0;
				tempOrd = 0;
				
				if(child != null) {
					hirMenus.add(child);
					execMenu = child;
					continue;
				} else {
					for(Menu menu : menus) {
						if(execMenu.getParentId() == menu.getId()) {
							curOrd = execMenu.getOrd();
							execMenu = menu;
						}
					}
				}
				
				int breakCnt = 0;
				if(execMenu.getParentId() == 0) {
					for(Menu menu : menus) {
						if(execMenu.getId() == menu.getParentId()) {
							if(curOrd < menu.getOrd()) {
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
