package com.shan.app.repository.cms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shan.app.domain.Category;

@Repository("cmsCategoryRepository")
public interface CategoryRepository extends JpaRepository<Category, String> {

}
