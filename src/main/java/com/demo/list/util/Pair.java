package com.demo.list.util;

public class Pair<T, U> {

    private final T left;
    private final U right;

    private Pair(T t, U u) {
        this.left = t;
        this.right = u;
    }

    public static <T, U > Pair of(T t, U u) {
        return new Pair<>(t, u);
    }

    public T left() {
        return left;
    }

    public U right() {
        return right;
    }

}
