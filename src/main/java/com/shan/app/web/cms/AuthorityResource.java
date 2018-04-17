package com.shan.app.web.cms;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.shan.app.domain.Authority;
import com.shan.app.service.cms.AuthorityService;
import com.shan.app.service.cms.dto.AuthorityDTO;

@RestController("cmsAuthorityResource")
@RequestMapping("/cms/{menuId}")
public class AuthorityResource {
	
	@Resource(name = "cmsAuthorityService")
	private AuthorityService authorityService;
	
	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/authority")
	public ResponseEntity<Object> createAuthority(@RequestBody @Valid AuthorityDTO.Create create) {
		Authority newAuthority = authorityService.createAuthority(create);
		return new ResponseEntity<>(modelMapper.map(newAuthority, AuthorityDTO.Response.class), HttpStatus.CREATED);
	}
	
	@PutMapping("/authority/{id}")
	public ResponseEntity<Object> updateAuthority(@PathVariable Long id,
			@RequestBody @Valid AuthorityDTO.Update update) {
		Authority updatedAuthority = authorityService.updateAuthority(id, update);
		return new ResponseEntity<>(modelMapper.map(updatedAuthority, AuthorityDTO.Response.class), HttpStatus.OK);
	}
	
	@GetMapping("/authoritys")
	public ResponseEntity<Object> getAuthoritys(Pageable pageable) {
		Page<Authority> authoritys = authorityService.getAuthoritys(pageable);
		return new ResponseEntity<>(authoritys.map(authority -> modelMapper.map(authority, AuthorityDTO.Response.class)), HttpStatus.OK);
	}
	
	@GetMapping("/authority/{id}")
	public ResponseEntity<Object> getAuthority(@PathVariable Long id) {
		Authority authority = authorityService.getAuthority(id);
		return new ResponseEntity<>(modelMapper.map(authority, AuthorityDTO.Response.class), HttpStatus.OK);
	}
	
	@DeleteMapping("/authority/{id}")
	public ResponseEntity<Object> deleteAuthority(@PathVariable Long id) {
		authorityService.deleteAuthority(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
