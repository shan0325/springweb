package com.shan.app.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class AdminRole {

	@Id
	@GeneratedValue
	private Long id;
	
	private String role;
}
