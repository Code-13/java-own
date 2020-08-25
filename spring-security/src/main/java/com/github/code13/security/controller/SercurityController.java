package com.github.code13.security.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author code13
 * @date 2020/7/18 17:56
 */
@RestController
public class SercurityController {

  @PostMapping("/test")
  public String test() {
    return "测试";
  }

}
