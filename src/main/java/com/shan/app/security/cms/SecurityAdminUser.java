package com.shan.app.security.cms;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.shan.app.domain.Admin;
import com.shan.app.domain.Authority;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SecurityAdminUser extends User {
	
	private static final String ROLE_PREFIX = "ROLE_";
	private static final long serialVersionUID = 1L;
	
	public SecurityAdminUser(Admin admin) {
		super(admin.getUserId(), admin.getPassword(), makeGrantedAuthority(admin.getAuthorities()));
	}
	
	public static Set<GrantedAuthority> makeGrantedAuthority(Set<Authority> authoritys) {
		Set<GrantedAuthority> list = new HashSet<>();
		authoritys.forEach(authority -> {
			list.add(new SimpleGrantedAuthority(ROLE_PREFIX + authority.getAuthority()));
		});
		return list;
	}
}
