package com.shan.app.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.shan.app.security.CustomLoginSuccessHandler;
import com.shan.app.security.cms.CustomUserDetailsService;

@EnableWebSecurity
public class SecurityConfig {
	
	@Configuration
	@Order(1)
	public static class App1ConfigurationAdapter extends WebSecurityConfigurerAdapter {
		
		@Resource(name="cmsCustomUserDetailsService")
		private CustomUserDetailsService customUserDetailsService;
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
		}
		
		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers("/css/**", "/**/*.css",  "/script/**", "/**/*.js",  "image/**", "/fonts/**", "lib/**");
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/cms/**")
					.authorizeRequests()
						.antMatchers("/cms/login").permitAll()
						.antMatchers("/cms/**").authenticated()
				.and()
				.formLogin()
					.loginPage("/cms/login")
					.usernameParameter("userId")
					.passwordParameter("password")
					.loginProcessingUrl("/cms/login")
					.defaultSuccessUrl("/cms/main")
					.successHandler(successHandler())
					.failureUrl("/cms/login")
				.and()
				.logout();
		}
		
		@Bean
		public AuthenticationSuccessHandler successHandler() {
			return new CustomLoginSuccessHandler("/cms/main");
		}
		
		@Bean
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}
	}
	
	@Configuration
	@Order(2)
	public static class App2ConfigurationAdapter extends WebSecurityConfigurerAdapter {

		@Resource(name="homeCustomUserDetailsService")
		private com.shan.app.security.home.CustomUserDetailsService customUserDetailsService;
		
		@Autowired
		private PasswordEncoder passwordEncoder;
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
		}
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
					.antMatchers("/home/login").permitAll()
					.antMatchers("/home/**").authenticated()
				.and()
				.formLogin()
					.loginPage("/home/login")
					.usernameParameter("userId")
					.passwordParameter("password")
					.loginProcessingUrl("/home/login")
					.defaultSuccessUrl("/home/main")
					.failureUrl("/home/login")
				.and()
				.logout();
		}
	}
	
}
