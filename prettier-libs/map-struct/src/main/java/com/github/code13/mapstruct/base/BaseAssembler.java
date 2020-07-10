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
 * 基础转换工
 * <p>
 * 主要用于 领域对象 与 数据传输对象 之间的转换
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
 * @param <D> SOURCE DO  领域对象
 * @param <T> TARGET DTO 数据传输对象
 * @author Code13
 * @date 2020-07-10 09:59
 */
@SuppressWarnings({"unchecked"})
public interface BaseAssembler<D, T> extends ObjectConverter<D, T> {

  /**
   * 映射同名属性
   *
   * @param source D
   * @return {@link T} TARGET
   */
  T doToDto(D source);

  /**
   * 反向同名属性映射
   *
   * @param target T
   * @return {@link D} SOURCE
   */
  @InheritInverseConfiguration(name = "doToDto")
  D dtoToDo(T target);

  /**
   * 映射同名属性，集合形式
   *
   * @param sources SOURCE集合
   * @return {@link List <T>} TARGET 集合
   */
  @InheritConfiguration(name = "doToDto")
  List<T> doToDto(List<D> sources);

  /**
   * 反向 映射同名属性，集合形式
   *
   * @param targets TARGET集合
   * @return {@link List< D >} SOURCE集合
   */
  @InheritConfiguration(name = "dtoToDo")
  List<D> dtoToDo(List<T> targets);

  /**
   * 更新属性
   *
   * @param source DO
   * @param target PO
   */
  void updateDoToDto(D source, @MappingTarget T target);


  /**
   * 反向更新属性
   *
   * @param source DO
   * @param target PO
   */
  void updateDtoToDo(@MappingTarget D source, T target);

  /**
   * 映射同名属性，集合流形式
   *
   * @param stream SOURCE流
   * @return {@link List<T>} TARGET
   */
  List<T> doToDto(Stream<D> stream);

  /**
   * Map 转换 DTO
   *
   * @param map Map
   * @return {@link T} DTO
   */
  default T mapToDto(Map<String, Object> map) {
    try {
      TypeToken<T> poType = new TypeToken<>() {
      };
      Class<T> rawType = (Class<T>) poType.getRawType();

      final String s = JSON.toJSONString(map);
      return JSON.parseObject(s, rawType);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * DTO 转化为 Map
   *
   * @param target DTO
   * @return {@link Map<String,Object>}
   */
  default Map<String, Object> dtoToMap(T target) {
    final String s = JSON.toJSONString(target);
    return JSON.parseObject(s, Map.class);
  }

  /**
   * DTO集合转化为 Map 集合
   *
   * @param targets DTO集合
   * @return {@link List<Map<String,Object>>}
   */
  default List<Map<String, Object>> dtoListToMapList(List<T> targets) {
    return targets.stream().map(this::dtoToMap).collect(Collectors.toList());
  }

  /**
   * Map集合转化为 DTO集合
   *
   * @param mapList Map集合
   * @return {@link List<T>}
   */
  default List<T> mapListToDtoList(List<Map<String, Object>> mapList) {
    final String s = JSON.toJSONString(mapList);
    try {
      final TypeToken<T> doType = new TypeToken<>(this.getClass()) {
      };
      final Class<T> rawType = (Class<T>) doType.getRawType();
      return JSON.parseArray(s, rawType);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
