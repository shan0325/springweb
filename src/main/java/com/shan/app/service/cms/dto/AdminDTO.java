package com.shan.app.service.cms.dto;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.shan.app.domain.Authority;

import lombok.Data;

public class AdminDTO {
	
	@Data
	public static class Admin {
		private Long id;
		private String userId;
		private String name;
		private String email;
		private String hp;
		private String tel;
		private String regUserId;
		private String state;
		private Date passwordUpdateDate;
		private Date regDate;
		private Date updateDate;
		private Set<Authority> authorities;
	}
	
	@Data
	public static class Create {
		@NotBlank(message = "아이디를 입력해주세요.")
		@Size(min = 1, max = 50)
		private String userId;
		@NotBlank(message = "비밀번호를 입력해주세요.")
		private String password;
		@NotBlank(message = "이름을 입력해주세요.")
		@Size(min = 1, max = 50)
		private String name;
		private String email;
		private String hp;
		private String tel;
		@NotNull(message = "권한을 입력해주세요.")
		private Set<String> authorities;
	}
	
	@Data
	public static class Update {
		@NotBlank(message = "비밀번호를 입력해주세요.")
		private String password;
		@NotBlank(message = "비밀번호 확인을 입력해주세요.")
		private String passwordConfirm;
		@NotBlank(message = "이름을 입력해주세요.")
		@Size(min = 1, max = 50)
		private String name;
		private String email;
		private String hp;
		private String tel;
		@NotBlank(message = "상태를 입력해주세요.")
		private String state;
		@NotNull(message = "권한을 입력해주세요.")
		private Set<String> authorities;
	}

}
