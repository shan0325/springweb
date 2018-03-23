package com.shan.app.service.cms;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shan.app.domain.GroupCode;
import com.shan.app.repository.cms.GroupCodeRepository;
import com.shan.app.service.cms.dto.GroupCodeDTO;
import com.shan.app.web.errors.EntityDuplicatedException;

@Service("cmsGroupCodeService")
public class GroupCodeService {

	@Resource(name = "cmsGroupCodeRepository")
	private GroupCodeRepository groupCodeRepository;

	public GroupCode createGroupCode(GroupCodeDTO.Create create) {
		
		GroupCode groupCodeDetail = groupCodeRepository.findOneByGroupCode(create.getGroupCode());
		if(groupCodeDetail != null) {
			throw new EntityDuplicatedException(GroupCode.class, "groupCode", create.getGroupCode());
		}

		GroupCode groupCode = new GroupCode();
		groupCode.setGroupCode(create.getGroupCode());
		groupCode.setGroupCodeName(create.getGroupCodeName());
		groupCode.setUseYn(create.getUseYn());
		groupCode.setRegDate(new Date());
		
		return groupCodeRepository.save(groupCode);
	}
	
	
}
