package com.shan.app.security.admin;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shan.app.repository.admin.AdminRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		return Optional.ofNullable(adminRepository.findByUserId(userId))
						.filter(admin -> admin != null)
						.map(admin -> new SecurityAdminUser(admin))
						.get();
	}

}
