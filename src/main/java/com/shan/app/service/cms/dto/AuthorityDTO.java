package com.shan.app.service.cms.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

public class AuthorityDTO {

	@Data
	public static class Create {
		@NotBlank(message = "권한을 입력해주세요.")
		@Size(max = 50)
		private String authority;
		@NotBlank(message = "권한명을 입력해주세요.")
		@Size(max = 100)
		private String authorityName;
	}
	
	@Data
	public static class Update {
		@NotBlank(message = "권한명을 입력해주세요.")
		@Size(max = 100)
		private String authorityName;
	}
	
	@Data
	public static class Response {
		private Long id;
		private String authority;
		private String authorityName;
	}
}
