package com.github.code13.springbootconfiguration.controller;

import com.github.code13.springbootconfiguration.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Code13
 */
@RestController
public class HelloController {

  @Autowired
  Person person;

  @Value("${code13.num}")
  Object o;

  @GetMapping("/hello")
  public Object hello() {
    return o;
  }

}
