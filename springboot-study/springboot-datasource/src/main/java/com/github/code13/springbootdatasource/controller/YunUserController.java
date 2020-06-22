package com.github.code13.springbootdatasource.controller;


import com.github.code13.springbootdatasource.entity.YunUser;
import com.github.code13.springbootdatasource.service.YunUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 云客应用管理员表 前端控制器
 * </p>
 *
 * @author code13
 * @since 2020-02-15
 */
@RestController
@RequestMapping("/yunUser")
public class YunUserController {

  @Autowired
  private YunUserService yunUserService;

  @GetMapping("listMaster")
  public List<YunUser> getYunUser() {
    return yunUserService.list();
  }

}
