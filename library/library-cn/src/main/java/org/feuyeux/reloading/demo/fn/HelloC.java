package org.feuyeux.reloading.demo.fn;

import org.feuyeux.reloading.demo.Hello;
import org.feuyeux.reloading.demo.Saying;

@Saying(name = "Hello")
public class HelloC implements Hello {
  @Saying(name = "demo.hello.saying")
  public String sayHello() {
    return "你好";
  }
}
