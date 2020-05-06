package com.code13.javanio.card;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * 微信开发者工具
 *
 * @author Code13
 * @date 2020-05-06 13:14
 */
@Slf4j
public final class WxIdeUtil {

  private final String project;
  private final String port;

  public WxIdeUtil(String projectPath) {
    this.project = URLEncoder.encode(projectPath + "\\dist\\build\\mp-weixin", StandardCharsets.UTF_8);
    this.port = this.port();
  }

  /**
   * 获取端口号
   */
  public String port() {
    final String userHome = System.getProperties().getProperty("user.home");
    try {
      String filePath = userHome + "\\AppData\\Local\\微信开发者工具\\User Data\\Default\\.ide";
      final List<String> readAllLines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
      return String.join("", readAllLines);
    } catch (IOException e) {
      throw new IllegalArgumentException("请开启开发者工具http端口功能");
    }
  }

  public void open() {
    //http://192.168.0.218:端口号/v2/open?project=%2FUsers%2Fusername%2Fdemo&info-output=%2Users%2username%2info.json

    //final String url = "http://192.168.0.218:" + this.port + "/v2/open?project=" + this.project;
    final String url = "http://127.0.0.1:" + this.port + "/v2/open?project=" + this.project;

    try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
      HttpGet httpGet = new HttpGet(url);
      String responseBody = httpClient.execute(httpGet, httpResponse -> {
        int status = httpResponse.getStatusLine().getStatusCode();
        if (status < 200 || status >= 300) {
          throw new IllegalArgumentException("打开开发者工具失败");
        }
        HttpEntity entity = httpResponse.getEntity();
        return entity != null ? EntityUtils.toString(entity) : null;
      });
      log.info("成功: {}", responseBody);
    } catch (IOException e) {
      throw new IllegalArgumentException("打开开发者工具失败");
    }

  }

  public void upload(String version) {
    //http://127.0.0.1:端口号/v2/upload?project=%2FUsers%2Fusername%2Fdemo&version=v1.0.0&desc=test

    //final String url = "http://192.168.0.218:" + this.port + "/v2/upload?project=" + this.project + "&version=" + version + "&desc=desc";
    final String url = "http://127.0.0.1:" + this.port + "/v2/upload?project=" + this.project + "&version=" + version + "&desc=test";

    try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
      HttpGet httpGet = new HttpGet(url);
      String responseBody = httpClient.execute(httpGet, httpResponse -> {
        int status = httpResponse.getStatusLine().getStatusCode();
        if (status < 200 || status >= 300) {
          log.error("出现错误");
          throw new IllegalArgumentException("上传代码失败");
        }
        HttpEntity entity = httpResponse.getEntity();
        return entity != null ? EntityUtils.toString(entity) : null;
      });
      // ... do something with response
      log.info("成功: {}", responseBody);
    } catch (IOException e) {
      throw new IllegalArgumentException("上传代码失败");
    }

  }

}
