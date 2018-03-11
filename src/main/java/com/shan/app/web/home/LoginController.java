package com.shan.app.web.home;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("homeLoginController")
@RequestMapping("/home")
public class LoginController {
	
	@GetMapping("/login")
	public String loginForm(HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		request.getSession().setAttribute("prevPage", referer);
		return "home/login";
	}
	
	@GetMapping("/main")
	public String main() {
		return "home/main";
	}
}
