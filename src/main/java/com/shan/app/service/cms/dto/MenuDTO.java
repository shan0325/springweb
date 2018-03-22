package com.shan.app.service.cms.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

public class MenuDTO {
	
	@Data
	public static class Create {
		@NotBlank(message = "parentId 값이 없습니다.")
		private Long parentId;
		@NotBlank(message = "메뉴명을 입력해주세요.")
		@Size(max = 50)
		private String name;
		private String description;
		@NotBlank(message = "사용여부를 입력해주세요.")
		private String useYn;
		@NotBlank(message = "메뉴타입을 입력해주세요.")
		private String menuType;
		@NotBlank(message = "url을 입력해주세요.")
		private String url;
		private String urlTarget;
		@NotBlank(message = "메뉴 순서를 입력해주세요.")
		private Integer ord;
		private Long boardManagerId;
	}

}
