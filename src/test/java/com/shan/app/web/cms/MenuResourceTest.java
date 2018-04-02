package com.shan.app.web.cms;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.Filter;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MenuResourceTest {

	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Autowired
	private Filter springSecurityFilterChain;
	
	private MockHttpSession session;
	
	private MockMultipartFile multipartFile;
	
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
		
		File file = new File("src/main/resources/static/image/test.jpg");
		FileInputStream fis = new FileInputStream(file);
		multipartFile = new MockMultipartFile("image", file.getName(), MediaType.IMAGE_JPEG_VALUE, fis);
	}
	
	@Test
	public void createMenuTest() throws Exception {
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
		
		ResultActions result = mockMvc.perform(fileUpload("/cms/1/menu")
												.param("parentId", "1")
												.param("name", "코드관리")
												.param("useYn", "Y")
												.param("menuGubun", "CMS")
												.param("menuType", "URL")
												.param("cmsUrl", "/cms/{menuId}/code")
												.param("cmsUrlTarget", "_self")
												.param("ord", "3")
												.session(session));
		
		result.andDo(print());
		result.andExpect(status().isCreated());
	}
	
	@Test
	public void createMenuExtensionNotAllowedExceptionTest() throws Exception {
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
	public void createMenuMaxUploadSizeExceededException() throws Exception {
		File file = new File("src/main/resources/static/image/test2.jpg");
		FileInputStream fis = new FileInputStream(file);
		MockMultipartFile multipartFile = new MockMultipartFile("image", file.getName(), MediaType.IMAGE_JPEG_VALUE, fis);
		
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
	
	@Test
	public void createMenuValidationTest() throws Exception {
		ResultActions result = mockMvc.perform(fileUpload("/cms/1/menu")
												.file(this.multipartFile)
												.param("parentId", "1")
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
	public void updateMenuTest() throws Exception {
		mockMvc.perform(fileUpload("/cms/1/menu/8")
						.file(this.multipartFile)
						.param("name", "메뉴관리22")
						.param("useYn", "Y")
						.param("menuGubun", "ALL")
						.param("menuType", "URL")
						.param("cmsUrl", "/cms/{menuId}/menu")
						.param("cmsUrlTarget", "_blank")
						.param("homeUrl", "/cms/{menuId}/menu")
						.param("homeUrlTarget", "_blank")
						.param("ord", "2")
						.session(session))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	public void getMenusTest() throws Exception {
		mockMvc.perform(get("/cms/1/menus")
						.session(session))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	public void getMenuTest() throws Exception {
		mockMvc.perform(get("/cms/1/menu/1")
						.session(session))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	public void getMenuEntityNotFoundExceptionTest() throws Exception {
		mockMvc.perform(get("/cms/1/menu/0")
						.session(session))
				.andDo(print())
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void deleteMenuTest() throws Exception {
		mockMvc.perform(delete("/cms/1/menu/6")
						.session(session))
				.andDo(print())
				.andExpect(status().isOk());
	}
}
