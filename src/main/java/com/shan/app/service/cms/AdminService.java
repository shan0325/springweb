package com.shan.app.service.cms;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shan.app.domain.Admin;
import com.shan.app.repository.cms.AdminRepository;
import com.shan.app.service.cms.dto.AdminDTO;
import com.shan.app.web.errors.AdminDuplicatedException;

@Service("cmsAdminService")
@Transactional
public class AdminService {
	
	@Resource(name="cmsAdminRepository")
	private AdminRepository adminRepository;
	
	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	public Admin createAdmin(AdminDTO.Create adminDTO) {
		
		Admin adminDetail = adminRepository.findByUserId(adminDTO.getUserId());
		if(adminDetail != null) {
			throw new AdminDuplicatedException("Already used userId");
		}
		
		Admin admin = new Admin();
		admin.setUserId(adminDTO.getUserId());
		admin.setEmail(adminDTO.getEmail());
		admin.setHp(adminDTO.getHp());
		admin.setTel(adminDTO.getTel());
		admin.setPassword(bCryptPasswordEncoder.encode(adminDTO.getPassword()));
		admin.setRegDate(new Date());
		
		return adminRepository.save(admin);
	}

}
