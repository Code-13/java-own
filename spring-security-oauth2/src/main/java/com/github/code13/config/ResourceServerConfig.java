package com.github.code13.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author Code13
 * @date 2020-08-26 17:20
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig implements ResourceServerConfigurer {

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    resources.resourceId("rs1");
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    // @formatter:off
    http
      // Since we want the protected resources to be accessible in the UI as well we need
      // session creation to be allowed (it's disabled by default in 2.0.6)
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
      .and()
      .requestMatchers().anyRequest()
      .and()
      .anonymous()
      .and()
      .authorizeRequests()
//                    .antMatchers("/product/**").access("#oauth2.hasScope('select') and hasRole('ROLE_USER')")
      .antMatchers("/order/**").authenticated();// 配置 order 访问控制，必须认证过后才可以访问
    // @formatter:on
  }
}
