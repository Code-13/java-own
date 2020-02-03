package com.code13.springbootconfiguration.facroty;

import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.util.Objects;

/**
 * 使注解@PropertySource可以加载yaml文件
 *
 * @author code13
 * @date 2020/2/2 19:20
 */
public class YamlPropertySourceFactor extends DefaultPropertySourceFactory {

  @Override
  public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
    if (Objects.isNull(resource)) {
      super.createPropertySource(name, resource);
    }
    return new YamlPropertySourceLoader()
      .load(resource.getResource().getFilename(), resource.getResource()).get(0);
  }
}
