package com.shan.app.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_menu")
public class Manu {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "parent_id")
	private Long parentId;
	
	@Column(name = "top_id")
	private Long topId;
	
	private String name;
	
	@Column(name = "use_yn")
	private String useYn;
	
	private String type;
	
	private String url;
	
	@Column(name = "url_target")
	private String urlTarget;
	
	private Integer odr;
	
	@Column(name = "reg_date")
	private Date regDate;
	
	@Column(name = "update_date")
	private Date updateDate;
	
	
}
