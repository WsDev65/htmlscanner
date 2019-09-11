package com.html.scanner.util;

public class Pair<T, V> {

    private T t;
    private V v;


    public Pair(T t, V v) {
        this.t = t;
        this.v = v;
    }


    public T getKey() {
        return t;
    }

    public V getValue() {
        return v;
    }
}
