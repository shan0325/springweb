package com.shan.app.service.util;

import java.io.File;
import java.util.UUID;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import com.shan.app.service.cms.dto.FileDTO;
import com.shan.app.web.errors.ExtensionNotAllowedException;

@Component
public class UploadUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(UploadUtil.class);

	@Value("${upload.image.type}")
	private String UPLOAD_IMAGE_TYPE;
	
	@Value("${upload.image.size}")
	private long UPLOAD_IMAGE_SIZE;
	
	public FileDTO.Create uploadImage(HttpServletRequest request, MultipartFile multipartFile, String path) throws Exception {
		
		FileDTO.Create create = null;
		if(multipartFile != null && !multipartFile.isEmpty()) {
			String originalFileName = multipartFile.getOriginalFilename();
			String originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
			String newFileName = System.currentTimeMillis() + UUID.randomUUID().toString() + "." + originalFileExtension;
			long fileSize = multipartFile.getSize();
			String realPath = request.getSession().getServletContext().getRealPath(path);
			
			//파일 확장자 체크
			String[] types = UPLOAD_IMAGE_TYPE.split(",");
			long extensionCnt = Stream.of(types)
								.filter(type -> originalFileExtension.toLowerCase().equals(type.trim().toLowerCase()))
								.count();
			
			//파일 콘텐츠 타입 체크
			logger.debug("File Content-Type = " + multipartFile.getContentType());
			long contentTypeCnt = Stream.of(types)
								.filter(type -> ("image/" + type.trim().toLowerCase()).equals(multipartFile.getContentType().toLowerCase()))
								.count();
			
			if(extensionCnt < 1 || contentTypeCnt < 1) {
				throw new ExtensionNotAllowedException(UPLOAD_IMAGE_TYPE);
			}
			
			//파일 사이즈 체크
			if(UPLOAD_IMAGE_SIZE < fileSize) {
				throw new MaxUploadSizeExceededException(UPLOAD_IMAGE_SIZE);
			}
			
			//폴더 없으면 생성
			logger.debug("realPath = " + realPath);
			File dir = new File(realPath);
			if(!dir.exists()) {
				dir.mkdirs();
			}
			
			multipartFile.transferTo(new File(realPath + File.separator + newFileName));
			
			create = new FileDTO.Create();
			create.setOriginalFileName(originalFileName);
			create.setNewFileName(newFileName);
			create.setSavePath(path);
			create.setSize(fileSize);
		}
		
		return create;
	}
	
	
}
