package com.demo.list.util;

public class Decision {

    public static void decide(boolean condition, Runnable happy, Runnable error) {
        (condition ? happy : error).run();
    }

}