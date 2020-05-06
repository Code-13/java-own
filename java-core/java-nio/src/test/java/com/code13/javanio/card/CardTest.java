package com.code13.javanio.card;

import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * 名片测试
 *
 * @author code13
 * @date 2020/5/3 22:13
 */
public class CardTest {

  @Test
  void test1() throws IOException {

    try (CardQuickDeploy cardQuickDeploy = new CardQuickDeploy("D:\\appManager.js")) {
      cardQuickDeploy.deploy("1237272715912609794");
    }

  }

  @Test
  void test2() throws IOException {

    try (CardQuickDeploy cardManifestJson = new CardQuickDeploy("D:\\manifest.json")) {
      cardManifestJson.deploy("111111");
    }

  }

  @Test
  void test3() {

  }

}
