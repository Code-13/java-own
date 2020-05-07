package com.code13.designpatterns.chain.lambda;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

/**
 * @author code13
 * @date 2020/5/7 22:50
 */
public class LambdaHandlerTest {

  @Test
  public void test() {
    Function<String, String> addHeaderHandler = LambdaHandler.addHeaderHandler();
    Function<String, String> checkSpellHandler = LambdaHandler.checkSpellHandler();
    Function<String, String> addFooterHandler = LambdaHandler.addFooterHandler();
    String test = addHeaderHandler.andThen(checkSpellHandler).andThen(addFooterHandler).apply("labda");
    System.out.println(test);
  }

}
