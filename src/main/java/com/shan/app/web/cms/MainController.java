package com.shan.app.web.cms;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shan.app.service.cms.MainService;

@Controller("cmsMainController")
@RequestMapping("/cms")
public class MainController {
	
	private final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Resource(name="cmsMainService")
	private MainService mainService;
	
	@Value("${system.menu.id}")
	private String SYSTEM_MENU_ID;

	@RequestMapping("/main")
	public String main() {
		return "cms/main/main";
	}
	
	@RequestMapping("/system")
	public String system() {
		if(!StringUtils.isNumeric(SYSTEM_MENU_ID)) {
			throw new NumberFormatException(SYSTEM_MENU_ID + " is not Numeric");
		} else {
			String redirectUrl = mainService.getSystemRedirectUrl(Long.parseLong(SYSTEM_MENU_ID));
			
			return redirectUrl;
		}
	}
}
