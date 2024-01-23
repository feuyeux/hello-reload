package org.feuyeux.reloading.demo.spring;

import lombok.extern.slf4j.Slf4j;
import org.feuyeux.reloading.demo.Hello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.net.URLClassLoader;

@SpringBootApplication
@Slf4j
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    Hello component;

    @PostConstruct
    public void init() {
        String res = component.sayHello();
        log.info("res:{}", res);
        ClassLoader classLoader = component.getClass().getClassLoader();
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        log.info("classLoader:{},contextClassLoader:{}", classLoader, contextClassLoader);
        String tree = ClassLoaderUtils.tree();
        log.info("tree:{}", tree);
        String urls = ClassLoaderUtils.urls((URLClassLoader) Application.class.getClassLoader());
        log.debug("urls:{}", urls);
        String sysUrls = ClassLoaderUtils.urls((URLClassLoader) ClassLoader.getSystemClassLoader());
        log.debug("sysUrls:{}", sysUrls);
    }
}
