package com.shan.app.repository.cms.impl;

import java.util.ArrayList;
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
	public List<Menu> findDefaultHierarchicalMenu() {
		QMenu qMenu = QMenu.menu;
		
		JPQLQuery<Menu> query = from(qMenu);
		List<Menu> menus = query.fetch();
		
		JPQLQuery<Menu> query2 = from(qMenu);
		List<Menu> parMenus = query2.where(qMenu.depth.eq(0)).orderBy(qMenu.ord.asc(), qMenu.id.asc()).fetch();
		
		List<Menu> hirMenus = new ArrayList<Menu>();
		for(Menu parMenu : parMenus) {
			hirMenus.add(parMenu);
			
			Menu execMenu = parMenu;
			int lastOrd = Integer.MIN_VALUE;
			long lastMenu = 0;
			boolean ing = true;
			int stat = 1; //메뉴 순서가 중복이 있으면 상태를 2로 변경
			while(ing) {
				if(execMenu == null) break;
				logger.debug("execMenu = " + execMenu.getId());
				
				Menu child = null;
				List<Menu> childs = new ArrayList<Menu>();
				
				if(stat == 2) {
					for(Menu menu : menus) {
						if(execMenu.getId() == menu.getParentId() && lastOrd <= menu.getOrd() && lastMenu < menu.getId()) {
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
				
				StringBuffer sb = new StringBuffer();
				childs.forEach(entity -> {
					sb.append("{" + entity.getId() + ", " + entity.getName() + "} ");
				});
				logger.debug("childs = " + sb.toString());
				
				//execMenu가 최상위 메뉴이고 더이상 서브메뉴가 없을 시 빠져나감
				if(execMenu.getId() == 1 && childs.size() < 1) break;
				
				lastOrd = Integer.MIN_VALUE;
				lastMenu = 0;
				if(childs.size() > 0) {
					
					int tempOrd = 0;
					int index = 0;
					for(Menu ch : childs) {
						if(index == 0) {
							tempOrd = ch.getOrd();
							child = ch;
						} else {
							if(tempOrd > ch.getOrd()) {
								child = ch;
							}
						}
						index++;
					}
					
					int ordCnt = 0;
					for(Menu ch : childs) {
						if(child.getOrd() == ch.getOrd()) {
							ordCnt++;
						}
					}
										
					if(ordCnt > 1) {
						stat = 2;
					} else {
						stat = 1;
					}
					logger.debug("stat = " + stat);
					
					hirMenus.add(child);
					execMenu = child;
					
					StringBuffer sb2 = new StringBuffer();
					hirMenus.forEach(entity -> {
						sb2.append("{" + entity.getId() + ", " + entity.getName() + "} ");
					});
					logger.debug("hirMenus = " + sb2.toString());
				} else {
					//현재 메뉴의 부모 메뉴 찾기
					for(Menu menu : menus) {
						if(execMenu.getParentId() == menu.getId()) {
							lastOrd = execMenu.getOrd();
							lastMenu = execMenu.getId();
							execMenu = menu;
							logger.debug("lastOrd = " + lastOrd + ", lastMenu = " + lastMenu + ", execMenu = " + execMenu.getId());
						}
					}
					
					int ordCnt = 0;
					for(Menu menu : menus) {
						if(execMenu.getId() == menu.getParentId() && lastMenu <= menu.getId() && lastOrd == menu.getOrd()) {
							ordCnt++;
						}
					}
					
					if(ordCnt > 1) {
						stat = 2;
					} else {
						stat = 1;
					}
					logger.debug("stat = " + stat);
				}
				
				logger.debug("=======================================");
			}
		}
		
		logger.debug("hirMenus = " + hirMenus);
		return hirMenus;
	}

	@Override
	public List<Menu> findQueryDslHierarchicalMenu() {
		return findQueryDslHierarchicalMenuById(null);
	}

	@Override
	public List<Menu> findQueryDslHierarchicalMenuById(Long systemMenuId) {

		QMenu qMenu = QMenu.menu;
		List<Menu> parMenus = new ArrayList<Menu>();
		
		if(systemMenuId == null) {
			parMenus = from(qMenu)
					.where(qMenu.depth.eq(0))
					.orderBy(qMenu.ord.asc(), qMenu.id.asc())
					.fetch();
		} else {
			parMenus = from(qMenu)
					.where(qMenu.id.eq(systemMenuId))
					.orderBy(qMenu.ord.asc(), qMenu.id.asc())
					.fetch();
		}
		
		List<Menu> hirMenus = new ArrayList<Menu>();
		for(Menu parMenu : parMenus) {
			hirMenus.add(parMenu);
			
			Menu execMenu = parMenu;
			int lastOrd = Integer.MIN_VALUE;
			long lastMenu = 0;
			boolean ing = true;
			int stat = 1; //메뉴 순서가 중복이 있으면 상태를 2로 변경
			while(ing) {
				if(execMenu == null) break;
				logger.debug("execMenu = " + execMenu.getId());
				
				Menu child = null;
				List<Menu> childs = new ArrayList<Menu>();
				
				if(stat == 2) {
					childs = from(qMenu)
								.where(qMenu.parentId.eq(execMenu.getId())
										.and(qMenu.ord.goe(lastOrd))
										.and(qMenu.id.gt(lastMenu)))
								.fetch();
										
				} else {
					childs = from(qMenu)
								.where(qMenu.parentId.eq(execMenu.getId())
										.and(qMenu.ord.gt(lastOrd)))
								.fetch();
				}
				
				StringBuffer sb = new StringBuffer();
				childs.forEach(entity -> {
					sb.append("{" + entity.getId() + ", " + entity.getName() + "} ");
				});
				logger.debug("childs = " + sb.toString());
				
				//execMenu가 최상위 메뉴이고 더이상 서브메뉴가 없을 시 빠져나감
				if(execMenu.getId() == 1 && childs.size() < 1) break;
				
				lastOrd = Integer.MIN_VALUE;
				lastMenu = 0;
				if(childs.size() > 0) {
					
					int tempOrd = 0;
					int index = 0;
					for(Menu ch : childs) {
						if(index == 0) {
							tempOrd = ch.getOrd();
							child = ch;
						} else {
							if(tempOrd > ch.getOrd()) {
								child = ch;
							}
						}
						index++;
					}
					
					int ordCnt = 0;
					for(Menu ch : childs) {
						if(child.getOrd() == ch.getOrd()) {
							ordCnt++;
						}
					}
										
					if(ordCnt > 1) {
						stat = 2;
					} else {
						stat = 1;
					}
					logger.debug("stat = " + stat);
					
					hirMenus.add(child);
					execMenu = child;
					
					StringBuffer sb2 = new StringBuffer();
					hirMenus.forEach(entity -> {
						sb2.append("{" + entity.getId() + ", " + entity.getName() + "} ");
					});
					logger.debug("hirMenus = " + sb2.toString());
				} else {
					lastOrd = execMenu.getOrd();
					lastMenu = execMenu.getId();
					
					//현재 메뉴의 부모 메뉴 찾기
					execMenu = from(qMenu)
								.where(qMenu.id.eq(execMenu.getParentId()))
								.fetchOne();
					logger.debug("lastOrd = " + lastOrd + ", lastMenu = " + lastMenu + ", execMenu = " + execMenu.getId());
					
					long ordCnt = from(qMenu)
									.where(qMenu.parentId.eq(execMenu.getId())
											.and(qMenu.id.goe(lastMenu))
											.and(qMenu.ord.eq(lastOrd)))
									.fetchCount();
					
					if(ordCnt > 1) {
						stat = 2;
					} else {
						stat = 1;
					}
					logger.debug("stat = " + stat);
				}
				
				logger.debug("=======================================");
			}
		}
		
		logger.debug("hirMenus = " + hirMenus);
		return hirMenus;
	}

}
