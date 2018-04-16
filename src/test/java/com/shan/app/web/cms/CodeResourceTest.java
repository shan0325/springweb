package com.shan.app.web.cms;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shan.app.service.cms.dto.CodeDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CodeResourceTest {
	
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
	public void createTest() throws Exception {
		CodeDTO.Create create = new CodeDTO.Create();
		create.setGroupCodeId(2L);
		create.setCode("TEST");
		create.setCodeName("테스트코드");
		create.setOrd(5);
		create.setUseYn("Y");
		
		mockMvc.perform(post("/cms/1/code")
						.session(session)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(create)))
				.andDo(print())
				.andExpect(status().isCreated());
	}
	
	@Test
	public void updateTest() throws Exception {
		CodeDTO.Update update = new CodeDTO.Update();
		update.setCodeName("컨텐츠22");
		update.setOrd(3);
		update.setUseYn("N");
		
		mockMvc.perform(put("/cms/1/code/7")
						.session(session)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(update)))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	public void getCodes() throws Exception {
		mockMvc.perform(get("/cms/1/codes").session(session))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	public void getCode() throws Exception {
		mockMvc.perform(get("/cms/1/code/1").session(session))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	public void deleteCode() throws Exception {
		mockMvc.perform(delete("/cms/1/code/1").session(session))
				.andDo(print())
				.andExpect(status().isOk());
	}

}
