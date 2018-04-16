package com.shan.app.service.cms;

import java.util.Date;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shan.app.domain.Code;
import com.shan.app.domain.GroupCode;
import com.shan.app.repository.cms.CodeRepository;
import com.shan.app.repository.cms.GroupCodeRepository;
import com.shan.app.service.cms.dto.CodeDTO.Create;
import com.shan.app.service.cms.dto.CodeDTO.Update;
import com.shan.app.web.errors.EntityDuplicatedException;
import com.shan.app.web.errors.EntityNotFoundException;

@Service("cmsCodeService")
@Transactional
public class CodeService {
	
	@Resource(name = "cmsGroupCodeRepository")
	private GroupCodeRepository groupCodeRepository;
	
	@Resource(name = "cmsCodeRepository")
	private CodeRepository codeRepository;
	

	public Code createCode(Create create) {
		//상위 그룹코드가 존재하는 체크
		GroupCode groupCode = groupCodeRepository.findOne(create.getGroupCodeId());
		if(groupCode == null) {
			throw new EntityNotFoundException(GroupCode.class, "id", String.valueOf(create.getGroupCodeId()));
		}
		
		Code codeDetail = codeRepository.findOneByCode(create.getCode());
		if(codeDetail != null) {
			throw new EntityDuplicatedException(Code.class, "code", create.getCode());
		}
		
		Code code = new Code();
		code.setCode(create.getCode());
		code.setCodeName(create.getCodeName());
		code.setGroupCode(groupCode);
		code.setOrd(create.getOrd());
		code.setUseYn(create.getUseYn());
		code.setRegDate(new Date());
		
		return codeRepository.save(code);
	}

	public Code updateCode(Long id, Update update) {
		Code code = getCode(id);
		code.setCodeName(update.getCodeName());
		code.setOrd(update.getOrd());
		code.setUseYn(update.getUseYn());
		code.setUpdateDate(new Date());
		
		return codeRepository.save(code);
	}
	
	public Code getCode(Long id) {
		Code code = codeRepository.findOne(id);
		if(code == null) {
			throw new EntityNotFoundException(Code.class, "id", String.valueOf(id));
		}
		return code;
	}

	public Page<Code> getCodes(Pageable pageable) {
		return codeRepository.findAll(pageable);
	}

	public void deleteCode(Long id) {
		codeRepository.delete(getCode(id));
	}

}
