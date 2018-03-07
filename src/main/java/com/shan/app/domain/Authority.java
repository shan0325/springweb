package com.shan.app.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Entity
@Data
@Table(name="tb_authority")
public class Authority {

	@NotBlank
	@Size(max=50)
	@Id
	@Column(length=50)
	private String authority;
	
	@NotBlank
	@Column(name="authority_name", nullable=false, length=200)
	private String authorityName;
	
	@ManyToMany(mappedBy="authorities")
	private Set<Admin> admins = new HashSet<>();
	
}
