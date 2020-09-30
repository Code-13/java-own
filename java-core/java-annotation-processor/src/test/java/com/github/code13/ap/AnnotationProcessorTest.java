package com.github.code13.ap;

import org.junit.Test;

import javax.annotation.processing.Processor;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author Code13
 * @date 2020-09-30 17:26
 */
public class AnnotationProcessorTest {

  @Test
  public void test() {
    final ServiceLoader<Processor> processors = ServiceLoader.load(Processor.class);
    final Iterator<Processor> iterator = processors.iterator();
    while (iterator.hasNext()) {
      iterator.next();
    }
  }

}
