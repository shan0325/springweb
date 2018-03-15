package com.shan.app.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_contents")
public class Contents {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "menu_id", nullable = false)
	private Long menuId;
	
	@Column(length = 300, nullable = false)
	private String title;
	
	@Lob
	private String content;
	
	@Column(name = "use_yn", length = 1, nullable = false)
	private String useYn;
	
	@Column(name = "reg_user_id")
	private String regUserId;
	
	@Column(name = "reg_date")
	private Date regDate;
	
	@Column(name = "update_date")
	private Date updateDate;
}
