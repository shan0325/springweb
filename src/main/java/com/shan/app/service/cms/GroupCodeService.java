package com.shan.app.service.cms;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shan.app.domain.GroupCode;
import com.shan.app.repository.cms.GroupCodeRepository;
import com.shan.app.service.cms.dto.GroupCodeDTO;

@Service("cmsGroupCodeService")
public class GroupCodeService {

	@Resource(name = "cmsGroupCodeRepository")
	private GroupCodeRepository groupCodeRepository;

	public GroupCode createGroupCode(GroupCodeDTO.Create create) {

		GroupCode groupCode = new GroupCode();
		groupCode.setGroupCode(create.getGroupCode());
		groupCode.setGroupCodeName(create.getGroupCodeName());
		groupCode.setUseYn(create.getUseYn());
		groupCode.setRegDate(new Date());
		
		return groupCodeRepository.save(groupCode);
	}
	
	
}
