package com.shan.app.service.cms.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

public class MenuDTO {
	
	@Data
	public static class Create {
		private Long parentId;
		@NotBlank(message = "메뉴명을 입력해주세요.")
		@Size(max = 50)
		private String name;
		private String description;
		@NotBlank(message = "사용여부를 입력해주세요.")
		private String useYn;
		@NotBlank(message = "메뉴구분을 입력해주세요.")
		private String menuGubun;
		@NotBlank(message = "메뉴타입을 입력해주세요.")
		private String menuType;
		private String url;
		private String urlTarget;
		@NotNull(message = "메뉴순서를 입력해주세요.")
		private Integer ord;
		private Long boardManagerId;
	}

}
