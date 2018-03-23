package com.shan.app.repository.cms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shan.app.domain.GroupCode;

@Repository("cmsGroupCodeRepository")
public interface GroupCodeRepository extends JpaRepository<GroupCode, Long> {

	GroupCode findOneByGroupCode(String groupCode);

}
