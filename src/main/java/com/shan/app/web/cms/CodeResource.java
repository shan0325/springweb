package com.shan.app.web.cms;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shan.app.domain.Code;
import com.shan.app.service.cms.CodeService;
import com.shan.app.service.cms.dto.CodeDTO;

@RestController("cmsCodeResource")
@RequestMapping("/cms/{menuId}")
public class CodeResource {
	
	private Logger logger = LoggerFactory.getLogger(CodeResource.class);
	
	@Resource(name = "cmsCodeService")
	private CodeService codeService;
	
	@PostMapping("/code")
	public ResponseEntity<Object> createCode(@RequestBody @Valid CodeDTO.Create create) {
		Code newCode = codeService.createCode(create);
		return new ResponseEntity<>(newCode, HttpStatus.CREATED);
	}
	
	@PutMapping("/code/{id}")
	public ResponseEntity<Object> updateCode(@PathVariable Long id,
			@RequestBody @Valid CodeDTO.Update update) {
		Code updateCode = codeService.updateCode(id, update);
		
		return null;
	}

}
