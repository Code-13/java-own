package com.code13.designpatterns.chain.lambda;

import java.util.function.Function;

/**
 * @author code13
 * @date 2020/5/7 22:44
 */
public class LambdaHandler {

  public static Function<String, String> addHeaderHandler() {
    return s -> "From Raoul, Mario and Alan " + s;
  }

  public static Function<String, String> checkSpellHandler() {
    return s -> s.replace("labda", "lambda");
  }

  public static Function<String, String> addFooterHandler() {
    return s -> s + " Kind regard";
  }

}


