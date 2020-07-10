package com.github.code13.mapstruct.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Code13
 * @date 2020-07-09 16:00
 */
@Data
@Accessors(chain = true)
public class CarType {
  private String type;
}
