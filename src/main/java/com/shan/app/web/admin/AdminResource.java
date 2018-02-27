package com.shan.app.web.admin;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shan.app.domain.Admin;
import com.shan.app.service.admin.AdminService;

@RestController
@RequestMapping("/cms")
public class AdminResource {
	
	public static final Logger logger = LoggerFactory.getLogger(AdminResource.class);
	
	@Resource(name="adminAdminService")
	private AdminService adminService;
	
	@PostMapping("/admin")
	public String createAdmin(@ModelAttribute @Valid Admin admin, BindingResult result) {
		
		if(result.hasErrors()) {
			logger.info(result.toString());
			return "main";
		}
		
		Admin newAdmin = adminService.createAdmin(admin);
		
		logger.info("newAdmin = " + newAdmin);
		return "main";
	}

}
