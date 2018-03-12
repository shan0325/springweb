package com.shan.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_oauth_client_details")
public class OauthClientDetails {
	
	@Id
	@Column(name = "client_id", nullable = false)
	private String clientId;
	
	@Column(name = "resource_ids")
	private String resourceIds;
	
	@Column(name = "client_secret")
	private String clientSecret;
	
	private String scope;
	
	@Column(name = "authorized_grant_types")
	private String authorizedGrantTypes;
	
	@Column(name = "web_server_redirect_uri")
	private String webServerRedirectUri;
	
	private String authorities;
	
	@Column(name = "access_token_validity")
	private Integer accessTokenValidity;
	
	@Column(name = "refresh_token_validity")
	private Integer refreshTokenValidity;
	
	@Column(name = "additional_information", length = 4096)
	private String additionalInformation;
	
	private String autoapprove;
}
