package com.github.code13.mapstruct.bean;

import lombok.Data;

/**
 * @author Code13
 * @date 2020-07-09 15:49
 */
@Data
public class Car {
  private String make;
  private int numberOfSeats;
  private CarType type;
}
