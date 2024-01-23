package org.feuyeux.reloading.demo;

public class MyClassLoader extends ClassLoader{
    public MyClassLoader(ClassLoader parent) {
        super(parent);
    }
}
