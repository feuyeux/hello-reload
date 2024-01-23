package org.feuyeux.reloading.demo.spring;

import org.feuyeux.reloading.demo.Hello;
import org.springframework.stereotype.Service;

@Service
public class HelloA implements Hello {

    @Override
    public String sayHello() {
        return "Benjour " + printTimestamp();
    }

    public static long printTimestamp() {
        return System.currentTimeMillis();
    }
}
