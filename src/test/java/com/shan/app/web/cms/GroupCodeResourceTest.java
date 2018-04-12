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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shan.app.service.cms.dto.GroupCodeDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class GroupCodeResourceTest {

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
		GroupCodeDTO.Create create = new GroupCodeDTO.Create();
		create.setGroupCode("MENUTYPE");
		create.setGroupCodeName("메뉴타입");
		create.setUseYn("Y");
		
		ResultActions result = mockMvc.perform(post("/cms/1/group-code")
										.session(session)
										.contentType(MediaType.APPLICATION_JSON)
										.content(objectMapper.writeValueAsString(create)));
		
		result.andDo(print());
		result.andExpect(status().isCreated());
	}
	
	@Test
	public void updateTest() throws Exception {
		GroupCodeDTO.Update update = new GroupCodeDTO.Update();
		update.setGroupCodeName("메뉴타입22");
		update.setUseYn("N");
		
		ResultActions result = mockMvc.perform(put("/cms/1/group-code/2")
										.session(session)
										.contentType(MediaType.APPLICATION_JSON)
										.content(objectMapper.writeValueAsString(update)));
		
		result.andDo(print());
		result.andExpect(status().isOk());
	}
	
	@Test
	public void getGroupCodes() throws Exception {
		mockMvc.perform(get("/cms/1/group-codes")
						.session(session))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	public void getGroupCode() throws Exception {
		mockMvc.perform(get("/cms/1/group-code/1")
						.session(session))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	public void deleteGroupCode() throws Exception {
		mockMvc.perform(delete("/cms/1/group-code/1")
						.session(session))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
}
