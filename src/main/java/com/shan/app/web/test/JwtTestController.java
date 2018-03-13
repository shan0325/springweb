package com.shan.app.web.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jwttest")
public class JwtTestController {

	@RequestMapping("/main")
	public String jwttest() {
		return "/jwttest/main";
	}
}
