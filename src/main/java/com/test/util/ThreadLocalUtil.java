package com.test.util;

public class ThreadLocalUtil {

    //通过ThreadLocal对象
    private static final ThreadLocal THREAD_LOCAL = new ThreadLocal();

    //根据键获取值
    public static <T> T get() {
        return (T) THREAD_LOCAL.get();
    }

    //储存键值对
    public static void set(Object obj) {
        THREAD_LOCAL.set(obj);
    }

    //清除ThreadLocal，防止内存泄漏
    public static void remove() {
        THREAD_LOCAL.remove();
    }
}
