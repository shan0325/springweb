package com.shan.app.web.cms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cms/{menuId}")
public class SystemController {

	@RequestMapping("/system")
	public String system() {
		return "";
	}
}
