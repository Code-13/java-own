package com.github.code13.mapstruct.base;

import com.alibaba.fastjson.JSON;
import com.google.common.reflect.TypeToken;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 基础转换类
 * <p>
 * 主要用于 领域对象 与 持久化对象之间的转换
 *
 * <pre>
 * 1.SOURCE与TARGET中属性名相同的默认映射(如这两个都有name属性)
 * 2.SOURCE与TARGET中属性名不同的，需要通过 {@code @Mappings({@Mappings()}} 明确关系来映射 (如 sex 对应 gender)
 * 3.形成映射关系的属性类型不同的,需要通过表达式转换数据类型类型(如Date对应String)
 * 4.无映射关系属性被忽略(如UserEntity的password)
 * </pre>
 * 对象名称规范
 * <pre>
 * DO   domain object         领域对象
 * PO   persistent Object     持久化对象
 * TO   Transfer Object       数据传输对象
 * DTO  Data Transfer Object  数据传输对象
 * VO   view object           值对象
 * BO   business object       业务对象
 * POJO plain ordinary java object 简单无规则 java 对象
 * </pre>
 *
 * @param <D> SOURCE DO
 * @param <P> TARGET PO
 * @author Code13
 * @date 2020-07-10 09:59
 */
@SuppressWarnings({"unchecked"})
public interface BaseConverter<D, P> extends ObjectConverter<D, P> {

  /**
   * 映射同名属性
   *
   * @param source D
   * @return {@link P} TARGET
   */
  P doToPo(D source);

  /**
   * 反向同名属性映射
   *
   * @param target P
   * @return {@link D} SOURCE
   */
  @InheritInverseConfiguration(name = "doToPo")
  D poToDo(P target);

  /**
   * 映射同名属性，集合形式
   *
   * @param sources SOURCE集合
   * @return {@link List< P >} TARGET 集合
   */
  @InheritConfiguration(name = "doToPo")
  List<P> doToPo(List<D> sources);

  /**
   * 反向 映射同名属性，集合形式
   *
   * @param targets TARGET集合
   * @return {@link List< D >} SOURCE集合
   */
  @InheritConfiguration(name = "poToDo")
  List<D> poToDo(List<P> targets);

  /**
   * 更新属性
   *
   * @param source DO
   * @param target PO
   */
  void updateDoToPo(D source, @MappingTarget P target);


  /**
   * 反向更新属性
   *
   * @param source DO
   * @param target PO
   */
  void updatePoToDo(@MappingTarget D source, P target);

  /**
   * 映射同名属性，集合流形式
   *
   * @param stream SOURCE流
   * @return {@link List< P >} TARGET
   */
  List<P> doToPo(Stream<D> stream);

  /**
   * 将 Map 转换为 持久化对象
   *
   * @param map Map
   * @return {@link P} 持久化对象
   */
  default P mapToPo(Map<String, Object> map) {
    try {
      TypeToken<P> poType = new TypeToken<>() {
      };
      Class<P> rawType = (Class<P>) poType.getRawType();

      final String s = JSON.toJSONString(map);
      return JSON.parseObject(s, rawType);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  /**
   * 将持久化对象转化为Map
   *
   * @param target PO
   * @return {@link Map<String,Object>}
   */
  default Map<String, Object> poToMap(P target) {
    final String s = JSON.toJSONString(target);
    return JSON.parseObject(s, Map.class);
  }

  /**
   * 持久化集合转换为 Map集合
   *
   * @param targets PO List
   * @return {@link List<Map<String,Object>>}
   */
  default List<Map<String, Object>> poListToMapList(List<P> targets) {
    return targets.stream().map(this::poToMap).collect(Collectors.toList());
  }

  /**
   * Map集合转换为 List
   *
   * @param mapList mapList
   * @return {@link List<P>} List
   */
  default List<P> mapListToPoList(List<Map<String, Object>> mapList) {
    final String s = JSON.toJSONString(mapList);
    try {
      final TypeToken<P> doType = new TypeToken<>(this.getClass()) {
      };
      final Class<P> rawType = (Class<P>) doType.getRawType();
      return JSON.parseArray(s, rawType);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
