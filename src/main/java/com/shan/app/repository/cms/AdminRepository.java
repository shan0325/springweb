package com.shan.app.repository.cms;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shan.app.domain.Admin;

@Repository("cmsAdminRepository")
public interface AdminRepository extends JpaRepository<Admin, Long> {

	Admin findOneByUserId(String userId);
	
	@EntityGraph(attributePaths="authorities")
	Optional<Admin> findOneWithAuthoritiesByUserId(String userId);
	

}
