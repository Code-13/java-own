package com.github.code13.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * OAuth2.0 TokenStore 配置
 *
 * @author Code13
 * @date 2020-08-26 15:54
 */
@Configuration
public class TokenStoreConfig {

  @Bean
  public TokenStore tokenStore() {
    return new InMemoryTokenStore();
  }

}
