package com.github.code13.designpatterns.strategy.demo02.service;

import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Code13
 * @date 2020-09-29 09:35
 */
@SuppressWarnings({"unchecked"})
public abstract class AbstractStrategyHolder<T, A extends Annotation, E extends Enum<E>> {

  private final Class<A> annotationType;

  private final Map<E, T> enumMap;

  private final Map<A, T> annotationMap;

  protected AbstractStrategyHolder(List<T> strategyList) {
    this.annotationType = this.annotationType();
    Method method = this.getAnnotationMethod();

    this.enumMap = strategyList.stream().collect(
      Collectors.toMap(h -> {
          try {
            return (E) method.invoke(AnnotationUtils.findAnnotation(h.getClass(), this.annotationType));
          } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("请在策略类上标注" + this.annotationType.getName() + ",并指定" + method.getName() + "的值", e);
          }
        },
        v -> v, (v1, v2) -> v1));

    this.annotationMap = strategyList.stream().collect(
      Collectors.toMap(t -> AnnotationUtils.findAnnotation(t.getClass(), this.annotationType), o -> o, (t, t2) -> t)
    );
  }

  /**
   * 获取注解的类型
   *
   * @return {@link Class<A>}
   */
  protected Class<A> annotationType() {
    return (Class<A>) this.getTypes()[1];
  }


  /**
   * 获取注解内部的方法名
   *
   * @return {@link String}
   */
  protected String annotationMethodName() {
    return "value";
  }

  /**
   * 根据注解方法名获取注解的方法
   *
   * @return {@link Method}
   */
  private Method getAnnotationMethod() {
    final String methodName = this.annotationMethodName();
    try {
      return this.annotationType.getMethod(methodName);
    } catch (NoSuchMethodException e) {
      throw new IllegalArgumentException("指定的注解方法名不存在，请检查 annotationMethodName 方法", e);
    }
  }

  /**
   * 获取所有泛型的类型
   *
   * @return {@link Type[]}
   */
  protected Type[] getTypes() {
    return ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();
  }

  /**
   * 根据枚举获取处理器
   *
   * @return {@link T}
   */
  public T getHandler(E e) {
    return this.enumMap.get(e);
  }

  /**
   * 根据 Annotation 获取处理器
   *
   * @param annotation
   * @return {@link T}
   */
  public T getHandler(A annotation) {
    return this.annotationMap.get(annotation);
  }

}
