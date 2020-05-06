package com.code13.javanio.card;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 命令行工具类
 *
 * @author Code13
 * @date 2020-05-06 13:53
 */
public final class CmdUtil {

  private CmdUtil() {
  }

  public static void build(String projectPath) {
    String command = "npm.cmd run build:mp-weixin";
    exec(command, projectPath);
  }

  public static void rollback(String projectPath) {
    String command = "git checkout .";
    exec(command, projectPath);
  }

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
