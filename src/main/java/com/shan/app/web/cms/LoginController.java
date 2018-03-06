package com.shan.app.web.cms;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shan.app.web.errors.GlobalExceptionHandler;

@Controller
@RequestMapping("/cms")
public class LoginController {
	
	@GetMapping("/login")
	public String loginForm(HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		request.getSession().setAttribute("prevPage", referer);
		return "login";
	}
	
	@GetMapping("/signup")
	public String signUp() {
		return "signUp";
	}
	
	@GetMapping("/main")
	public String main() {
		return "main";
	}
}
