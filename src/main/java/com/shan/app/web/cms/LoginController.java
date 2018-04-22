package com.shan.app.web.cms;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("cmsLoginController")
@RequestMapping("/cms")
public class LoginController {
	
	private final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping("/login")
	public String loginForm(HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		logger.debug("request.getHeader(\"Referer\") = " + referer);
		request.getSession().setAttribute("prevPage", referer);
		
		request.setAttribute("loginredirectname", "/cms/main");
		return "cms/login/login";
	}
	
	@RequestMapping("/login/fail")
	public String loginFail() {
		logger.debug("url = /login/fail");
		return "cms/login/login";
	}
	
}
