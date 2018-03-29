package com.shan.app.web.cms;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuResourceTest {

	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Autowired
	private Filter springSecurityFilterChain;
	
	private MockHttpSession session;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
										.addFilter(springSecurityFilterChain)
										.build();
		
		this.session = (MockHttpSession) mockMvc.perform(formLogin("/cms/login")
														.user("userId", "admin")
														.password("password", "1234"))
												.andExpect(status().is3xxRedirection())
												.andReturn().getRequest().getSession();
	}
	
	@Test
	public void createTest() throws Exception {
		// url 규칙
		// cms url : 현재메뉴 /cms/{menuId}/..., 임의 http://...
		// home url : 현재메뉴 /home/{menuId}/..., 임의 http://...
	
		/*ResultActions result = mockMvc.perform(post("/cms/1/menu")
												.param("name", "시스템관리")
												.param("useYn", "Y")
												.param("menuGubun", "CMS")
												.param("menuType", "LIST")
												.param("cmsUrl", "/cms/{menuId}/system")
												.param("cmsUrlTarget", "_self")
												.param("ord", "1")
												.session(session));*/
		
		FileInputStream fis = new FileInputStream(new File("static/image/test.jpg"));
		
		ResultActions result = mockMvc.perform(fileUpload("/cms/1/menu")
												.file(new MockMultipartFile("test", "test.jpg", MediaType.IMAGE_JPEG_VALUE, fis))
												.param("parentId", "1")
												.param("name", "메뉴관리")
												.param("useYn", "Y")
												.param("menuGubun", "CMS")
												.param("menuType", "URL")
												.param("cmsUrl", "/cms/{menuId}/menu")
												.param("cmsUrlTarget", "_self")
												.param("ord", "1")
												.session(session));
		
		result.andDo(print());
		result.andExpect(status().isCreated());
	}
	
	@Test
	public void createExtensionNotAllowedExceptionTest() throws Exception {
		ResultActions result = mockMvc.perform(fileUpload("/cms/1/menu")
												.file(new MockMultipartFile("image", "test.txt", MediaType.IMAGE_JPEG_VALUE, "Hello World!!".getBytes()))
												.param("parentId", "1")
												.param("name", "메뉴관리")
												.param("useYn", "Y")
												.param("menuGubun", "CMS")
												.param("menuType", "URL")
												.param("cmsUrl", "/cms/{menuId}/menu")
												.param("cmsUrlTarget", "_self")
												.param("ord", "1")
												.session(session));
		
		result.andDo(print());
		result.andExpect(status().isBadRequest());
	}
	
	@Test
	public void createMaxUploadSizeExceededException() throws Exception {
		FileInputStream fis = new FileInputStream(new File("src/main/resources/static/image/test.jpg"));
		MockMultipartFile multipartFile = new MockMultipartFile("test", "test.jpg", "multipart/mixed", fis);
		
		System.out.println("multipartFile = " + multipartFile);
		
		ResultActions result = mockMvc.perform(fileUpload("/cms/1/menu")
												.file(multipartFile)
												.param("parentId", "1")
												.param("name", "메뉴관리")
												.param("useYn", "Y")
												.param("menuGubun", "CMS")
												.param("menuType", "URL")
												.param("cmsUrl", "/cms/{menuId}/menu")
												.param("cmsUrlTarget", "_self")
												.param("ord", "1")
												.session(session));
		
		result.andDo(print());
		result.andExpect(status().isBadRequest());
	}
}
