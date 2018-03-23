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
@Table(name = "tb_category")
public class Category {
	
	@Id
	@GeneratedValue
	private Long id;

	@Column(length = 20, nullable = false)
	private String category;
	
	@Column(length = 100, nullable = false)
	private String categoryName;
	
	@Column(length = 1, nullable = false)
	private String useYn;
	
	@Column(nullable = false)
	private Integer ord;
	
	@Column(name = "reg_date")
	private Date regDate;
	
	@Column(name = "update_date")
	private Date updateDate;
	
	@ManyToOne
	@JoinColumn(name = "group_category_id", nullable = false)
	private GroupCategory groupCategory;
}
