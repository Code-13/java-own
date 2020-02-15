package com.code13.springbootdatasource;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.BeetlTemplateEngine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

/**
 * 自动代码生成器测试
 *
 * @author code13
 * @date 2020-02-15 19:22
 */
@SpringBootTest
public class SpringBootDataSourceApplicationTests {

  @Test
  public void execute() {
    autoGenerator("yun_user");
  }

  public void autoGenerator(String... tables) {

    // 代码生成器
    AutoGenerator generator = new AutoGenerator();
    generator.setTemplateEngine(new BeetlTemplateEngine());

    // 全局配置
    GlobalConfig globalConfig = new GlobalConfig();
    globalConfig.setOutputDir(System.getProperty("user.dir") + "\\src\\main\\java");
    globalConfig.setAuthor("code13");
    globalConfig.setSwagger2(false);
    globalConfig.setOpen(false);
    globalConfig.setFileOverride(true);
    globalConfig.setEnableCache(false);
    globalConfig.setActiveRecord(true);
    globalConfig.setBaseColumnList(true);
    globalConfig.setDateType(DateType.TIME_PACK);
    globalConfig.setEntityName(null);
    globalConfig.setMapperName("%sMapper");
    globalConfig.setXmlName("%sMapper");
    globalConfig.setServiceName("%sService");
    globalConfig.setServiceImplName("%sServiceImpl");
    globalConfig.setIdType(IdType.ASSIGN_ID);
    generator.setGlobalConfig(globalConfig);

    //数据源配置
    DataSourceConfig dataSourceConfig = new DataSourceConfig();
    dataSourceConfig.setDbType(DbType.MYSQL);
    //dataSourceConfig.setUrl("jdbc:mysql://127.0.0.1:3306/tjyun?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false&serverTimezone=CTT&allowPublicKeyRetrieval=true");
    dataSourceConfig.setUrl("jdbc:mysql://rm-bp11bdf19z4m594yrjo.mysql.rds.aliyuncs.com:3306/tjyun_test?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false&serverTimezone=CTT");
    dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
    dataSourceConfig.setUsername("tjyk");
    dataSourceConfig.setPassword("tjyk!2019");
    generator.setDataSource(dataSourceConfig);

    PackageConfig packageConfig = new PackageConfig()
      .setParent("com.code13.springbootdatasource")
      //.setModuleName("tjyun")
      .setController("controller")
      .setEntity("entity")
      .setService("service")
      .setServiceImpl("service.impl")
      .setMapper("mapper")
      .setXml("mapper");
    generator.setPackageInfo(packageConfig);

    StrategyConfig strategyConfig = new StrategyConfig()
      .setCapitalMode(true)
      .setSkipView(true)
      .setNaming(NamingStrategy.underline_to_camel)
      .setColumnNaming(NamingStrategy.underline_to_camel)
      //.setTablePrefix("")
      //.setFieldPrefix("")
      .setInclude(tables)
      //.setEntityColumnConstant(true)
      .setEntityBuilderModel(true)
      .setEntityLombokModel(true)
      .setEntityBooleanColumnRemoveIsPrefix(true)
      .setEntityTableFieldAnnotationEnable(true)
      .setVersionFieldName("version")
      .setLogicDeleteFieldName("delFlag")
      .setRestControllerStyle(true)
      .setControllerMappingHyphenStyle(false);

    // 公共字段填充
    ArrayList<TableFill> tableFills = new ArrayList<>();
    tableFills.add(new TableFill("CREATE_TIME", FieldFill.INSERT));
    tableFills.add(new TableFill("UPDATE_TIME", FieldFill.UPDATE));
    tableFills.add(new TableFill("CREATE_USER", FieldFill.INSERT));
    tableFills.add(new TableFill("UPDATE_USER", FieldFill.UPDATE));
    strategyConfig.setTableFillList(tableFills);


    generator.setStrategy(strategyConfig);

    generator.execute();
  }

}
