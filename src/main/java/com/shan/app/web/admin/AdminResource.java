package com.shan.app.web.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shan.app.domain.Admin;
import com.shan.app.service.admin.AdminService;
import com.shan.app.service.admin.dto.AdminDTO;

@RestController
@RequestMapping("/cms")
public class AdminResource {
	
	public static final Logger logger = LoggerFactory.getLogger(AdminResource.class);
	
	@Resource(name="adminAdminService")
	private AdminService adminService;
	
	@PostMapping("/admin")
	public ResponseEntity<?> createAdmin(@ModelAttribute @Valid AdminDTO.Create adminDTO, BindingResult result) {
		
		if(result.hasErrors()) {
			logger.info(result.toString());
			List<ObjectError> errorMessage = result.getAllErrors();
			return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
		}
		
		Admin newAdmin = adminService.createAdmin(adminDTO);
		
		logger.info("newAdmin = " + newAdmin);
		return new ResponseEntity<>(newAdmin, HttpStatus.OK);
	}

}
