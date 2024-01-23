package org.feuyeux.reloading.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class ReloaderTest {
    BasicReloader basicReloader = new BasicReloader();
    FnReLoader fnReLoader = new FnReLoader();
    String HELLO_METHOD = "demo.hello.saying";

    @Test
    public void testBaseReload() throws Exception {
        Hello cnHello = basicReloader.reloadHello("cn");
        Class<? extends Hello> cnClass = cnHello.getClass();

        Hello zhHello = basicReloader.reloadHello("zh");
        Class<? extends Hello> zhClass = zhHello.getClass();

        MyClassLoader myClassLoader = new MyClassLoader(cnClass.getClassLoader());
        Hello cnHello2 = basicReloader.reloadHello("cn", myClassLoader);
        Class<? extends Hello> cnClass2 = cnHello2.getClass();

        Hello enHello = basicReloader.reloadHello("en");
        Class<? extends Hello> enClass = enHello.getClass();

        /*Hello frHello = new HelloA();
        Class<? extends Hello> frClass = frHello.getClass();
        boolean assignableFrom = cnClass.isAssignableFrom(frClass);
        log.info("assignableFrom:{}", assignableFrom);*/

        log.info("Test: {},{}, {}",
                cnClass.isAssignableFrom(zhClass),
                cnClass.isAssignableFrom(cnClass2),
                cnClass.isAssignableFrom(enClass));
        log.info("basic hello:{}", cnHello.sayHello());
        log.info("basic hello:{}", enHello.sayHello());
    }

    @Test
    public void testFnReload() throws Exception {
        Hello enHello = fnReLoader.reloadHello("en", HELLO_METHOD);
        assertEquals("Hello", enHello.sayHello());
        log.info("en hello:{}", enHello.sayHello());

        Hello cnHello = fnReLoader.reloadHello("cn", HELLO_METHOD);
        assertEquals("你好", cnHello.sayHello());
        log.info("cn hello:{}", cnHello.sayHello());
    }
}
