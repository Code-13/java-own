package com.github.code13.designpatterns.strategy.demo01;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 回执类
 *
 * @author Code13
 * @date 2020-06-21 20:12
 */
@Data
@AllArgsConstructor
public class Receipt {

  /**
   * 回执信息
   */
  private String message;

  /**
   * 回执类型(`MT1101、MT2101、MT4101、MT8104、MT8105、MT9999`)
   */
  private String type;

}
