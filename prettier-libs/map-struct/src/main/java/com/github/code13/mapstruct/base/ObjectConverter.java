package com.github.code13.mapstruct.base;

import com.alibaba.fastjson.JSON;
import com.google.common.reflect.TypeToken;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 对象转换接口
 *
 * @param <S> SOURCE
 * @param <T> TARGET
 * @author Code13
 * @date 2020-07-10 14:31
 */
@SuppressWarnings({"unchecked"})
public interface ObjectConverter<S, T> {

  /**
   * 将map转化为SOURCE DO
   *
   * @param map map
   * @return {@link S}
   */
  default S mapToDo(Map<String, Object> map) {
    try {
      final TypeToken<S> doToken = new TypeToken<>(this.getClass()) {
      };
      Class<S> rawType = (Class<S>) doToken.getRawType();
      final String s = JSON.toJSONString(map);
      return JSON.parseObject(s, rawType);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * 将SOURCE 转化为 Map
   *
   * @param source source
   * @return {@link Map <String,Object>}
   */
  default Map<String, Object> doToMap(S source) {
    final String string = JSON.toJSONString(source);
    return JSON.parseObject(string, Map.class);
  }

  /**
   * 将 SOURCE 集合转换为Map集合
   *
   * @param sources SOURCE
   * @return {@link List< Map< String, Object>>} 集合
   */
  default List<Map<String, Object>> listDoToListMap(List<S> sources) {
    return sources.stream().map(this::doToMap).collect(Collectors.toList());
  }

  /**
   * 将  Map list转化为 SOURCE LIST
   *
   * @param mapList
   * @return {@link List< S >}
   */
  default List<S> mapListToDoList(List<Map<String, Object>> mapList) {
    final String s = JSON.toJSONString(mapList);
    try {
      final TypeToken<S> doType = new TypeToken<>(this.getClass()) {
      };
      final Class<S> rawType = (Class<S>) doType.getRawType();
      return JSON.parseArray(s, rawType);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
