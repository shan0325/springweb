package com.shan.app.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shan.app.domain.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

	Admin findByUserId(String userId);

}
