package com.shan.app.service.admin.dto;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

public class AdminDTO {
	
	@Data
	public static class Create {
		@NotBlank
		private String userId;
		@NotBlank
		private String password;
		private String email;
		private String hp;
		private String tel;
	}

}
