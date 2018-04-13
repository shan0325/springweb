package com.shan.app.service.cms.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

public class CodeDTO {
	
	@Data
	public static class Create {
		@NotBlank(message = "코드를 입력해주세요.")
		@Size(max = 20)
		private String code;
		@NotBlank(message = "코드명을 입력해주세요.")
		@Size(max = 50)
		private String codeName;
		@NotBlank(message = "사용여부를 입력해주세요.")
		@Size(max = 1)
		private String useYn;
		@NotNull(message = "순서를 입력해주세요.")
		private Integer ord;
		@NotNull(message = "그룹코드 아이디가 존재하지않습니다.")
		private Long groupCodeId;
	}
	
	@Data
	public static class Update {
		@NotBlank(message = "코드명을 입력해주세요.")
		@Size(max = 50)
		private String codeName;
		@NotBlank(message = "사용여부를 입력해주세요.")
		@Size(max = 1)
		private String useYn;
		@NotNull(message = "순서를 입력해주세요.")
		private Integer ord;
		@NotNull(message = "그룹코드 아이디가 존재하지않습니다.")
		private Long groupCodeId;
	}

}
