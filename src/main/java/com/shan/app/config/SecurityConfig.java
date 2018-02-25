package com.shan.app.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/**/*.css",  "/script/**", "/**/*.js",  "image/**", "/fonts/**", "lib/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/cms").hasRole("ADMIN")
			.and()
			.formLogin()
				.loginPage("/cms")
				.loginProcessingUrl("/cms")
				.defaultSuccessUrl("/cms")
				.failureUrl("/cms")
			.and()
			.logout();
	}
	
}
