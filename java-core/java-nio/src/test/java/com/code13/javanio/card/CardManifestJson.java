package com.code13.javanio.card;

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
 * manifest.json文件的替换
 *
 * @author code13
 * @date 2020/5/3 22:26
 */
@NotThreadSafe
public class CardManifestJson implements AutoCloseable {

  private String filePath;
  private List<String> readAllLines;
  private List<String> resultList;

  public CardManifestJson(String filePath) throws IOException {
    this.filePath = filePath;
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
      //.peek(System.out::println)
      .collect(Collectors.toList());

    String s = "";

    int lineNumber = 0;

    for (int i = 0; i < lineList.size(); i++) {
      String line = lineList.get(i);
      if (line.trim().startsWith("\"mp-weixin\"")) {
        s = lineList.get(i + 1);
        break;
      }
    }

    System.out.println(s);

    for (int i = 0; i < this.readAllLines.size(); i++) {
      if (Objects.equals(s, this.readAllLines.get(i))) {
        lineNumber = i;
        break;
      }
    }

    System.out.println(lineNumber);

    return lineNumber;
  }

  /**
   * 替换 appId的值
   *
   * @param lineNumber 行数
   * @param content    所要替换的内容
   */
  public void replace(int lineNumber, String content) {
    this.resultList.set(lineNumber, "    \"appid\": \"" + content + "\",");
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
}
