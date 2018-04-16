package com.shan.app.web.cms;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shan.app.domain.Menu;
import com.shan.app.service.cms.MenuService;
import com.shan.app.service.cms.dto.MenuDTO;

@RestController("cmsMenuResource")
@RequestMapping("/cms/{menuId}")
public class MenuResource {
	
	private final Logger logger = LoggerFactory.getLogger(MenuResource.class);
	
	@Resource(name = "cmsMenuService")
	private MenuService menuService;
	
	@PostMapping("/menu")
	public ResponseEntity<Object> createMenu(HttpServletRequest request,
			@ModelAttribute @Valid MenuDTO.Create create) throws Exception {
		
		Menu newMenu = menuService.createMenu(request, create);
		return new ResponseEntity<>(newMenu, HttpStatus.CREATED);
	}
	
	@PostMapping("/menu/{id}")
	public ResponseEntity<Object> updateMenu(HttpServletRequest request,
			@PathVariable Long id, @ModelAttribute @Valid MenuDTO.Update update) throws Exception {
		
		Menu updatedMenu = menuService.updateMenu(request, id, update);
		return new ResponseEntity<>(updatedMenu, HttpStatus.OK);
	}
	
	@GetMapping("/menus")
	public ResponseEntity<Object> getMenus() {
		
		List<Menu> menus = menuService.getMenus();
		return new ResponseEntity<>(menus, HttpStatus.OK);
	}
	
	@GetMapping("/menu/{id}")
	public ResponseEntity<Object> getMenu(@PathVariable Long id) {
		
		Menu menu = menuService.getMenu(id);
		return new ResponseEntity<>(menu, HttpStatus.OK);
	}
	
	@DeleteMapping("/menu/{id}")
	public ResponseEntity<Object> deleteMenu(@PathVariable Long id) {
		
		menuService.deleteMenu(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	

}
