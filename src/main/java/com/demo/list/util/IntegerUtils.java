package com.demo.list.util;

public class IntegerUtils {

    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

}
