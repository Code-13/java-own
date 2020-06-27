package com.github.code13.javacore.nio.card;

import javax.annotation.concurrent.NotThreadSafe;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author code13
 * @date 2020/5/3 15:20
 */
@NotThreadSafe
public class CardQuickDeploy implements AutoCloseable {

  private volatile String projectPath;

  private volatile FileQuickDeploy appIdDeploy;

  private volatile FileQuickDeploy wxAppIdDeploy;

  private volatile CityQuickDeploy cityQuickDeploy;

  private volatile WxIdeUtil wxIdeUtil;

  public CardQuickDeploy(String projectPath) {
    try {
      this.projectPath = projectPath;
      this.appIdDeploy = new FileQuickDeploy(projectPath + "\\src\\common\\manager\\appManager.js");
      this.wxAppIdDeploy = new FileQuickDeploy(projectPath + "\\src\\manifest.json");
      this.cityQuickDeploy = new CityQuickDeploy(projectPath + "\\src\\store\\state.js");
      this.wxIdeUtil = new WxIdeUtil(projectPath);
    } catch (IOException e) {
      throw new IllegalArgumentException("项目路径不正确");
    }
  }

  public synchronized CardQuickDeploy setAppId(String appId) {
    this.appIdDeploy.setContent(appId);
    return this;
  }

  public synchronized CardQuickDeploy setWxAppId(String wxAppId) {
    this.wxAppIdDeploy.setContent(wxAppId);
    return this;
  }

  public synchronized CardQuickDeploy setCityName(String cityName) {
    this.cityQuickDeploy.setCityName(cityName);
    return this;
  }

  public synchronized CardQuickDeploy setCityId(int cityId) {
    this.cityQuickDeploy.setCityId(cityId);
    return this;
  }

  public synchronized void deploy(String version) {
    try {
      this.appIdDeploy.deploy();
      this.wxAppIdDeploy.deploy();
      this.cityQuickDeploy.deploy();
      ShellUtil.build(this.projectPath);
      this.wxIdeUtil.upload(version);
      ShellUtil.rollback(this.projectPath);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void close() {
    synchronized (this) {
      this.appIdDeploy = null;
      this.wxAppIdDeploy = null;
      this.cityQuickDeploy = null;
      this.projectPath = null;
      this.wxIdeUtil = null;
    }
  }

  public static String nextVersion(String version) {
    final List<String> list = new ArrayList<>(Arrays.asList(version.split("\\.")));
    list.set(list.size() - 1, String.valueOf((Integer.parseInt(list.get(list.size() - 1)) + 1)));
    final String join = String.join(".", list);
    return "v" + join;
  }

}
