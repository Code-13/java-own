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
 * 微信开发者工具HTTP调用工具类
 * <p>
 * 微信文档
 * https://developers.weixin.qq.com/miniprogram/dev/devtools/http.html
 *
 * @author Code13
 * @date 2020-05-06 13:14
 */
@Slf4j
public final class WxIdeUtil {

  private final static String HOST = "127.0.0.1";
  private final static String HTTP_VERSION = "v2";

  private final String project;
  private final String port;

  public WxIdeUtil(String projectPath) {
    this.project = URLEncoder.encode(projectPath + "\\dist\\build\\mp-weixin", StandardCharsets.UTF_8);
    this.port = this.port();
  }

  /**
   * 初始化IDE的端口号
   *
   * @return {@link String} 端口号
   */
  private String port() {
    final String userHome = System.getProperties().getProperty("user.home");
    try {
      String filePath = userHome + "\\AppData\\Local\\微信开发者工具\\User Data\\Default\\.ide";
      final List<String> readAllLines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
      return String.join("", readAllLines);
    } catch (IOException e) {
      throw new IllegalArgumentException("请开启开发者工具http端口功能");
    }
  }

  /**
   * 启动工具
   * <pre>
   * 接口定义：
   *
   * URL： /open
   *
   * HTTP 方法: GET
   * URL 参数 	必填 	说明
   * project 	否 	打开指定路径中的项目。如项目已打开，自动刷新项目。如项目未创建，自动创建并打开项目。如不填，显示工具窗口
   *
   * 示例：
   *
   * # 打开工具
   *    http://127.0.0.1:端口号/v2/open
   * # 打开/刷新项目
   *    http://127.0.0.1:端口号/v2/open?project=项目全路径
   *
   * 注意：
   *     项目路径中必须含正确格式的 project.config.json 且其中有 appid 和 projectname 字段。
   *     项目路径需经 URL encode
   * </pre>
   */
  public void open() {
    final String url = "http://" + HOST + ":" + this.port + "/" + HTTP_VERSION + "/open?project=" + this.project;
    this.exec(url);
  }

  /**
   * 上传代码
   * <pre>
   * 接口定义：
   *
   * URL：/v2/upload
   *
   * HTTP 方法：GET
   * URL 参数 	必填 	说明
   * project 	是 	项目路径
   * version 	是 	版本号
   * desc 	否 	本次上传的版本备注
   * info-output 	否 	指定后，会将本次上传的额外信息以 json 格式输出至指定路径，如代码包大小、分包大小信息。
   *
   * 示例：
   *
   * # 上传路径为 /Users/username/demo 的项目，指定版本号为 v1.0.0
   *    http://127.0.0.1:端口号/v2/upload?project=%2FUsers%2Fusername%2Fdemo&version=v1.0.0
   * # 上传路径为 /Users/username/demo 的项目，指定版本号为 v1.0.0，并带上备注
   *    http://127.0.0.1:端口号/v2/upload?project=%2FUsers%2Fusername%2Fdemo&version=v1.0.0&desc=test
   * # 上传路径为 /Users/username/demo 的项目，指定版本号为 v1.0.0，并将上传信息输出至 /Users/username/info.json
   *    http://127.0.0.1:端口号/v2/upload?project=%2FUsers%2Fusername%2Fdemo&version=v1.0.0&info-output=%2Users%2username%2info.json
   * </pre>
   *
   * @param version 版本号
   */
  public void upload(String version) {
    final String url = "http://" + HOST + ":" + this.port + "/" + HTTP_VERSION + "/upload?project=" + this.project + "&version=" + version + "&desc=test";
    this.exec(url);
  }

  /**
   * http请求执行
   *
   * @param url 请求的全路径
   */
  private void exec(String url) {
    try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
      HttpGet httpGet = new HttpGet(url);
      String responseBody = httpClient.execute(httpGet, httpResponse -> {
        int status = httpResponse.getStatusLine().getStatusCode();
        HttpEntity entity = httpResponse.getEntity();
        String body = entity != null ? EntityUtils.toString(entity) : null;
        if (status < 200 || status >= 300) {
          log.error("微信IDE执行出现错误: {}", body);
        }
        return body;
      });
      log.info("成功: {}", responseBody);
    } catch (IOException e) {
      log.error("微信IDE执行出现错误: {}", e.getMessage());
      throw new IllegalArgumentException(e);
    }
  }

}
