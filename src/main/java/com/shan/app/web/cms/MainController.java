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
import com.shan.app.service.cms.MainService;
import com.shan.app.service.cms.dto.MenuDTO;

@Controller("cmsMainController")
@RequestMapping("/cms")
public class MainController {
	
	private final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Resource(name="cmsMainService")
	private MainService mainService;

	@RequestMapping({"", "/main"})
	public String main() {
		return "cms/main/main";
	}
	
	@RequestMapping("/{menuId}/system")
	public ResponseEntity<Object> system(@PathVariable Long menuId) {
		Menu menu = mainService.getSystemRedirectMenu(menuId);
		logger.debug("## RedirectMenu = " + menu);
		return new ResponseEntity<>(modelMapper.map(menu, MenuDTO.Response.class), HttpStatus.OK);
	}
}
