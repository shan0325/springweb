package com.shan.app.web.cms;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shan.app.domain.Admin;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AdminResourceTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void create() throws Exception {
		Admin admin = new Admin();
		admin.setUserId("manager");
		admin.setPassword("1234");
		
		ResultActions result = mockMvc.perform(post("/cms/admin")
										.contentType(MediaType.APPLICATION_JSON)
										.content(objectMapper.writeValueAsString(admin)));
		
		result.andDo(print());
		result.andExpect(status().isCreated());
	}
	
	@Test
	public void createAdminDuplicatedExceptionTest() throws Exception {
		Admin admin = new Admin();
		admin.setUserId("admin");
		admin.setPassword("1234");
		
		ResultActions result = mockMvc.perform(post("/cms/admin")
										.contentType(MediaType.APPLICATION_JSON)
										.content(objectMapper.writeValueAsString(admin)));
		
		result.andDo(print());
		result.andExpect(status().isBadRequest());
	}
	
	@Test
	public void createValidationTest() throws Exception {
		Admin admin = new Admin();
		admin.setUserId("admin");
		
		ResultActions result = mockMvc.perform(post("/cms/admin")
										.contentType(MediaType.APPLICATION_JSON)
										.content(objectMapper.writeValueAsString(admin)));
		
		result.andDo(print());
		result.andExpect(status().isBadRequest());
	}
	
	
}
