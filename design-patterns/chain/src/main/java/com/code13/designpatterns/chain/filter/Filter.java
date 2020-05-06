package com.code13.designpatterns.chain.filter;

/**
 * 过滤器
 *
 * @author code13
 * @date 2020/5/6 22:51
 */
public interface Filter {

  void doFilter(FilterChain chain);

}
