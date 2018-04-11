package com.shan.app.service.cms.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

public class GroupCodeDTO {

	@Data
	public static class Create {
		@NotBlank(message = "그룹코드를 입력해주세요.")
		@Size(max = 20)
		private String groupCode;
		@NotBlank(message = "그룹코드명을 입력해주세요.")
		@Size(max = 50)
		private String groupCodeName;
		@NotBlank(message = "사용여부를 입력해주세요.")
		@Size(max = 1)
		private String useYn;
	}
	
	@Data
	public static class Update {
		@NotBlank(message = "그룹코드명을 입력해주세요.")
		@Size(max = 50)
		private String groupCodeName;
		@NotBlank(message = "사용여부를 입력해주세요.")
		@Size(max = 1)
		private String useYn;
	}
}
