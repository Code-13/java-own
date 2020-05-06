package com.code13.designpatterns.chain.filter;

/**
 * @author code13
 * @date 2020/5/6 22:53
 */
public class FilterImpl2 implements Filter {
  @Override
  public void doFilter(FilterChain chain) {
    System.out.println("过滤器2执行前");
    chain.execute();
    System.out.println("过滤器2执行后");
  }
}
