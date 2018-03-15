package com.shan.app.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_group_category")
public class GroupCategory {

	@Id
	@Column(name = "group_category", length = 20)
	private String groupCategory;
	
	@Column(name = "group_category_name", length = 100)
	private String groupCategoryName;
	
	@Column(length = 1, nullable = false)
	private String useYn;
	
	@Column(name = "reg_date")
	private Date regDate;
	
	@Column(name = "update_date")
	private Date updateDate;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "groupCategory")
	private List<Category> categorys;
}
