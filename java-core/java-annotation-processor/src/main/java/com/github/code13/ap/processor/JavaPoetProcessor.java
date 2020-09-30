package com.github.code13.ap.processor;

import com.github.code13.ap.annotation.DoctorInterface;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

/**
 * @author Code13
 * @date 2020-09-30 17:45
 */
public class JavaPoetProcessor extends AbstractProcessor {
  private Filer filer;

  @Override
  public synchronized void init(ProcessingEnvironment processingEnvironment) {
    super.init(processingEnvironment);
    this.filer = this.processingEnv.getFiler();
  }

  @Override
  public Set<String> getSupportedAnnotationTypes() {
    return Collections.singleton(DoctorInterface.class.getCanonicalName());
  }

  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latestSupported();
  }

  @Override
  public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
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
      javaFile.writeTo(this.filer);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return true;
  }
}
