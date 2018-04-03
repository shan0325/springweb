package com.shan.app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	private final Logger logger = LoggerFactory.getLogger(ResourceServerConfig.class);

	@Value("${resource.id:spring-boot-application}")
	private String resourceId;
	
	@Value("${security.oauth2.resource.jwt.key-value}")
	private String publicKey;
	
	@Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }
	
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {		
		logger.info("publicKey : " + publicKey);
		
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		//converter.setSigningKey("secret");
		converter.setVerifierKey(publicKey);
		return converter;
	}
	
	@Bean
	@Primary
	public DefaultTokenServices tokenService() {
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		defaultTokenServices.setSupportRefreshToken(true);
		return defaultTokenServices;
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/jwttest/**").hasRole("ADMIN");
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {		
		resources.resourceId(resourceId);
	}
}
