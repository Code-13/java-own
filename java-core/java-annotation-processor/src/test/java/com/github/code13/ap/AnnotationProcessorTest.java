package com.github.code13.ap;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import org.junit.Test;

import javax.annotation.processing.Processor;
import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ServiceLoader;

/**
 * @author Code13
 * @date 2020-09-30 17:26
 */
public class AnnotationProcessorTest {

  @Test
  public void test() {
    final ServiceLoader<Processor> processors = ServiceLoader.load(Processor.class);
    for (Processor processor : processors) {
      System.out.println(processor.getClass().getName());
    }
  }

  @Test
  public void test2() {
    MethodSpec main = MethodSpec.methodBuilder("main")
      .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
      .returns(void.class)
      .addParameter(String[].class, "args")
      .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
      .build();
    // HelloWorld class
    TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
      .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
      .addMethod(main)
      .build();
    try {
      // build com.example.HelloWorld.java
      JavaFile javaFile = JavaFile.builder("com.github.code13.ap", helloWorld)
        .addFileComment(" This codes are generated automatically. Do not modify!")
        .build();
      // write to file
      javaFile.writeTo(Path.of("D:\\code13\\own\\java-own\\java-core\\java-annotation-processor\\src\\main\\java"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
