package com.github.code13.javacore.nio.card;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 文件快速部署
 *
 * @author Code13
 * @date 2020-05-06 11:12
 */
public class FileQuickDeploy implements AutoCloseable {

  protected String filePath;
  private final ChangeType changeType;
  protected List<String> readAllLines;
  protected List<String> resultList;

  private String content;

  public FileQuickDeploy(String filePath) throws IOException {
    this.filePath = filePath;
    this.changeType = ChangeType.ofFilPath(filePath);
    this.readAllLines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
    this.resultList = new ArrayList<>(this.readAllLines);
  }

  public final void setContent(String content) {
    this.content = content;
  }

  /**
   * 部署的方法
   */
  public void deploy() throws IOException {
    final int lineNumber = this.getLineNumber();
    this.replace(lineNumber, this.content);
    this.write();
  }

  /**
   * 获取AppId的所在行数
   *
   * @return {@link int} 所在行数
   */
  public int getLineNumber() {

    final List<String> lineList = this.readAllLines
      .stream()
      .filter(line -> !line.trim().startsWith("//"))
      .filter(line -> !line.trim().startsWith("/**"))
      .filter(line -> !line.trim().startsWith("*"))
      .filter(line -> !line.trim().startsWith("/*"))
      .filter(line -> !line.trim().startsWith("* */"))
      .peek(System.out::println)
      .collect(Collectors.toList());

    String s = "";

    int lineNumber = 0;

    for (int i = 0; i < lineList.size(); i++) {
      String line = lineList.get(i);
      if (line.trim().startsWith(this.getFindFlag())) {
        s = lineList.get(i + 1);
        break;
      }
    }

    for (int i = 0; i < this.readAllLines.size(); i++) {
      if (Objects.equals(s, this.readAllLines.get(i))) {
        lineNumber = i;
        break;
      }
    }

    return lineNumber;
  }

  protected String getFindFlag() {
    return this.changeType.getFindFlag();
  }

  private String buildResult(String content) {
    return this.changeType.buildResult(content);
  }

  /**
   * 替换 appId的值
   *
   * @param lineNumber 行数
   * @param content    所要替换的内容
   */
  private void replace(int lineNumber, String content) {
    this.resultList.set(lineNumber, this.buildResult(content));
  }

  /**
   * 写出文件
   * <p>
   * 将写出的文件替换原文件
   */
  protected void write() throws IOException {
    final String tempList = String.join("\n", this.resultList);
    final byte[] bytes = tempList.getBytes(StandardCharsets.UTF_8);
    Files.write(Paths.get(this.filePath), bytes);
  }

  @Override
  public void close() {
    this.filePath = null;
    this.readAllLines = null;
    this.resultList = null;
  }

  @Getter
  @AllArgsConstructor
  private enum ChangeType {

    AppId("getAppId", "    return \"", "\""),

    WxAppId("\"mp-weixin\"", "    \"appid\": \"", "\","),

    ;

    private final String findFlag;

    private final String resultFlag;

    private final String resultSymbol;

    private String buildResult(String content) {
      return this.resultFlag + content + this.resultSymbol;
    }

    private static ChangeType ofFilPath(String filePath) {
      final String[] split = filePath.split("\\\\");
      final String fileName = split[split.length - 1];

      switch (fileName) {
        case "manifest.json":
          return WxAppId;
        case "appManager.js":
          return AppId;
        default:
          return null;
      }
    }

  }

}
