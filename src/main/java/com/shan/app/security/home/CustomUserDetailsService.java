package com.shan.app.security.home;

import java.util.Optional;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shan.app.domain.Admin;
import com.shan.app.repository.cms.AdminRepository;
import com.shan.app.security.cms.AdminNotActivatedException;
import com.shan.app.security.cms.SecurityAdminUser;
import com.shan.app.web.errors.EntityNotFoundException;

@Service("homeCustomUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	private final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Resource(name="cmsAdminRepository")
	private AdminRepository adminRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		logger.debug("Authenticating {}", userId);
		
		Optional<Admin> adminFromDatabase = adminRepository.findOneWithAuthoritiesByUserId(userId);
		return adminFromDatabase.map(admin -> {
					if(!"Y".equals(admin.getState())) {
						throw new AdminNotActivatedException("Admin " + userId + " was not activated");
					}
					return new SecurityAdminUser(admin);
				}).orElseThrow(() -> new EntityNotFoundException(Admin.class, "userId", userId));
	}

}
