package com.demo.list.util;

public class StringUtils {

    public static String capitalize(String string) {
        return initialInUpperCase(string) + restInLowerCase(string);
    }

    private static char initialInUpperCase(String string) {
        return string.charAt(0);
    }

    private static String restInLowerCase(String string) {
        return string.substring(1).toLowerCase();
    }

}
