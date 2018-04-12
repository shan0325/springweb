package com.shan.app.service.cms;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shan.app.domain.GroupCode;
import com.shan.app.repository.cms.GroupCodeRepository;
import com.shan.app.service.cms.dto.GroupCodeDTO;
import com.shan.app.service.cms.dto.GroupCodeDTO.Update;
import com.shan.app.web.errors.EntityDuplicatedException;
import com.shan.app.web.errors.EntityNotFoundException;

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

	public GroupCode updateGroupCode(Long id, Update update) {
		GroupCode groupCode = getGroupCode(id);
		groupCode.setGroupCodeName(update.getGroupCodeName());
		groupCode.setUseYn(update.getUseYn());
		groupCode.setUpdateDate(new Date());
		
		return groupCodeRepository.save(groupCode);
	}
	
	public GroupCode getGroupCode(Long id) {
		GroupCode groupCode = groupCodeRepository.findOne(id);
		if(groupCode == null) {
			throw new EntityNotFoundException(GroupCode.class, "id", String.valueOf(id));
		}
		return groupCode;
	}
	
	public GroupCodeDTO.Response findGroupCodeByQueryDsl(Long id) {
		GroupCodeDTO.Response groupCode = groupCodeRepository.findGroupCodeByQueryDsl(id);
		if(groupCode == null) {
			throw new EntityNotFoundException(GroupCode.class, "id", String.valueOf(id));
		}
		return groupCode;
	}

	public Page<GroupCode> getGroupCodes(Pageable pageable) {
		return groupCodeRepository.findAll(pageable);
	}

	public void deleteGroupCode(Long id) {
		groupCodeRepository.delete(getGroupCode(id));
	}
	
}
