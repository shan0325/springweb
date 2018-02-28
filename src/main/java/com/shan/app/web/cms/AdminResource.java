package com.shan.app.web.cms;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shan.app.domain.Admin;
import com.shan.app.service.cms.AdminService;
import com.shan.app.service.cms.dto.AdminDTO;

@RestController
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
		
		logger.info("newAdmin = " + newAdmin);
		return new ResponseEntity<>(modelMapper.map(newAdmin, AdminDTO.Admin.class), HttpStatus.CREATED);
	}

}
