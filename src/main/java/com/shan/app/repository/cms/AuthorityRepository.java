package com.shan.app.repository.cms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shan.app.domain.Authority;

@Repository("cmsAuthorityRepository")
public interface AuthorityRepository extends JpaRepository<Authority, String> {

}
