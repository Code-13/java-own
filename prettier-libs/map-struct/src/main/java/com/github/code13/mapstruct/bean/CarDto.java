package com.github.code13.mapstruct.bean;

import lombok.Data;

/**
 * @author Code13
 * @date 2020-07-09 15:49
 */
@Data
public class CarDto {
  //private String make;
  //private int seatCount;
  //private String type;
  private String make;
  private int numberOfSeats;
  private CarType type;
}
