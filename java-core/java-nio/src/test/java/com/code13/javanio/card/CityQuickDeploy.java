package com.code13.javanio.card;

import javax.annotation.concurrent.NotThreadSafe;
import java.io.IOException;

/**
 * 城市快速部署
 *
 * @author Code13
 * @date 2020-05-06 11:35
 */
@NotThreadSafe
public class CityQuickDeploy extends FileQuickDeploy {

  private String cityName;

  private int cityId;

  public CityQuickDeploy(String filePath) throws IOException {
    super(filePath);
  }

  public CityQuickDeploy setCityName(String cityName) {
    this.cityName = cityName;
    return this;
  }

  public CityQuickDeploy setCityId(int cityId) {
    this.cityId = cityId;
    return this;
  }

  @Override
  protected String getFindFlag() {
    return "city: {";
  }

  @Deprecated
  @Override
  public void replace(int lineNumber, String content) {
    throw new IllegalArgumentException();
  }

  @Deprecated
  @Override
  protected String buildResult(String content) {
    throw new IllegalArgumentException();
  }

  @Override
  public void deploy() throws IOException {
    final int lineNumber = super.getLineNumber();
    this.resultList.set(lineNumber, "");
    this.resultList.set(lineNumber, "    shortName : \"" + this.cityName + "\",");
    this.resultList.set(lineNumber + 1, "    id : " + this.cityId + "");
    super.write();
  }

}
