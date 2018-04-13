package com.shan.app.repository.cms;

import com.shan.app.domain.GroupCode;

public interface GroupCodeRepositoryCustom {
	
	GroupCode findGroupCodeWithCodesByQueryDsl(Long id);

}
