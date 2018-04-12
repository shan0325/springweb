package com.shan.app.web.cms;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.shan.app.domain.GroupCode;
import com.shan.app.service.cms.GroupCodeService;
import com.shan.app.service.cms.dto.GroupCodeDTO;

@RestController("cmsGroupCodeResource")
@RequestMapping("/cms/{menuId}")
public class GroupCodeResource {
	
	private final Logger logger = LoggerFactory.getLogger(GroupCodeResource.class);

	@Resource(name = "cmsGroupCodeService")
	private GroupCodeService groupCodeService;
	
	@PostMapping("/group-code")
	public ResponseEntity<Object> createGroupCode(@RequestBody @Valid GroupCodeDTO.Create create) {
		
		GroupCode newGroupCode = groupCodeService.createGroupCode(create);
		
		return new ResponseEntity<>(newGroupCode, HttpStatus.CREATED);
	}
	
	@PutMapping("/group-code/{id}")
	public ResponseEntity<Object> updateGroupCode(@PathVariable Long id, 
			@RequestBody @Valid GroupCodeDTO.Update update) {
		
		GroupCode updateGroupCode = groupCodeService.updateGroupCode(id, update);
		
		return new ResponseEntity<>(updateGroupCode, HttpStatus.OK);
	}
	
	@GetMapping("/group-codes")
	public ResponseEntity<Object> getGroupCodes(Pageable pageable) {
		Page<GroupCode> groupCode = groupCodeService.getGroupCodes(pageable);
		return new ResponseEntity<>(groupCode, HttpStatus.OK);
	}
	
	@GetMapping("/group-code/{id}")
	public ResponseEntity<Object> getGroupCode(@PathVariable Long id) {
		GroupCodeDTO.Response groupCode = groupCodeService.findGroupCodeByQueryDsl(id);
		return new ResponseEntity<>(groupCode, HttpStatus.OK);
	}
	
	@DeleteMapping("/group-code/{id}")
	public ResponseEntity<Object> deleteGroupCode(@PathVariable Long id) {
		groupCodeService.deleteGroupCode(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
