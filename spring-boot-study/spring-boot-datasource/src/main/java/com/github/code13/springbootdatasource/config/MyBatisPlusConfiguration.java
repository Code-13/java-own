package com.github.code13.springbootdatasource.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * mybatis-plus配置类
 *
 * @author code13
 * @date 2020-02-15 00:40
 */
@MapperScan("com.github.code13.springbootdatasource.mapper*")
@EnableTransactionManagement
@Configuration
public class MyBatisPlusConfiguration {

  /**
   * 分页插件
   * https://mp.baomidou.com/guide/page.html
   */
  @Bean
  public PaginationInterceptor paginationInterceptor() {
    PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
    // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
    // paginationInterceptor.setOverflow(false);
    // 设置最大单页限制数量，默认 500 条，-1 不受限制
    // paginationInterceptor.setLimit(500);
    // 开启 count 的 join 优化,只针对部分 left join
    paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
    return paginationInterceptor;
  }

  /**
   * 乐观锁插件
   * https://mp.baomidou.com/guide/optimistic-locker-plugin.html#_2-%E6%B3%A8%E8%A7%A3%E5%AE%9E%E4%BD%93%E5%AD%97%E6%AE%B5-version-%E5%BF%85%E9%A1%BB%E8%A6%81
   *
   * <pre>
   * 意图：
   * 当要更新一条记录的时候，希望这条记录没有被别人更新
   *
   * 乐观锁实现方式：
   *     取出记录时，获取当前version
   *     更新时，带上这个version
   *     执行更新时， set version = newVersion where version = oldVersion
   *     如果version不对，就更新失败
   * </pre>
   * <p>
   * 注解实体字段 @Version 必须要!
   * 整数类型下 newVersion = oldVersion + 1
   * 仅支持 updateById(id) 与 update(entity, wrapper) 方法
   * 支持的数据类型只有:int,Integer,long,Long,Date,Timestamp,LocalDateTime
   * newVersion 会回写到 entity 中
   */
  @Bean
  public OptimisticLockerInterceptor optimisticLockerInterceptor() {
    return new OptimisticLockerInterceptor();
  }

}
