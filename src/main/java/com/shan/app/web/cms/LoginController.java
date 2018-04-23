package com.shan.app.web.cms;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("cmsLoginController")
@RequestMapping("/cms")
public class LoginController {
	
	private final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@GetMapping("/login")
	public String loginView(HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		logger.debug("request.getHeader(\"Referer\") = " + referer);
		request.getSession().setAttribute("prevPage", referer);
		
		logger.debug("userId = " + request.getAttribute("userId"));
		logger.debug("loginRedirect = " + request.getAttribute("loginredirectname"));
		logger.debug("securityexceptionmsg = " + request.getAttribute("securityexceptionmsg"));
		logger.debug("error = " + request.getParameter("error"));
		//request.setAttribute("loginredirectname", "/cms/main");
		return "cms/login/login";
	}
	
	@PostMapping("/login")
	public String login() {
		return "cms/login/login";
	}
	
	@RequestMapping("/loginprocess")
	public String loginProcess() {
		return "cms/main/login";
	}
	
	@RequestMapping("/loginfail")
	public String loginFail(HttpServletRequest request) {
		logger.debug("url = /login/fail");
		logger.debug("loginRedirect = " + request.getAttribute("loginredirectname"));
		return "cms/login/login";
	}
	
}
