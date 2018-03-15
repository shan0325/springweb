package com.shan.app.repository.cms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shan.app.domain.Menu;

@Repository("cmsMenuRepository")
public interface MenuRepository extends JpaRepository<Menu, Long> {

}
