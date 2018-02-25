package com.shan.app.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Entity
@Data
public class Admin {

	@Id
	@GeneratedValue
	private Long id;
	
	@NotBlank
	@Size(min=1, max=50)
	@Column(name="user_id", length=50, unique=true, nullable=false)
	private String userId;

	@NotBlank
	@Column(nullable=false)
	private String password;
	
	private String salt;
	
	private String email;
	
	private String hp;
	
	private String tel;
	
	private String regUserId;
	
	@Column(nullable=false)
	private String state = "N";
	
	private Date passwordUpdateDate;
	
	private Date regDate;
	
	private Date updateDate;
	
}
