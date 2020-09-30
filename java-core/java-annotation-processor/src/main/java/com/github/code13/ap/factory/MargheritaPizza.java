package com.github.code13.ap.factory;

import com.github.code13.ap.annotation.Factory;

/**
 * @author Code13
 * @date 2020-09-30 17:23
 */
@Factory(type = Meal.class, id = "Margherita")
public class MargheritaPizza implements Meal {
  @Override
  public float getPrice() {
    return 6.0f;
  }
}
