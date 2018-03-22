package com.shan.app.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_file")
public class File {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "real_file_name", nullable = false) //실제 파일명
	private String realFileName;
	
	@Column(name = "new_file_name", nullable = false) //새로운 파일명
	private String newFileName;
	
	@Column(name = "save_path", nullable = false)
	private String savePath;
	
	@Column(nullable = false)
	private String size;
	
	private String gubun;
	
	@Column(name = "reg_date")
	private Date regDate;
	
	@Column(name = "update_date")
	private Date updateDate;
	
	@ManyToOne
	@JoinColumn(name = "board_id", nullable = false)
	private Board board;
}
