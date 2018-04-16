package com.shan.app.web.cms;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shan.app.domain.Authority;
import com.shan.app.service.cms.AuthorityService;
import com.shan.app.service.cms.dto.AuthorityDTO;

@RestController("cmsAuthorityResource")
@RequestMapping("/cms/{menuId}")
public class AuthorityResource {
	
	@Resource(name = "cmsAuthorityService")
	private AuthorityService authorityService;

	@PostMapping("/authority")
	public ResponseEntity<Object> createAuthority(@RequestBody @Valid AuthorityDTO.Create create) {
		
		Authority newAuthority = authorityService.createAuthority(create);
		
		return null;
	}
}
