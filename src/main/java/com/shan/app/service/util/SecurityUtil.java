package com.shan.app.service.util;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class SecurityUtil {
	
	public static Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	public static User getUser() {
		Authentication authentication = getAuthentication();
		User user = (User) authentication.getPrincipal();
		return user;
	}
	
	public static Collection<? extends GrantedAuthority> getAuthoritys() {
		Authentication authentication = getAuthentication();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		return authorities;
	}

}
