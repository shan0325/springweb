package com.shan.app.web.cms;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shan.app.domain.Menu;
import com.shan.app.service.cms.MenuService;
import com.shan.app.service.cms.dto.MenuDTO;

@RestController("cmsMenuResource")
@RequestMapping("/cms")
public class MenuResource {
	
	public static final Logger logger = LoggerFactory.getLogger(MenuResource.class);
	
	@Resource(name = "cmsMenuService")
	private MenuService menuService;
	
	@PostMapping("/menu")
	public ResponseEntity<Object> createMenu(@RequestBody @Valid MenuDTO.Create create) {
		
		Menu newMenu = menuService.createMenu(create);
		
		return null;
	}

}
