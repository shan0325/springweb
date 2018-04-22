package com.shan.app.web.cms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("cmsMainController")
@RequestMapping("/cms")
public class MainController {

	@RequestMapping("/main")
	public String main() {
		return "cms/main/main";
	}
}
