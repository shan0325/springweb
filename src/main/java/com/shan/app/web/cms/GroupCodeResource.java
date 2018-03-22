package com.shan.app.web.cms;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shan.app.domain.GroupCode;
import com.shan.app.service.cms.GroupCodeService;
import com.shan.app.service.cms.dto.GroupCodeDTO;

@RestController
@RequestMapping("/cms")
public class GroupCodeResource {

	@Resource(name = "cmsGroupCodeService")
	private GroupCodeService groupCodeService;
	
	@PostMapping("/group-code")
	public ResponseEntity<Object> createGroupCode(@RequestBody @Valid GroupCodeDTO.Create create) {
		
		GroupCode newGroupCode = groupCodeService.createGroupCode(create);
		
		return new ResponseEntity<>(newGroupCode, HttpStatus.CREATED);
	}
}
