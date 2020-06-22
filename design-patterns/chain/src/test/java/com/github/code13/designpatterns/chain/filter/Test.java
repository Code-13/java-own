package com.github.code13.designpatterns.chain.filter;

/**
 * @author code13
 * @date 2020/5/6 22:59
 */
public class Test {

  @org.junit.jupiter.api.Test
  void test1() {

    FilterChainImpl chain = new FilterChainImpl();

    chain.append(new FilterImpl1());
    chain.append(new FilterImpl2());
    chain.append(new FilterImpl3());

    chain.execute();

  }


}
