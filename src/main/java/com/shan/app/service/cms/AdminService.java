package com.shan.app.service.cms;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shan.app.domain.Admin;
import com.shan.app.domain.Authority;
import com.shan.app.repository.cms.AdminRepository;
import com.shan.app.repository.cms.AuthorityRepository;
import com.shan.app.security.cms.SecurityAdminUser;
import com.shan.app.service.cms.dto.AdminDTO;
import com.shan.app.service.cms.dto.AdminDTO.Update;
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
		
		Set<String> authorities = adminDTO.getAuthorities();
		for(String auth : authorities) {
			Authority authority = authorityRepository.findOneByAuthority(auth);
			if(authority == null) {
				throw new EntityNotFoundException(Authority.class, "authority", auth);
			}
			admin.addAuthority(authority);
		}

		//시큐리티 세션 가져오기
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		SecurityAdminUser user = (SecurityAdminUser) authentication.getPrincipal();
		admin.setRegUserId(user.getUsername());
		
		return adminRepository.save(admin);
	}
	
	public Admin updateAdmin(Long id, Update adminDTO) {
		
		Admin admin = adminRepository.findOne(id);
		if(admin == null) {
			throw new EntityNotFoundException(Admin.class, "admin", String.valueOf(id));
		}
		
		admin.setName(adminDTO.getName());
		admin.setEmail(adminDTO.getEmail());
		admin.setHp(adminDTO.getHp());
		admin.setTel(adminDTO.getTel());
		admin.setState(adminDTO.getState());
		admin.setPassword(bCryptPasswordEncoder.encode(adminDTO.getPassword()));
		admin.setUpdateDate(new Date());
		
		Set<Authority> authoritySet = new HashSet<>();
		Set<String> authorities = adminDTO.getAuthorities();
		for(String auth : authorities) {
			Authority authority = authorityRepository.findOneByAuthority(auth);
			if(authority == null) {
				throw new EntityNotFoundException(Authority.class, "authority", auth);
			}
			authoritySet.add(authority);
		}
		admin.setAuthorities(authoritySet);
		
		return adminRepository.save(admin);
	}

	public List<Admin> getAdmins() {
		return adminRepository.findAll();
	}

	public Admin getAdmin(Long id) {
		return adminRepository.findOne(id);
	}

	public void deleteAdmin(Long id) {
		adminRepository.delete(id);
	}

}
