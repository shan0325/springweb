package com.shan.app.service.cms.dto;

import lombok.Data;

public class FileDTO {
	
	@Data
	public static class Create {
		private String originalFileName;
		private String newFileName;
		private String savePath;
		private Long size;
		private String gubun;
	}

}
