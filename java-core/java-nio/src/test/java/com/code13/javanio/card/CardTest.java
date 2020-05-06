package com.code13.javanio.card;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 名片测试
 *
 * @author code13
 * @date 2020/5/3 22:13
 */
@Slf4j
public class CardTest {

  @Test
  void test0() {
    try (CardQuickDeploy cardQuickDeploy = new CardQuickDeploy("D:\\code13\\company\\tjyun_app_card")) {
      cardQuickDeploy.setAppId("1237272715912609794").setWxAppId("wxcbcd66fb059af21b").setCityName("镇江").setCityId(321100);
      cardQuickDeploy.deploy("v1.0.23");
    }
  }

  @Test
  void test1() throws IOException {

    try (FileQuickDeploy cardQuickDeploy = new FileQuickDeploy("D:\\appManager.js")) {
      cardQuickDeploy.setContent("1237272715912609794");
      cardQuickDeploy.deploy();
    }

  }

  @Test
  void test2() throws IOException {

    try (FileQuickDeploy cardManifestJson = new FileQuickDeploy("D:\\manifest.json")) {
      cardManifestJson.setContent("123456gaga");
    }

  }

  @Test
  void test3() throws IOException {
    try (CityQuickDeploy cityQuickDeploy = new CityQuickDeploy("D:\\state.js")) {
      cityQuickDeploy.setCityName("biejing").setCityId(100000);
      cityQuickDeploy.deploy();
    }
  }

  /**
   * 执行 npm 命令
   */
  @Test
  void test4() {

    File dir = new File("D:\\code13\\company\\tjyun_app_card");

    String command = "npm.cmd run build:mp-weixin";

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

  /**
   * 获取微信开发者工具端口号
   */
  @Test
  void test5() {

    final String userHome = System.getProperties().getProperty("user.home");

    System.out.println(userHome);

    try {
      String filePath = userHome + "\\AppData\\Local\\微信开发者工具\\User Data\\Default\\.ide";
      final List<String> readAllLines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);

      final String port = String.join("", readAllLines);

      System.out.println(port);

    } catch (IOException e) {
      //抛出异常
      e.printStackTrace();
    }
  }

  /**
   * 获取文件名称
   */
  @Test
  void test6() {
    String filePath = "D:\\code13\\company\\tjyun_app_card\\src\\manifest.json";
    final String[] split = filePath.split("\\\\");
    final String s = split[split.length - 1];
    System.out.println(s);
  }

  /**
   * 撤销修改操作
   */
  @Test
  void test7() {
    File dir = new File("D:\\code13\\company\\tjyun_app_card");

    String command = "git checkout .";

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

  @Test
  void test8() {
    //http://127.0.0.1:端口号/v2/upload?project=%2FUsers%2Fusername%2Fdemo&version=v1.0.0&desc=test

    //final String url = "http://192.168.0.218:" + this.port + "/v2/upload?project=" + this.project + "&version=" + version + "&desc=desc";
    final String url = "http://127.0.0.1:" + 12561 + "/v2/upload?project=" + URLEncoder.encode("D:\\code13\\company\\tjyun_app_card\\dist\\build\\mp-weixin", StandardCharsets.UTF_8) + "&version=" + "v1.0.23" + "&desc=test";

    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder().url(url).build();
    try (Response response = client.newCall(request).execute()) {
      if (!response.isSuccessful()) {
        log.error(response.body().string());
        throw new IllegalArgumentException("上传代码失败");
      }
      String responseBody = response.body().string();
      // ... do something with response
      log.info("上传代码成功");
    } catch (IOException e) {
      log.error(e.getMessage());
      throw new IllegalArgumentException("上传代码失败");
    }

  }

  @Test
  void test9() {
    String version = "1.0.23";

    final List<String> list = new ArrayList<>(Arrays.asList(version.split("\\.")));

    list.set(list.size() - 1, String.valueOf((Integer.parseInt(list.get(list.size() - 1)) + 1)));

    final String join = String.join(".", list);

    System.out.println("v" + join);
  }

}
