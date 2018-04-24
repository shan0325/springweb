package com.shan.app.web.cms;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("cmsLoginController")
@RequestMapping("/cms")
public class LoginController {
	
	private final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping("/login")
	public String loginView(HttpServletRequest request) {
		logger.debug("error = " + request.getParameter("error"));
		request.setAttribute("loginredirectname", "/cms/main");
		
		return "cms/login/login";
	}
	
	@RequestMapping("/login/process")
	public String loginProcess() {
		return "redirect:/cms/login";
	}
	
}
