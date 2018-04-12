package com.shan.app.service.cms.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.shan.app.domain.Code;

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
	
	@Data
	public static class Response {
		private Long id;
		private String groupCode;
		private String groupCodeName;
		private String useYn;
		private Date regDate;
		private Date updateDate;
		private List<Code> codes;
	}
}
