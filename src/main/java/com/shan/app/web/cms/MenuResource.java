package com.shan.app.web.cms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("cmsMenuResource")
@RequestMapping("/cms")
public class MenuResource {
	
	public static final Logger logger = LoggerFactory.getLogger(MenuResource.class);
	
	
	@PostMapping("/menu")
	public ResponseEntity<Object> createMenu() {
		
		return null;
	}

}
