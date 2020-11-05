package com.github.code13;

import javassist.*;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * @author code13
 * @date 2020/10/31 11:02
 */
@SuppressWarnings("AlibabaUndefineMagicConstant")
public class AgentMain {

  /*public static void premain(String arg, Instrumentation instrumentation) {
    System.out.println("Hello agent");

    final var userServiceName = "com.github.code13.UserService";

    final ClassPool pool = new ClassPool();
    pool.appendSystemPath();

    instrumentation.addTransformer(new ClassFileTransformer() {
      @Override
      public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        if (!Objects.equals("com/github/code13/UserService", className)) {
          return null;
        }

        try {
          var ctClass = pool.get("com.github.code13.UserService");
          var sayHello = ctClass.getDeclaredMethod("sayHello");
          //sayHello.insertBefore("System.out.println(System.currentTimeMillis());");

          // 复制方法
          var copy = CtNewMethod.copy(sayHello, ctClass, null);
          copy.setName("sayHello$agent");
          ctClass.addMethod(copy);

          // 改变原有方法
          sayHello.setBody("{ long begin = System.currentTimeMillis(); " +
            "sayHello$agent($1);  " +
            "System.out.println(System.currentTimeMillis() - begin); }");

          return ctClass.toBytecode();
        } catch (Exception e) {
          e.printStackTrace();
        }

        return null;
      }
    });

  }*/

  public static void premain(String arg, Instrumentation instrumentation) {

    final String config = arg;

    final ClassPool pool = new ClassPool();
    pool.appendSystemPath();

    instrumentation.addTransformer(new ClassFileTransformer() {
      @Override
      public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        if (className == null || !className.replace("/", ".").startsWith(config)) {
          return null;
        }

        try {
          var name = className.replace("/", ".");
          var ctClass = pool.get(name);
          for (CtMethod declaredMethod : ctClass.getDeclaredMethods()) {
            newMethod(declaredMethod);
          }

          return ctClass.toBytecode();
        } catch (Exception e) {
          e.printStackTrace();
        }

        return null;
      }
    });

  }

  private static void newMethod(CtMethod oldMethod) throws CannotCompileException, NotFoundException {
    var copyMethod = CtNewMethod.copy(oldMethod, oldMethod.getDeclaringClass(), null);
    copyMethod.setName(copyMethod.getName() + "$agent");
    copyMethod.getDeclaringClass().addMethod(copyMethod);

    if (copyMethod.getReturnType().equals(CtClass.voidType)) {
      oldMethod.setBody(String.format(voidSource, oldMethod.getName()));
    } else {
      oldMethod.setBody(String.format(source, oldMethod.getName()));
    }

  }

  public static final String source = "{\n" +
    "      long begin = System.currentTimeMillis();\n" +
    "      Object result;\n" +
    "      try {\n" +
    "        result = ($w)%s$agent($$);\n" +
    "      } finally {\n" +
    "        System.out.println(System.currentTimeMillis() - begin);\n" +
    "      }\n" +
    "      return ($r) result;\n" +
    "    }";

  public static final String voidSource = "{\n" +
    "      long begin = System.currentTimeMillis();\n" +
    "      try {\n" +
    "        %s$agent($$);\n" +
    "      } finally {\n" +
    "        System.out.println(System.currentTimeMillis() - begin);\n" +
    "      }\n" +
    "    }";
  ;


}
