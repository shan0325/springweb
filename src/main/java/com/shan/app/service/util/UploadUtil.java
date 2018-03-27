package com.shan.app.service.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import com.shan.app.service.cms.dto.FileDTO;

@Component
public class UploadUtil {

	@Value("${upload.image.type}")
	private String UPLOAD_IMAGE_TYPE;
	
	@Value("${upload.image.size}")
	private long UPLOAD_IMAGE_SIZE;
	
	public FileDTO.Create uploadImage(MultipartFile multipartFile, String path) throws Exception {
		
		FileDTO.Create create = null;
		if(multipartFile != null && !multipartFile.isEmpty()) {
			String originalFileName = multipartFile.getOriginalFilename();
			String originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
			String newFileName = System.currentTimeMillis() + UUID.randomUUID().toString() + "." + originalFileExtension;
			long fileSize = multipartFile.getSize();
			
			//파일 확장자 체크
			String[] types = UPLOAD_IMAGE_TYPE.split(",");
			long existCnt = Stream.of(types)
								.filter(type -> originalFileExtension.toUpperCase().equals(type.trim().toUpperCase()))
								.count();
			if(existCnt < 1) {
				throw new IOException("Bad extension file upload");
			}
			
			//파일 사이즈 체크
			if(UPLOAD_IMAGE_SIZE < fileSize) {
				throw new MaxUploadSizeExceededException(UPLOAD_IMAGE_SIZE);
			}
			
			//폴더 없으면 생성
			File dir = new File(path);
			if(!dir.exists()) {
				dir.mkdirs();
			}
			
			multipartFile.transferTo(new File(path + File.separator + newFileName));
			
			create = new FileDTO.Create();
			create.setOriginalFileName(originalFileName);
			create.setNewFileName(newFileName);
			create.setSavePath(path);
			create.setSize(fileSize);
		}
		
		return create;
	}
	
	
}
