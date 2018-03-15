package com.shan.app.repository.cms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shan.app.domain.Contents;

@Repository("cmsContentsRepository")
public interface ContentsRepository extends JpaRepository<Contents, Long> {

}
