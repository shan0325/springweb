package com.shan.app.web.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminLoginController {

	
	@GetMapping("/login")
	public ResponseEntity<?> loginForm() {
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
