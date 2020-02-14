package com.code13.springbootdatasource.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 自动填充功能
 * <p>
 * https://mp.baomidou.com/guide/auto-fill-metainfo.html
 *
 * @author code13
 * @date 2020-02-15 00:31
 */
@Slf4j
@Component
public class AutoFillObjectHandler implements MetaObjectHandler {

  @Override
  public void insertFill(MetaObject metaObject) {
    log.info("start insert fill ...");

    /* 以下两种方法选择其中一种即可 */

    // 起始版本 3.3.0(推荐使用)
    this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
    // 也可以使用(3.3.0 该方法有bug请升级到之后的版本如`3.3.1.8-SNAPSHOT`)
    //this.fillStrategy(metaObject, "createTime", LocalDateTime.now());
  }

  @Override
  public void updateFill(MetaObject metaObject) {
    log.info("start update fill ....");

    /* 以下两种方法选择其中一种即可 */

    // 起始版本 3.3.0(推荐使用)
    this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
    // 也可以使用(3.3.0 该方法有bug请升级到之后的版本如`3.3.1.8-SNAPSHOT`)
    this.fillStrategy(metaObject, "updateTime", LocalDateTime.now());
  }
}
