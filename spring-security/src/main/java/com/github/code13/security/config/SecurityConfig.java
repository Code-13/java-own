package com.github.code13.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author code13
 * @date 2020/7/18 17:38
 */
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private ObjectMapper objectMapper;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    //http.httpBasic()
    //  .authenticationEntryPoint((request, response, authException) -> {
    //    response.setContentType("application/json; charset=UTF-8");
    //    response.setStatus(401);
    //    Map<String, Object> result = new HashMap<>();
    //    result.put("code", 401);
    //    result.put("message", "未认证");
    //    response.getWriter().write(this.objectMapper.writeValueAsString(result));
    //  })
    //
    //  .and()
    //  .authorizeRequests()
    //  .anyRequest()
    //  .authenticated();

    http.formLogin()
      .successHandler((request, response, authentication) -> System.out.println("hahah"))
      .failureHandler((request, response, exception) -> System.out.println("哈哈哈"))
      .and()
      .authorizeRequests()
      .antMatchers("/rrr")
      .permitAll()
      .anyRequest()
      .authenticated()
      .and()
      .csrf()
      .disable();

  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers(HttpMethod.OPTIONS, "*/**");
  }

}
