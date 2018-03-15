package com.shan.app.repository.cms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shan.app.domain.Code;

@Repository("cmsCodeRepository")
public interface CodeRepository extends JpaRepository<Code, String> {

}
