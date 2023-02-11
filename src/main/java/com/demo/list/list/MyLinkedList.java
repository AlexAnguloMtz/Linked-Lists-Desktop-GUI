package com.demo.list.list;

import java.util.Comparator;

public interface MyLinkedList <T> {

    void add(T element);

    void add(int index, T element);

    int size();

    void remove(int index);

    boolean contains (T element);

    int indexOf(T element);

    boolean isEmpty();

    T get(int index);

    void sort(Comparator<T> comparator);

}