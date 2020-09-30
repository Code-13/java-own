package com.github.code13.ap.annotation;

import org.apache.commons.lang3.StringUtils;

import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.MirroredTypeException;

/**
 * @author Code13
 * @date 2020-09-30 15:10
 */
public class FactoryAnnotatedClass {

  private TypeElement annotatedClassElement;

  private String qualifiedSuperClassName;

  private String simpleTypeName;

  private String id;

  public FactoryAnnotatedClass(TypeElement classElement) throws IllegalArgumentException {
    this.annotatedClassElement = classElement;
    Factory annotation = classElement.getAnnotation(Factory.class);
    this.id = annotation.id();

    if (StringUtils.isEmpty(this.id)) {
      throw new IllegalArgumentException(
        String.format("id() in @%s for class %s is null or empty! that's not allowed",
          Factory.class.getSimpleName(), classElement.getQualifiedName().toString()));
    }

    // Get the full QualifiedTypeName
    try {
      Class<?> clazz = annotation.type();
      this.qualifiedSuperClassName = clazz.getCanonicalName();
      this.simpleTypeName = clazz.getSimpleName();
    } catch (MirroredTypeException mte) {
      DeclaredType classTypeMirror = (DeclaredType) mte.getTypeMirror();
      TypeElement classTypeElement = (TypeElement) classTypeMirror.asElement();
      this.qualifiedSuperClassName = classTypeElement.getQualifiedName().toString();
      this.simpleTypeName = classTypeElement.getSimpleName().toString();
    }
  }

  /**
   * 获取在{@link Factory#id()}中指定的id
   * return the id
   */
  public String getId() {
    return this.id;
  }

  /**
   * 获取在{@link Factory#type()}指定的类型合法全名
   *
   * @return qualified name
   */
  public String getQualifiedFactoryGroupName() {
    return this.qualifiedSuperClassName;
  }


  /**
   * 获取在 {@link Factory#type()} 中指定的类型的简单名字
   *
   * @return qualified name
   */
  public String getSimpleFactoryGroupName() {
    return this.simpleTypeName;
  }

  /**
   * 获取被@Factory注解的原始元素
   */
  public TypeElement getTypeElement() {
    return this.annotatedClassElement;
  }

}
