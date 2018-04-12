package com.shan.app.repository.cms.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import com.shan.app.domain.Code;
import com.shan.app.domain.GroupCode;
import com.shan.app.domain.QCode;
import com.shan.app.domain.QGroupCode;
import com.shan.app.repository.cms.GroupCodeRepositoryCustom;
import com.shan.app.service.cms.dto.GroupCodeDTO;

public class GroupCodeRepositoryImpl extends QueryDslRepositorySupport implements GroupCodeRepositoryCustom {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public GroupCodeRepositoryImpl() {
		super(GroupCode.class);
	}
	
	@Override
	public GroupCodeDTO.Response findGroupCodeByQueryDsl(Long id) {
		GroupCodeDTO.Response response = new GroupCodeDTO.Response();
		
		QGroupCode qGroupCode = QGroupCode.groupCode1;
		QCode qCode = QCode.code1;
		
		GroupCode groupCode = from(qGroupCode)
								.where(qGroupCode.id.eq(id))
								.fetchOne();
		
		List<Code> codes = from(qCode)
							.where(qCode.groupCode.eq(groupCode))
							.fetch();
		
		response = modelMapper.map(groupCode, GroupCodeDTO.Response.class);
		response.setCodes(codes);
		
		return response;
	}
}
