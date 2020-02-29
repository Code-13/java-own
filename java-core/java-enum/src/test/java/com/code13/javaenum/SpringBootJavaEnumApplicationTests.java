package com.code13.javaenum;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 枚举测试
 *
 * @author code13
 * @date 2020-02-12 21:21
 */
@SpringBootTest
public class SpringBootJavaEnumApplicationTests {

  @Test
  public void test1() {
    String str = "21-1-1-2-201";
    String[] split = str.split("-");

    List<String> list = Lists.newArrayList(split);
    Collections.reverse(list);
    List<String> subList = list.subList(0, 4);

    System.out.println(list);
    System.out.println(subList);
  }

  @Test
  public void test2() {
    String str = "21-1-1-2-201";
    String[] split = str.split("-");

    String[] strings = Arrays.copyOfRange(split, split.length - 4, split.length);

    System.out.println(Arrays.toString(strings));

  }

  @Test
  public void test3() {
    String uniqueId = "21-1-1-2-201";
    String[] split = uniqueId.split("-");
    String[] strings = Arrays.copyOfRange(split, split.length - 4, split.length);

    List<String> list = Lists.newArrayList(strings);

    for (int i = 0; i < list.size(); i++) {
      String s = list.get(i);
      if (i == 0) {
        s = s + "号楼";
      } else if (i == 1) {
        s = s + "单元";
      } else if (i == 2) {
        s = s + "F";
      }
      list.set(i, s);
    }

    System.out.println(list);

    String collect = String.join("-", list);

    System.out.println(collect);
  }

}
