package com.code13.javanio.card;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.annotation.concurrent.NotThreadSafe;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author code13
 * @date 2020/5/3 15:20
 */
@NotThreadSafe
public class CardQuickDeploy implements AutoCloseable {

  private String filePath;
  private final FileType fileType;
  private List<String> readAllLines;
  private List<String> resultList;

  public CardQuickDeploy(String filePath) throws IOException {
    this.filePath = filePath;
    this.fileType = FileType.ofFilPath(filePath);
    this.readAllLines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
    this.resultList = new ArrayList<>(this.readAllLines);
  }

  /**
   * 部署的方法
   *
   * @param content appId的内容
   */
  public void deploy(String content) throws IOException {
    final int lineNumber = this.getLineNumber();
    this.replace(lineNumber, content);
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
      if (line.trim().startsWith(this.fileType.getFindFlag())) {
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

  /**
   * 替换 appId的值
   *
   * @param lineNumber 行数
   * @param content    所要替换的内容
   */
  public void replace(int lineNumber, String content) {
    this.resultList.set(lineNumber, this.fileType.buildResult(content));
  }

  /**
   * 写出文件
   * <p>
   * 将写出的文件替换原文件
   */
  public void write() throws IOException {
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
  private enum FileType {

    JS("getAppId", "    return \"", "\""),

    JSON("\"mp-weixin\"", "    \"appid\": \"", "\","),

    ;

    private final String findFlag;

    private final String resultFlag;

    private final String resultSymbol;

    private String buildResult(String content) {
      return this.resultFlag + content + this.resultSymbol;
    }

    private static FileType ofFilPath(String filePath) {
      final int index = filePath.lastIndexOf(".");
      if (index == -1) {
        throw new IllegalArgumentException("文件类型不正确");
      }

      final String substring = filePath.substring(index + 1);

      for (FileType fileType : values()) {
        if (fileType.toString().equalsIgnoreCase(substring)) {
          return fileType;
        }
      }

      throw new IllegalArgumentException("文件类型不正确");
    }

  }

}
