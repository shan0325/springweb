package com.shan.app.web.cms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cms/{menuId}")
public class MenuController {

	@RequestMapping("/menu")
	public String menuView() {
		return "cms/menu/menu";
	}
}
