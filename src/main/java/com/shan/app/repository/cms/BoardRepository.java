package com.shan.app.repository.cms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shan.app.domain.Board;

@Repository("cmsBoardRepository")
public interface BoardRepository extends JpaRepository<Board, Long> {

}
