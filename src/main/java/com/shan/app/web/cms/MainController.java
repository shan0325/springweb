package com.shan.app.web.cms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("cmsMainController")
@RequestMapping("/cms")
public class MainController {
	
	private final Logger logger = LoggerFactory.getLogger(MainController.class);

	@RequestMapping({"", "/main"})
	public String main() {
		return "cms/main/main";
	}
}
