package org.feuyeux.reloading.demo;

public class HelloA implements Hello {

  public String sayHello() {
    return "Hello " + HelloLibUtils.printTimestamp();
  }
}
