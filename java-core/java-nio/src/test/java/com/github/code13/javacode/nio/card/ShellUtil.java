package com.github.code13.javacode.nio.card;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 命令行工具类
 *
 * @author Code13
 * @date 2020-05-06 13:53
 */
public abstract class ShellUtil {

  /**
   * 编译命令
   */
  private final static String COMPILE_COMMAND = "npm.cmd run build:mp-weixin";

  /**
   * 撤销更改的命令
   */
  private final static String ROLLBACK_COMMAND = "git checkout .";

  private ShellUtil() {
    throw new AssertionError("No ShellUtil instances for you!");
  }

  /**
   * npm编译命令
   *
   * @param projectPath 项目地址
   */
  public static void build(String projectPath) {
    exec(COMPILE_COMMAND, projectPath);
  }

  /**
   * git 撤销更改命令
   *
   * @param projectPath 项目地址
   */
  public static void rollback(String projectPath) {
    exec(ROLLBACK_COMMAND, projectPath);
  }

  /**
   * 执行命令
   *
   * @param command  具体的命令
   * @param filePath 需要执行命令的文件夹地址
   */
  public static void exec(String command, String filePath) {
    File dir = new File(filePath);

    try {
      final Process process = Runtime.getRuntime().exec(command, null, dir);

      final InputStream inputStream = process.getInputStream();

      try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
        String content = br.readLine();
        while (content != null) {
          System.out.println(content);
          content = br.readLine();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
