package com.shan.app.repository.cms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shan.app.domain.BoardManager;

@Repository("cmsBoardManagerRepository")
public interface BoardManagerRepository extends JpaRepository<BoardManager, Long> {

}
