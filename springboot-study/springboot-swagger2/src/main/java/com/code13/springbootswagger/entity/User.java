package com.code13.springbootswagger.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 用户
 *
 * @author code13
 * @date 2020-02-12 17:14
 */
@Data
@ApiModel(description = "用户实体")
public class User {

  @ApiModelProperty("用户编号")
  private Long id;

  @NotBlank
  @ApiModelProperty("用户姓名")
  private String name;

  @NotNull
  @ApiModelProperty("用户年龄")
  private Integer age;

}
