package com.shan.app.repository.cms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shan.app.domain.File;

@Repository("cmsFileRepository")
public interface FileRepository extends JpaRepository<File, Long> {

}
