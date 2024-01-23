package org.feuyeux.reloading.demo;

import static org.feuyeux.reloading.demo.HelloClassLoader.getClassLoader;

import com.google.common.reflect.ClassPath;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FnReLoader {

  String biz_clz = "org.feuyeux.reloading.demo.fn";

  public Hello reloadHello(String type, String fnInRule) throws Exception {
    Set<Class<?>> clazzs = findAllClasses(getClassLoader(type), biz_clz);
    for (Class<?> clazz : clazzs) {
      Method[] methods = clazz.getMethods();
      for (Method method : methods) {
        Saying annotation = method.getAnnotation(Saying.class);
        if (annotation != null && annotation.name().equals(fnInRule)) {
          return (Hello) clazz.getDeclaredConstructor().newInstance();
        }
      }
    }
    return null;
  }

  private Set<Class<?>> findAllClasses(ClassLoader cl, String packageName) throws Exception {
    return ClassPath.from(cl).getAllClasses().stream()
        .filter(
            clazz -> {
              String packageName0 = clazz.getPackageName();
              if (packageName0.contains("feuyeux") && packageName0.endsWith("fn")) {
                log.info("scanning: {}", clazz.getName());
              }
              return packageName0.equalsIgnoreCase(packageName);
            })
        .map(ClassPath.ClassInfo::load)
        .collect(Collectors.toSet());
  }
}
