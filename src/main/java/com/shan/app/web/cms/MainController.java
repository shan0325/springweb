package com.shan.app.web.cms;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shan.app.service.cms.MainService;

@Controller("cmsMainController")
@RequestMapping("/cms")
public class MainController {
	
	private final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Resource(name="cmsMainService")
	private MainService mainService;

	@RequestMapping("/main")
	public String main() {
		return "cms/main/main";
	}
	
	@RequestMapping("/{menuId}/system")
	public String system(@PathVariable Long menuId) {
		String redirectUrl = mainService.getSystemRedirectUrl(menuId);
		return "redirect:" + redirectUrl;
	}
}
