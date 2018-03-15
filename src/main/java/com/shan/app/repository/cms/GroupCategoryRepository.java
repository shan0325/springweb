package com.shan.app.repository.cms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shan.app.domain.GroupCategory;

@Repository("cmsGroupCategoryRepository")
public interface GroupCategoryRepository extends JpaRepository<GroupCategory, String> {

}
