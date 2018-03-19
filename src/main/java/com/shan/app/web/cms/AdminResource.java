package com.shan.app.web.cms;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shan.app.domain.Admin;
import com.shan.app.service.cms.AdminService;
import com.shan.app.service.cms.dto.AdminDTO;
import com.shan.app.web.errors.PasswordConfirmException;

@RestController("cmsAdminResource")
@RequestMapping("/cms")
public class AdminResource {
	
	public static final Logger logger = LoggerFactory.getLogger(AdminResource.class);
	
	@Resource(name="cmsAdminService")
	private AdminService adminService;
	
	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/admin")
	public ResponseEntity<Object> createAdmin(@RequestBody @Valid AdminDTO.Create adminDTO) {
		
		Admin newAdmin = adminService.createAdmin(adminDTO);
		logger.debug("newAdmin = " + newAdmin);
		
		return new ResponseEntity<>(modelMapper.map(newAdmin, AdminDTO.Admin.class), HttpStatus.CREATED);
	}
	
	@PutMapping("/admin/{id}")
	public ResponseEntity<Object> updateAdmin(@PathVariable Long id,
			@RequestBody @Valid AdminDTO.Update adminDTO) {
		
		if(!adminDTO.getPassword().equals(adminDTO.getPasswordConfirm())) {
			throw new PasswordConfirmException("Password verification is not the same");
		}
		
		Admin updatedAdmin = adminService.updateAdmin(id, adminDTO);
		logger.debug("updatedAdmin = " + updatedAdmin);
		
		return new ResponseEntity<>(modelMapper.map(updatedAdmin, AdminDTO.Admin.class), HttpStatus.OK);
	}
	
	@GetMapping("/admins")
	public ResponseEntity<Object> getAdmins(Pageable pageable) {

		Page<AdminDTO.Admin> admins = adminService.getAdmins(pageable);
		logger.debug("admins = " + admins);
		
		return new ResponseEntity<>(admins, HttpStatus.OK);
	}
	
	@GetMapping("/admin/{id}")
	public ResponseEntity<Object> getAdmin(@PathVariable Long id) {
		
		Admin admin = adminService.getAdmin(id);
		
		return new ResponseEntity<>(modelMapper.map(admin, AdminDTO.Admin.class), HttpStatus.OK);
	}
	
	@DeleteMapping("/admin/{id}")
	public ResponseEntity<Object> deleteAdmin(@PathVariable Long id) {
		
		adminService.deleteAdmin(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
