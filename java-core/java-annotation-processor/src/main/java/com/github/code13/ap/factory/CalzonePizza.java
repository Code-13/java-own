package com.github.code13.ap.factory;

import com.github.code13.ap.annotation.Factory;

/**
 * @author Code13
 * @date 2020-09-30 17:24
 */
@Factory(type = Meal.class, id = "Calzone")
public class CalzonePizza implements Meal {
  @Override
  public float getPrice() {
    return 8.5f;
  }
}
