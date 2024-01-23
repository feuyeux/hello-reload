package org.feuyeux.reloading.demo;

public class HelloA implements Hello {

  public String sayHello() {
    return "你好 " + HelloLibUtils.printTimestamp();
  }
}
