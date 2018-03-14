package com.shan.app.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Entity
@Data
@Table(name = "tb_authority")
public class Authority {
	
	@Id
	@GeneratedValue
	private Long id;

	@NotBlank
	@Size(max = 50)
	@Column(length = 50)
	private String authority;
	
	@NotBlank
	@Column(name = "authority_name", nullable = false, length = 200)
	private String authorityName;
	
//	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "authorities")
//	private Set<Admin> admins = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "tb_authority_menu",
		joinColumns = @JoinColumn(name = "authority_id"),
		inverseJoinColumns = @JoinColumn(name = "menu_id"))
	private List<Menu> menus = new ArrayList();

}
