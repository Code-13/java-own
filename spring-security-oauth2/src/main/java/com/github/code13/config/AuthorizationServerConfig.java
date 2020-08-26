package com.github.code13.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * OAuth2.0 授权服务器配置类
 *
 * @author Code13
 * @date 2020-08-26 15:33
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig implements AuthorizationServerConfigurer {

  @Autowired
  TokenStore tokenStore;

  @Autowired
  ClientDetailsService clientDetailsService;

  @Autowired
  AuthenticationManager authenticationManager;

  @Bean
  public AuthorizationServerTokenServices tokenService() {
    DefaultTokenServices service = new DefaultTokenServices();
    service.setClientDetailsService(this.clientDetailsService);
    service.setSupportRefreshToken(true);
    service.setTokenStore(this.tokenStore);
    service.setAccessTokenValiditySeconds(7200); // 令牌默认有效期2小时
    service.setRefreshTokenValiditySeconds(259200); // 刷新令牌默认有效期3天
    return service;
  }

  @Bean
  public AuthorizationCodeServices authorizationCodeServices() {
    return new InMemoryAuthorizationCodeServices();
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security.tokenKeyAccess("permitAll()")
      .checkTokenAccess("isAuthenticated()")
      .allowFormAuthenticationForClients();
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.inMemory()
      .withClient("client_1")
      .secret("333")
      .scopes("select")
      .resourceIds("rs1")
      .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token")
      .and()
      .withClient("client_2")
      .secret("333")
      .scopes("select")
      .resourceIds("rs1")
      .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token");

    //clients.withClientDetails(this.clientDetailsService);
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints
      .tokenStore(this.tokenStore)
      .authenticationManager(this.authenticationManager)
      .authorizationCodeServices(this.authorizationCodeServices());

  }

}
