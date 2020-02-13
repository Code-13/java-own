package com.code13.springbootscheduler.quartz.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 任务状态枚举
 *
 * @author code13
 * @date 2020-02-11 22:24
 */
@AllArgsConstructor
@Getter
public enum JobStatusEnum {

  /**
   * 停止
   */
  STOP("0", "停止"),

  /**
   * 运行
   */
  RUNNING("1", "运行");

  @EnumValue
  @JsonValue
  private final String value;

  private final String code;

  public static JobStatusEnum getEnumByKey(String key) {
    for (JobStatusEnum e : JobStatusEnum.values()) {
      if (Objects.equals(e.getValue(), key)) {
        return e;
      }
    }
    return null;
  }
}
