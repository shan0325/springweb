package com.shan.app.service.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shan.app.domain.Admin;
import com.shan.app.repository.admin.AdminRepository;
import com.shan.app.web.errors.AdminDuplicatedException;

@Service("adminAdminService")
@Transactional
public class AdminService {
	
	@Resource(name="adminAdminRepository")
	private AdminRepository adminRepository;

	public Admin createAdmin(Admin admin) {
		
		Admin adminDetail = adminRepository.findByUserId(admin.getUserId());
		
		if(adminDetail == null) {
			throw new AdminDuplicatedException();
		}
		
		return adminRepository.save(admin);
	}

}
