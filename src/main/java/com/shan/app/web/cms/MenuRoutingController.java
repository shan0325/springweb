package com.shan.app.web.cms;

import javax.annotation.Resource;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shan.app.domain.Menu;
import com.shan.app.service.cms.MenuRoutingService;
import com.shan.app.service.cms.dto.MenuDTO;

@Controller
@RequestMapping("/cms")
public class MenuRoutingController {
	
	private final Logger logger = LoggerFactory.getLogger(MenuRoutingController.class);
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Resource(name="cmsMenuRoutingService")
	private MenuRoutingService menuRoutingService;
	

	/**
	 * 메뉴 라우팅
	 * @param menuId
	 * @return
	 */
	@RequestMapping("/menu/routing/{menuId}")
	public ResponseEntity<Object> menuRouting(@PathVariable Long menuId) {
		Menu menu = menuRoutingService.getMenuRouting(menuId);
		logger.debug("## RedirectMenu = " + menu);
		
		return new ResponseEntity<>(modelMapper.map(menu, MenuDTO.Response.class), HttpStatus.OK);
	}
	
}
