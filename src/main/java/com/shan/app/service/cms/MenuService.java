package com.shan.app.service.cms;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shan.app.domain.Menu;
import com.shan.app.repository.cms.MenuRepository;
import com.shan.app.service.cms.dto.MenuDTO.Create;

@Service("cmsMenuService")
public class MenuService {
	
	@Resource(name = "cmsMenuRepository")
	private MenuRepository menuRepository;

	public Menu createMenu(Create create) {

		Menu newMenu = new Menu();
		newMenu.setName(create.getName());
		newMenu.setDescription(create.getDescription());
		newMenu.setUseYn(create.getUseYn());
		newMenu.setMenuType(create.getMenuType());
		newMenu.setUrl(create.getUrl());
		newMenu.setUrlTarget(create.getUrlTarget());
		newMenu.setOrd(create.getOrd());
		
		//부모메뉴가 없으면 최상위메뉴 있으면 하위메뉴로 등록
		if(menuRepository.findOne(create.getParentId()) == null) {
			
		} else {
			
		}
		
		return menuRepository.save(newMenu);
	}

}
