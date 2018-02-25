package com.shan.app.security.admin;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		return null;
	}

}
