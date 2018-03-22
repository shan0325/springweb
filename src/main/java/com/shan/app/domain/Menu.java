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
	
	@Column(name = "menu_image_path")
	private String menuImagePath;
	
	@Column(name = "use_yn", length = 1, nullable = false)
	private String useYn;
	
	@Column(name = "menu_type", nullable = false)
	private String menuType;
	
	@Column(name = "url_type", nullable = false)
	private String urlType;	//local(/menu/..), external(http://..)
	
	@Column(nullable = false)
	private String url;
	
	@Column(name = "url_target", nullable = false)
	private String urlTarget;
	
	@Column(nullable = false)
	private Integer ord;
	
	@Column(name = "reg_date")
	private Date regDate;
	
	@Column(name = "update_date")
	private Date updateDate;
	
	@ManyToOne
	@JoinColumn(name = "board_manager_id", nullable = false)
	private BoardManager boardManager;
}
