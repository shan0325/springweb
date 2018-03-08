package com.shan.app.repository.cms;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.shan.app.domain.Authority;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorityRepositoryTest {

	@Resource(name="cmsAuthorityRepository")
	private AuthorityRepository authorityRepository;
	
	@Test
	public void saveAuthority() {
		Authority authority = new Authority(); 
		authority.setAuthority("ADMIN");
		authority.setAuthorityName("관리자");
		
		Authority newAuthority = authorityRepository.save(authority);
		
		assertThat(newAuthority, is(notNullValue()));
	}
}
