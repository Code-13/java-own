package com.github.code13.ap.annotation;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Code13
 * @date 2020-09-30 15:15
 */
public class FactoryGroupedClasses {

  /**
   * 将被添加到生成的工厂类的名字中
   */
  private static final String SUFFIX = "Factory";

  private String qualifiedClassName;

  private Map<String, FactoryAnnotatedClass> itemsMap = new LinkedHashMap<>();

  public FactoryGroupedClasses(String qualifiedClassName) {
    this.qualifiedClassName = qualifiedClassName;
  }

  public void add(FactoryAnnotatedClass toInsert) {
    final FactoryAnnotatedClass factoryAnnotatedClass = this.itemsMap.get(toInsert.getId());

    if (Objects.nonNull(factoryAnnotatedClass)) {
      throw new IllegalArgumentException("已经被使用了");
    }

    this.itemsMap.put(toInsert.getId(), toInsert);

  }

  public void generateCode(Elements elements, Filer filer) throws IOException {
    final TypeElement supperClassName = elements.getTypeElement(this.qualifiedClassName);
    final String factoryClassName = supperClassName.getSimpleName() + SUFFIX;

    //final JavaFileObject jfo = filer.createSourceFile(this.qualifiedClassName + SUFFIX);

    //final Writer writer = jfo.openWriter();

    final MethodSpec.Builder builder = MethodSpec.methodBuilder("create")
      .addModifiers(Modifier.PUBLIC)
      .addParameter(String.class, "id")
      .returns(supperClassName.getClass());

    for (FactoryAnnotatedClass item : this.itemsMap.values()) {
      builder.addStatement("if (\"%s\".equals(id))", item.getId());
      builder.addStatement("return new %s()", item.getTypeElement().getQualifiedName().toString());
    }

    final TypeSpec typeSpec = TypeSpec.classBuilder(factoryClassName)
      .addModifiers(Modifier.PUBLIC)
      .addMethod(builder.build())
      .build();

    JavaFile javaFile = JavaFile.builder("com.github.code13.ap.factory", typeSpec)
      .build();

    javaFile.writeTo(filer);
  }

}
