package com.shan.app.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_admin")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Admin {

	@Id
	@GeneratedValue
	private Long id;
	
	@NotBlank
	@Size(min = 1, max = 50)
	@Column(name = "user_id", length = 50, unique = true, nullable = false)
	private String userId;

	@NotBlank
	@Column(nullable = false)
	private String password;
	
	private String salt;
	
	@NotBlank
	@Size(min = 1, max = 50)
	@Column(length = 100, nullable = false)
	private String name;
	
	private String email;
	
	private String hp;
	
	private String tel;
	
	@Column(name = "reg_user_id")
	private String regUserId;
	
	@Column(nullable = false)
	private String state = "N";
	
	@Column(name = "password_update_date")
	private Date passwordUpdateDate;
	
	@Column(name = "reg_date")
	private Date regDate;
	
	@Column(name = "update_date")
	private Date updateDate;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
		name = "tb_admin_authority",
		joinColumns = @JoinColumn(name = "admin_id"),
		inverseJoinColumns = @JoinColumn(name = "authority_id"))
	private Set<Authority> authorities = new HashSet<>();
	
	public void addAuthority(Authority authority) {
		this.authorities.add(authority);
	}
	
}
