package com.shan.app.web.cms;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shan.app.service.cms.dto.MenuDTO;

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
		//url 규칙
		//   /cms/{메뉴번호}/admin
		
		MenuDTO.Create create = new MenuDTO.Create();
		create.setName("시스템관리");
		create.setUseYn("Y");
		create.setMenuGubun("CMS");
		create.setMenuType("LIST");
		create.setOrd(1);
		
		ResultActions result = mockMvc.perform(post("/cms/1/menu")
												.session(session)
												.contentType(MediaType.APPLICATION_JSON)
												.content(objectMapper.writeValueAsString(create)));
		
		result.andDo(print());
		result.andExpect(status().isCreated());
	}
}
