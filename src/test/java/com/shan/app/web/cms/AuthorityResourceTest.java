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
import com.shan.app.service.cms.dto.AuthorityDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AuthorityResourceTest {

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
		AuthorityDTO.Create create = new AuthorityDTO.Create();
		create.setAuthority("TEST");
		create.setAuthorityName("테스트 권한");
		
		mockMvc.perform(post("/cms/1/authority")
						.session(session)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(create)))
				.andDo(print())
				.andExpect(status().isCreated());
	}
	
	@Test
	public void createDuplicationExceptionTest() throws Exception {
		AuthorityDTO.Create create = new AuthorityDTO.Create();
		create.setAuthority("ADMIN");
		create.setAuthorityName("관리자");
		
		mockMvc.perform(post("/cms/1/authority")
						.session(session)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(create)))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void updateTest() throws Exception {
		AuthorityDTO.Update update = new AuthorityDTO.Update();
		update.setAuthorityName("관리자11");
		
		mockMvc.perform(put("/cms/1/authority/2")
						.session(session)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(update)))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	public void updateNotFoundExceptionTest() throws Exception {
		AuthorityDTO.Update update = new AuthorityDTO.Update();
		update.setAuthorityName("관리자11");
		
		mockMvc.perform(put("/cms/1/authority/0")
						.session(session)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(update)))
				.andDo(print())
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void getAuthoritysTest() throws Exception {
		mockMvc.perform(get("/cms/1/authoritys")
						.session(session))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	public void getAuthorityTest() throws Exception {
		mockMvc.perform(get("/cms/1/authority/2")
						.session(session))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	public void deleteAuthorityTest() throws Exception {
		mockMvc.perform(delete("/cms/1/authority/2")
						.session(session))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
}
