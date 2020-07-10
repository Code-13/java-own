package com.github.code13.mapstruct.base;

import com.github.code13.mapstruct.bean.Car;
import com.github.code13.mapstruct.bean.CarType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * @author Code13
 * @date 2020-07-10 11:48
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BaseConvertTest {

  @Autowired
  CarConverter carConverter;

  @Test
  public void test1() {
    final Car car = new Car();
    car.setMake("德意志");
    car.setNumberOfSeats(5);
    car.setType(new CarType().setType("奔驰"));
    final Map<String, Object> stringObjectMap = this.carConverter.doToMap(car);
    System.out.println(stringObjectMap);
    assertTrue(true);

  }

}
