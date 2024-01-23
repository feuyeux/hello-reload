package org.feuyeux.reloading.demo;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class HelloClassLoader {

    // String PREFIX = "/Users/huangfuyiyu/IdeaProjects/";
    static final String PREFIX = "/Users/hanl5/coding/xpeng/";
    static final String LIB_CN_PATH = PREFIX + "reloading-demo/library/library-cn/target/";
    static final String LIB_EN_PATH = PREFIX + "reloading-demo/library/library-en/target/";

    private static final Map<String, ClassLoader> clMap = new HashMap<>(3);

    static ClassLoader getClassLoader(String type) throws MalformedURLException {
        if (clMap.get(type) == null) {
            File dir;
            if ("cn".equals(type)) {
                dir = new File(LIB_CN_PATH);
                String cnLibPath =
                        Arrays.stream(Objects.requireNonNull(dir.list()))
                                .filter(s -> s.endsWith("jar-with-dependencies.jar"))
                                .findFirst()
                                .get();
                URL cnUrl = new File(LIB_CN_PATH + cnLibPath).toURI().toURL();
                ClassLoader urlClassLoader = new URLClassLoader(new URL[]{cnUrl});
                clMap.put(type, urlClassLoader);
            } else if ("zh".equals(type)) {
                dir = new File(LIB_CN_PATH);
                String cnLibPath =
                        Arrays.stream(Objects.requireNonNull(dir.list()))
                                .filter(s -> s.endsWith("jar-with-dependencies.jar"))
                                .findFirst()
                                .get();
                URL cnUrl = new File(LIB_CN_PATH + cnLibPath).toURI().toURL();
                ClassLoader urlClassLoader = new URLClassLoader(new URL[]{cnUrl});
                clMap.put(type, urlClassLoader);
            } else {
                dir = new File(LIB_EN_PATH);
                String enLibPath =
                        Arrays.stream(Objects.requireNonNull(dir.list()))
                                .filter(s -> s.endsWith("jar-with-dependencies.jar"))
                                .findFirst()
                                .get();
                URL enUrl = new File(LIB_EN_PATH + enLibPath).toURI().toURL();
                ClassLoader urlClassLoader = new URLClassLoader(new URL[]{enUrl});
                clMap.put(type, urlClassLoader);
            }
        }
        return clMap.get(type);
    }
}
