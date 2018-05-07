package com.shan.app.repository.cms;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.shan.app.domain.Menu;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuRepositoryTest {

	@Resource(name="cmsMenuRepository")
	private MenuRepository menuRepository;
	
	@Test
	public void findQueryDslHierarchicalMenuByIdTest() {
		List<Menu> menus = menuRepository.findQueryDslHierarchicalMenuById(1L);
		
		menus.forEach(menu -> {
			System.out.println(menu);
		});
	}
}
