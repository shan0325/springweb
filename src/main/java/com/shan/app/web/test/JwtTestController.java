package com.shan.app.web.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jwttest")
public class JwtTestController {

	@RequestMapping("/main")
	public String jwttest() {
		return "jwttest main";
	}
}
