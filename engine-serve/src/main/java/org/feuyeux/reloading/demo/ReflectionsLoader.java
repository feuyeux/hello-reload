package org.feuyeux.reloading.demo;

import static org.feuyeux.reloading.demo.HelloClassLoader.getClassLoader;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yyhuangfu
 * @create 2023/11/28
 */
@Slf4j
public class ReflectionsLoader {
  String biz_clz = "org.feuyeux.reloading.demo.HelloA";

  public Hello reloadHello() {
    try {
      ClassLoader urlClassLoader = getClassLoader("cn");
      Class<?> clazz = urlClassLoader.loadClass(biz_clz);
      Class<?> clazz1 = Class.forName(clazz.getName());
      return (Hello) clazz1.getDeclaredConstructor().newInstance();
    } catch (Exception e) {
      log.error("exp:{}", e.getMessage());
      return null;
    }
  }

  public static void main(String[] args) {
    ReflectionsLoader reflectionsLoader = new ReflectionsLoader();
    Hello hello = reflectionsLoader.reloadHello();
    log.info("hello:{}", hello.sayHello());
  }
}
