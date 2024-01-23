package org.feuyeux.reloading.demo;

import lombok.extern.slf4j.Slf4j;

import static org.feuyeux.reloading.demo.HelloClassLoader.getClassLoader;

@Slf4j
public class BasicReloader {

    String biz_clz = "org.feuyeux.reloading.demo.HelloA";

    public Hello reloadHello(String type, ClassLoader urlClassLoader) {
        if (urlClassLoader == null) {
            try {
                urlClassLoader = getClassLoader(type);
            } catch (Exception e) {
                log.error("", e);
                return null;
            }
        }
        try {
            Class<?> clazz = urlClassLoader.loadClass(biz_clz);
            Object instance = clazz.getDeclaredConstructor().newInstance();
            return (Hello) instance;
        } catch (Exception e) {
            log.error("", e);
            return null;
        }
    }

    public Hello reloadHello(String type) {
        return reloadHello(type, null);
    }
}
