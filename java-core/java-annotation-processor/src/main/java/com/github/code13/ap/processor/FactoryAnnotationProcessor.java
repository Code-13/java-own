package com.github.code13.ap.processor;

import com.github.code13.ap.annotation.Factory;
import com.github.code13.ap.annotation.FactoryAnnotatedClass;
import com.github.code13.ap.annotation.FactoryGroupedClasses;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Code13
 * @date 2020-09-30 14:28
 */
public class FactoryAnnotationProcessor extends AbstractProcessor {

  private Types typeUtils;
  private Elements elementUtils;
  private Filer filer;
  private Messager messager;
  private Map<String, FactoryGroupedClasses> factoryClasses = new LinkedHashMap<>();

  @Override
  public synchronized void init(ProcessingEnvironment processingEnv) {
    super.init(processingEnv);
    this.typeUtils = processingEnv.getTypeUtils();
    this.elementUtils = processingEnv.getElementUtils();
    this.filer = processingEnv.getFiler();
    this.messager = processingEnv.getMessager();
  }

  @Override
  public Set<String> getSupportedAnnotationTypes() {
    Set<String> set = new HashSet<>();
    set.add(Factory.class.getName());
    return set;
  }

  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latestSupported();
  }

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

    this.messager.printMessage(Diagnostic.Kind.ERROR, "我执行了吗");

    // 检查被注解为@Factory的元素是否是一个类
    for (Element annotatedElement : roundEnv.getElementsAnnotatedWith(Factory.class)) {
      if (annotatedElement.getKind() != ElementKind.CLASS) {
        this.error(annotatedElement, "Only classes can be annotated with @%s", Factory.class.getSimpleName());
        // 退出处理
        return true;
      }

      final TypeElement typeElement = (TypeElement) annotatedElement;

      try {
        final FactoryAnnotatedClass annotatedClass = new FactoryAnnotatedClass(typeElement);

        if (!this.isValidClass(annotatedClass)) {
          return true;
        }

        FactoryGroupedClasses factoryClass = this.factoryClasses.get(annotatedClass.getQualifiedFactoryGroupName());
        if (this.factoryClasses == null) {
          final String qualifiedGroupName = annotatedClass.getQualifiedFactoryGroupName();
          factoryClass = new FactoryGroupedClasses(qualifiedGroupName);
          this.factoryClasses.put(qualifiedGroupName, factoryClass);
        }

        // 如果和其他的@Factory标注的类的id相同冲突，
        // 抛出IdAlreadyUsedException异常
        factoryClass.add(annotatedClass);
      } catch (IllegalArgumentException e) {
        this.error(typeElement, e.getMessage());
        return true;
      }
    }

    try {
      for (FactoryGroupedClasses factoryClass : this.factoryClasses.values()) {
        factoryClass.generateCode(this.elementUtils, this.filer);
      }

      this.factoryClasses.clear();
    } catch (IOException e) {
      this.error(null, e.getMessage());
    }

    return true;
  }

  private boolean isValidClass(FactoryAnnotatedClass item) {
    final TypeElement classElement = item.getTypeElement();

    if (!classElement.getModifiers().contains(Modifier.PUBLIC)) {
      this.error(classElement, "The class %s is not public.", classElement.getQualifiedName().toString());
      return false;
    }

    if (classElement.getModifiers().contains(Modifier.ABSTRACT)) {
      this.error(classElement, "The class %s is abstract. You can't annotate abstract classes with @%",
        classElement.getQualifiedName().toString(), Factory.class.getSimpleName());
      return false;
    }

    final TypeElement supperClassElement = this.elementUtils.getTypeElement(item.getQualifiedFactoryGroupName());

    if (supperClassElement.getKind() == ElementKind.INTERFACE) {
      if (!classElement.getInterfaces().contains(supperClassElement.asType())) {
        this.error(classElement, "The class %s annotated with @%s must implement the interface %s",
          classElement.getQualifiedName().toString(), Factory.class.getSimpleName(),
          item.getQualifiedFactoryGroupName());
        return false;
      }
    } else {
      TypeElement currentClass = classElement;
      while (true) {
        final TypeMirror superclassType = currentClass.getSuperclass();

        if (superclassType.getKind() == TypeKind.NONE) {
          // 到达了基本类型(java.lang.Object), 所以退出
          this.error(classElement, "The class %s annotated with @%s must inherit from %s",
            classElement.getQualifiedName().toString(), Factory.class.getSimpleName(),
            item.getQualifiedFactoryGroupName());
          return false;
        }

        if (superclassType.toString().equals(item.getQualifiedFactoryGroupName())) {
          break;
        }

        currentClass = (TypeElement) this.typeUtils.asElement(superclassType);
      }
    }

    for (Element enclosedElement : classElement.getEnclosedElements()) {
      if (enclosedElement.getKind() == ElementKind.CONSTRUCTOR) {
        ExecutableElement constructorElement = (ExecutableElement) enclosedElement;
        if (constructorElement.getParameters().isEmpty() && constructorElement.getModifiers().contains(Modifier.PUBLIC)) {
          return true;
        }
      }
    }

    // 没有找到默认构造函数
    this.error(classElement, "The class %s must provide an public empty default constructor",
      classElement.getQualifiedName().toString());

    return false;
  }

  private void error(Element e, String msg, Object... args) {
    this.messager.printMessage(
      Diagnostic.Kind.ERROR,
      String.format(msg, args),
      e);
  }

}
