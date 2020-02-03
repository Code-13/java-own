package com.code13.springbootconfiguration.bean;

import com.code13.springbootconfiguration.facroty.YamlPropertySourceFactor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * 注解 {@code @ConfigurationProperties}:告诉springBoot将本类中的所有属性和配置文件中相关的配置进行绑定
 *    prefix 配置文件中的那个属性进行一一映射
 *    只有这个组件是容器中的组件，才能使用
 *    支持JSR303校验
 *
 * 注解{@code @PropertySource}加载外部配置文件中的配置
 *    默认只能加载 .properties文件，指定 factory 后可以加载yaml文件
 *
 * @author Code13
 */
@Data
@Component
@PropertySource(value = "classpath:config/person.yml",encoding = "utf-8",factory = YamlPropertySourceFactor.class)
@ConfigurationProperties(prefix = "person")
@Validated
public class Person {


  private String lastName;

  private Integer age;

  private Boolean boss;

  /**
   * 出生日期
   * 必须是一个过去的日期
   */
  @Past
  private LocalDate birth;

  private Map<String, Object> map;

  private List<Object> list;

  private Dog dog;
}
