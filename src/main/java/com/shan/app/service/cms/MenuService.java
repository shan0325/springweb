package com.shan.app.service.cms;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.shan.app.domain.BoardManager;
import com.shan.app.domain.Menu;
import com.shan.app.repository.cms.BoardManagerRepository;
import com.shan.app.repository.cms.MenuRepository;
import com.shan.app.service.cms.dto.FileDTO;
import com.shan.app.service.cms.dto.MenuDTO;
import com.shan.app.service.util.UploadUtil;
import com.shan.app.web.errors.EntityNotFoundException;

@Service("cmsMenuService")
@Transactional
public class MenuService {
	
	private final Logger logger = LoggerFactory.getLogger(MenuService.class);
	
	@Resource(name = "cmsMenuRepository")
	private MenuRepository menuRepository;
	
	@Resource(name = "cmsBoardManagerRepository")
	private BoardManagerRepository boardManagerRepository;
	
	@Autowired
	private UploadUtil uploadUtil;
	
	@Value("${upload.image.menu.path}")
	private String UPLOAD_IMAGE_MENU_PATH;

	public Menu createMenu(HttpServletRequest request, MenuDTO.Create create) throws Exception {

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
		
		//이미지 파일이 존재하면 업로드
		FileDTO.Create imageFile = uploadUtil.uploadImage(request, create.getImage(), UPLOAD_IMAGE_MENU_PATH);
		logger.debug("imageFile = " + imageFile);
		if(imageFile != null) {
			menu.setImageMenuPath(imageFile.getSavePath() + "/" + imageFile.getNewFileName());
		}
		
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
		
		newMenu.setCmsUrl(replaceURLMenuIdFormat(newMenu.getCmsUrl(), newMenu.getId()));
		newMenu.setHomeUrl(replaceURLMenuIdFormat(newMenu.getHomeUrl(), newMenu.getId()));
		
		return menuRepository.save(newMenu);
	}
	
	/**
	 * url내 "{menuId}" 를 실제 menuId로 치환
	 * @param url
	 * @param id
	 * @return
	 */
	public String replaceURLMenuIdFormat(String url, Long id) {
		String newUrl = null;
		if(!StringUtils.isEmpty(url) && id != null) {
			newUrl = url.replace("{menuId}", String.valueOf(id));
		} 
		return newUrl;
	}

	public Menu updateMenu(HttpServletRequest request, Long id, MenuDTO.Update update) throws Exception {
		
		Menu menu = getMenu(id);
		menu.setName(update.getName());
		menu.setDescription(update.getDescription());
		menu.setUseYn(update.getUseYn());
		menu.setMenuGubun(update.getMenuGubun());
		menu.setMenuType(update.getMenuType());
		menu.setCmsUrl(replaceURLMenuIdFormat(update.getCmsUrl(), id));
		menu.setCmsUrlTarget(update.getCmsUrlTarget());
		menu.setHomeUrl(replaceURLMenuIdFormat(update.getHomeUrl(), id));
		menu.setHomeUrlTarget(update.getHomeUrlTarget());
		menu.setOrd(update.getOrd());
		menu.setUpdateDate(new Date());
		
		//이미지 파일이 존재하면 업로드
		FileDTO.Create imageFile = uploadUtil.uploadImage(request, update.getImage(), UPLOAD_IMAGE_MENU_PATH);
		logger.debug("imageFile = " + imageFile);
		if(imageFile != null) {
			menu.setImageMenuPath(imageFile.getSavePath() + "/" + imageFile.getNewFileName());
		}
		
		//메뉴타입이 게시판이면 boardManager 구해오기
		String menuType = update.getMenuType();
		if("BOARD".equals(menuType)) {
			BoardManager boardManager = boardManagerRepository.findOne(update.getBoardManagerId());
			if(boardManager == null) {
				throw new EntityNotFoundException(BoardManager.class, "boardManager", String.valueOf(update.getBoardManagerId()));
			}
			menu.setBoardManager(boardManager);
		}
				
		return menuRepository.save(menu);
	}

	public List<Menu> getMenus() {
		
		//return menuRepository.findAll();
		//return menuRepository.findDefaultHierarchicalMenu();
		return menuRepository.findQueryDslHierarchicalMenu();
	}

	public Menu getMenu(Long id) {
		Menu menu = menuRepository.findOne(id);
		if(menu == null) {
			throw new EntityNotFoundException(Menu.class, "id", String.valueOf(id));
		}
		return menu;
	}

	public void deleteMenu(Long id) {
		
		menuRepository.delete(getMenu(id));
	}
}
