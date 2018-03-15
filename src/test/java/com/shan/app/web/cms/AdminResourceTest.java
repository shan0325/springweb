package com.shan.app.web.cms;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shan.app.service.cms.dto.AdminDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AdminResourceTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Autowired
	private FilterChainProxy springSecurityFilterChain;
	
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
	public void create() throws Exception {
		AdminDTO.Create admin = new AdminDTO.Create();
		admin.setUserId("test");
		admin.setPassword("1234");
		admin.setName("테스트");
		Set<String> authorities = new HashSet<>();
		authorities.add("ADMIN");
		admin.setAuthorities(authorities);
		
		ResultActions result = mockMvc.perform(post("/cms/admin")
										.session(session)
										.contentType(MediaType.APPLICATION_JSON)
										.content(objectMapper.writeValueAsString(admin)));
		
		result.andDo(print());
		result.andExpect(status().isCreated());
	}
	
	@Test
	public void createAdminDuplicatedExceptionTest() throws Exception {
		AdminDTO.Create admin = new AdminDTO.Create();
		admin.setUserId("admin");
		admin.setPassword("1234");
		Set<String> authorities = new HashSet<>();
		authorities.add("ADMIN");
		admin.setAuthorities(authorities);
		
		ResultActions result = mockMvc.perform(post("/cms/admin")
										.contentType(MediaType.APPLICATION_JSON)
										.content(objectMapper.writeValueAsString(admin)));
		
		result.andDo(print());
		result.andExpect(status().isBadRequest());
	}
	
	@Test
	public void createValidationTest() throws Exception {
		AdminDTO.Create admin = new AdminDTO.Create();
		admin.setUserId("admin");
		
		ResultActions result = mockMvc.perform(post("/cms/admin")
										.contentType(MediaType.APPLICATION_JSON)
										.content(objectMapper.writeValueAsString(admin)));
		
		result.andDo(print());
		result.andExpect(status().isBadRequest());
	}
	
	@Test
	public void createHttpMessageNotReadableTest() throws Exception {
		ResultActions result = mockMvc.perform(post("/cms/admin")
										.contentType(MediaType.APPLICATION_JSON)
										.content(objectMapper.writeValueAsString(Arrays.asList("admin"))));
		
		result.andDo(print());
		result.andExpect(status().isBadRequest());
	}
	
	
	
}
