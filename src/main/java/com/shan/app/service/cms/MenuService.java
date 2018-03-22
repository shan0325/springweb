package com.shan.app.service.cms;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shan.app.domain.BoardManager;
import com.shan.app.domain.Menu;
import com.shan.app.repository.cms.BoardManagerRepository;
import com.shan.app.repository.cms.MenuRepository;
import com.shan.app.service.cms.dto.MenuDTO;
import com.shan.app.web.errors.EntityNotFoundException;

@Service("cmsMenuService")
public class MenuService {
	
	@Resource(name = "cmsMenuRepository")
	private MenuRepository menuRepository;
	
	@Resource(name = "cmsBoardManagerRepository")
	private BoardManagerRepository boardManagerRepository;

	public Menu createMenu(MenuDTO.Create create) {

		Menu menu = new Menu();
		menu.setName(create.getName());
		menu.setDescription(create.getDescription());
		menu.setUseYn(create.getUseYn());
		menu.setMenuType(create.getMenuType());
		menu.setUrl(create.getUrl());
		menu.setUrlTarget(create.getUrlTarget());
		menu.setOrd(create.getOrd());
		menu.setRegDate(new Date());
		
		//메뉴타입이 게시판이면 boardManager 구해오기
		String menuType = create.getMenuType();
		if("BOARD".equals(menuType)) {
			BoardManager boardManager = boardManagerRepository.findOne(create.getBoardManagerId());
			if(boardManager == null) {
				throw new EntityNotFoundException(BoardManager.class, "boardManager", String.valueOf(create.getBoardManagerId()));
			}
			menu.setBoardManager(boardManager);
		}
		
		//부모메뉴가 없으면 최상위메뉴로 있으면 하위메뉴로 등록
		Menu ParentMenu = menuRepository.findOne(create.getParentId());
		if(ParentMenu == null) {
			//최상위메뉴 등록
			menu.setParentId(0L);
			menu.setTopId(0L);
			menu.setDepth(0);
			
			Menu newMenu = menuRepository.save(menu);
			newMenu.setTopId(newMenu.getId());
			return menuRepository.save(newMenu);
		} 
		else {
			//하위메뉴 등록
			menu.setParentId(ParentMenu.getId());
			menu.setTopId(ParentMenu.getTopId());
			menu.setDepth(ParentMenu.getDepth() + 1);
			
			return menuRepository.save(menu);
		}
	}

}
