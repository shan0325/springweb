package com.shan.app.service.cms;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
		menu.setMenuGubun(create.getMenuGubun());
		menu.setMenuType(create.getMenuType());
		menu.setCmsUrl(create.getCmsUrl());
		menu.setCmsUrlTarget(create.getCmsUrlTarget());
		menu.setHomeUrl(create.getHomeUrl());
		menu.setHomeUrlTarget(create.getHomeUrlTarget());
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
		Menu newMenu;
		if(create.getParentId() == null) {
			//최상위메뉴 등록
			menu.setParentId(0L);
			menu.setTopId(0L);
			menu.setDepth(0);
			
			newMenu = menuRepository.save(menu);
			newMenu.setTopId(newMenu.getId());
		} else {
			//하위메뉴 등록
			Menu ParentMenu = menuRepository.findOne(create.getParentId());
			menu.setParentId(ParentMenu.getId());
			menu.setTopId(ParentMenu.getTopId());
			menu.setDepth(ParentMenu.getDepth() + 1);
			
			newMenu = menuRepository.save(menu);
		}
		
		if(!StringUtils.isEmpty(menu.getCmsUrl())) {
			newMenu.setCmsUrl(menu.getCmsUrl().replace("{menuId}", String.valueOf(newMenu.getId())));
		}
		if(!StringUtils.isEmpty(menu.getHomeUrl())) {
			newMenu.setHomeUrl(menu.getHomeUrl().replace("{menuId}", String.valueOf(newMenu.getId())));
		}
		
		newMenu = menuRepository.save(newMenu);
		
		//이미지 파일이 존재하면 업로드
		String path = "/upload/menuImage";
		MultipartFile file = create.getImage();
		if(!file.isEmpty()) {
			File destPath = new File(path);
			if(!destPath.exists()) {
				destPath.mkdirs();
			}
			
			File newFile = new File(path + File.separator + UUID.randomUUID() + ".txt");
			try {
				file.transferTo(newFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return newMenu;
		
	}

}
