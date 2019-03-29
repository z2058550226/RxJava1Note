package com.suikajy.rxjavanote.util;

public interface LogUtils {
    String defaultSeperator = "\t|\t";

    static void log(String msg) {
        System.out.print(System.currentTimeMillis() % 100000);
        System.out.print(defaultSeperator);
        System.out.print("thread - " + Thread.currentThread().getId());
        System.out.print(defaultSeperator);
        System.out.println(msg);
    }
}
