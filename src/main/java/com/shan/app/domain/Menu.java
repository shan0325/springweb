package com.shan.app.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_menu")
public class Menu {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "parent_id", nullable = false)
	private Long parentId;
	
	@Column(name = "top_id", nullable = false)
	private Long topId;

	@Column(nullable = false)
	private Integer depth;
	
	@Column(length = 100, nullable = false)
	private String name;
	
	private String description;
	
	@Column(name = "image_menu_path")
	private String imageMenuPath;
	
	@Column(name = "use_yn", length = 1, nullable = false)
	private String useYn;
	
	@Column(name = "menu_gubun", length = 50, nullable = false)
	private String menuGubun;
	
	@Column(name = "menu_type", length = 50, nullable = false)
	private String menuType;
	
	private String cmsUrl;
	
	@Column(name = "cms_url_target", length = 50)
	private String cmsUrlTarget;
	
	private String homeUrl;
	
	@Column(name = "home_url_target", length = 50)
	private String homeUrlTarget;
	
	@Column(nullable = false)
	private Integer ord;
	
	@Column(name = "reg_date")
	private Date regDate;
	
	@Column(name = "update_date")
	private Date updateDate;
	
	@ManyToOne
	@JoinColumn(name = "board_manager_id")
	private BoardManager boardManager;
}
