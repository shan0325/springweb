package com.shan.app.repository.cms;

import com.shan.app.service.cms.dto.GroupCodeDTO;

public interface GroupCodeRepositoryCustom {
	
	GroupCodeDTO.Response findGroupCodeByQueryDsl(Long id);

}
