package com.github.code13.designpatterns.chain.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author code13
 * @date 2020/5/6 22:54
 */
public class FilterChainImpl implements FilterChain {

  List<Filter> filters = new ArrayList<>();

  private int index;

  private boolean executeTarget;

  public void append(Filter filter) {
    this.filters.add(filter);
  }

  @Override
  public void execute() {

    if (this.index < this.filters.size()) {
      final Filter filter = this.filters.get(this.index);
      this.index++;
      filter.doFilter(this);
    }

    if (this.index == this.filters.size()) {
      if (!this.executeTarget) {
        this.executeTarget = true;
        System.out.println("执行拦截目标的方法");
      }
    }
  }


}
