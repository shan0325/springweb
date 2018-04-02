package com.shan.app.common;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.FileInputStream;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class UploadTest {

	@Test
	public void mockMultipartFileTest() throws Exception {
		FileInputStream fis = new FileInputStream(new File("src/main/resources/static/image/test.jpg"));
		MockMultipartFile multipartFile = new MockMultipartFile("test", "test.jpg", MediaType.IMAGE_JPEG_VALUE, fis);
		
		byte[] b = multipartFile.getBytes();
		for(int i=0; i<b.length; i++) {
			System.out.println("[" + i + "] = " + b[i] + "	to binary : " + byteToBinaryString(b[i]));
		}
		
		System.out.println("multipartFile = " + multipartFile);
		System.out.println("multipartFile.getContentType() = " + multipartFile.getContentType());
		System.out.println("multipartFile.getOriginalFilename() = " + multipartFile.getOriginalFilename());
		System.out.println("multipartFile.getSize() = " + multipartFile.getSize());
		
		multipartFile.transferTo(new File("/upload/temp/" + UUID.randomUUID() + ".jpg"));
		
		assertThat(multipartFile.getContentType(), is("image/jpeg"));
	}
	
	/**
     * 바이너리 바이트를 스트링으로 변환
     * 
     * @param n
     * @return
     */
    public static String byteToBinaryString(byte n) {
        StringBuilder sb = new StringBuilder("00000000");
        for (int bit = 0; bit < 8; bit++) {
            if (((n >> bit) & 1) > 0) {
                sb.setCharAt(7 - bit, '1');
            }
        }
        return sb.toString();
    }

}
