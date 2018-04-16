package com.shan.app.web.cms;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.shan.app.domain.Code;
import com.shan.app.service.cms.CodeService;
import com.shan.app.service.cms.dto.CodeDTO;

@RestController("cmsCodeResource")
@RequestMapping("/cms/{menuId}")
public class CodeResource {
	
	private final Logger logger = LoggerFactory.getLogger(CodeResource.class);
	
	@Resource(name = "cmsCodeService")
	private CodeService codeService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping("/code")
	public ResponseEntity<Object> createCode(@RequestBody @Valid CodeDTO.Create create) {
		Code newCode = codeService.createCode(create);
		return new ResponseEntity<>(newCode, HttpStatus.CREATED);
	}
	
	@PutMapping("/code/{id}")
	public ResponseEntity<Object> updateCode(@PathVariable Long id,
			@RequestBody @Valid CodeDTO.Update update) {
		Code updatedCode = codeService.updateCode(id, update);
		return new ResponseEntity<>(modelMapper.map(updatedCode, CodeDTO.Response.class), HttpStatus.OK);
	}
	
	@GetMapping("/codes")
	public ResponseEntity<Object> getCodes(Pageable pageable) {
		Page<Code> codes = codeService.getCodes(pageable);
		return new ResponseEntity<>(codes.map(code -> modelMapper.map(code, CodeDTO.Response.class)), HttpStatus.OK);
	}
	
	@GetMapping("/code/{id}")
	public ResponseEntity<Object> getCode(@PathVariable Long id) {
		Code code = codeService.getCode(id);
		return new ResponseEntity<>(modelMapper.map(code, CodeDTO.Response.class), HttpStatus.OK);
	}
	
	@DeleteMapping("/code/{id}")
	public ResponseEntity<Object> deleteCode(@PathVariable Long id) {
		codeService.deleteCode(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
