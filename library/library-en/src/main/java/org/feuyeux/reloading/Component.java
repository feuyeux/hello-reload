package org.feuyeux.reloading;

import org.feuyeux.reloading.demo.Hello;
import org.springframework.stereotype.Service;

@Service
public class Component implements Hello {

  @Override
  public String sayHello() {
    return "test spring jar load";
  }
}
