package com.shan.app.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shan.app.domain.Admin;

@Repository("adminAdminRepository")
public interface AdminRepository extends JpaRepository<Admin, Long> {

	Admin findByUserId(String userId);

}
