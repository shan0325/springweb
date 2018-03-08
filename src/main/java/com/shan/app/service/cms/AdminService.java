package com.shan.app.service.cms;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shan.app.domain.Admin;
import com.shan.app.domain.Authority;
import com.shan.app.repository.cms.AdminRepository;
import com.shan.app.repository.cms.AuthorityRepository;
import com.shan.app.service.cms.dto.AdminDTO;
import com.shan.app.web.errors.AdminDuplicatedException;
import com.shan.app.web.errors.EntityNotFoundException;

@Service("cmsAdminService")
@Transactional
public class AdminService {
	
	@Resource(name="cmsAdminRepository")
	private AdminRepository adminRepository;
	
	@Resource(name="cmsAuthorityRepository")
	private AuthorityRepository authorityRepository;
	
	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	public Admin createAdmin(AdminDTO.Create adminDTO) {
		
		Admin adminDetail = adminRepository.findOneByUserId(adminDTO.getUserId());
		if(adminDetail != null) {
			throw new AdminDuplicatedException("Already used userId");
		}
		
		Admin admin = new Admin();
		admin.setUserId(adminDTO.getUserId());
		admin.setName(adminDTO.getName());
		admin.setEmail(adminDTO.getEmail());
		admin.setHp(adminDTO.getHp());
		admin.setTel(adminDTO.getTel());
		admin.setPassword(bCryptPasswordEncoder.encode(adminDTO.getPassword()));
		admin.setRegDate(new Date());
		
		Set<Authority> set = new HashSet<>();
		Set<String> authorities = adminDTO.getAuthorities();
		for(String auth : authorities) {
			Authority authority = authorityRepository.findOneByAuthority(auth);
			if(authority == null) {
				throw new EntityNotFoundException(Authority.class, "authority", auth);
			}
			set.add(authority);
		}
		admin.setAuthorities(set);
		
		return adminRepository.save(admin);
	}

}
