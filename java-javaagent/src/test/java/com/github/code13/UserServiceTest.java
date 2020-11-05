package com.github.code13;

import org.junit.Before;
import org.junit.Test;

/**
 * @author code13
 * @date 2020/10/31 11:15
 */
public class UserServiceTest {

  UserService userService;

  @Before
  public void before() {
    userService = new UserService();
  }

  @Test
  public void sayHello() {
    userService.sayHello("懂王");
  }
  
}