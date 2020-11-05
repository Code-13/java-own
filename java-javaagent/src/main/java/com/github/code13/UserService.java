package com.github.code13;

import java.util.concurrent.TimeUnit;

/**
 * @author code13
 * @date 2020/10/31 11:01
 */
public class UserService {

  public void sayHello(String name) {
    try {
      TimeUnit.MILLISECONDS.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("名字是：" + name);
  }

  public void gender(String gender) {
    try {
      TimeUnit.MILLISECONDS.sleep(200);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("性别是：" + gender);
  }

  public int getAge(int age) {
    try {
      TimeUnit.MILLISECONDS.sleep(200);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("年龄是：" + age);
    return age;
  }

}
