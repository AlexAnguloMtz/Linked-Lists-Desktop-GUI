package com.demo.list.list;

import static java.lang.String.format;

public class MyLinkedListImplementation<T> implements MyLinkedList<T> {

    private Node<T> head;
    private int size;

    @Override
    public void add(T element) {
        if (isEmpty()) {
            head = new Node<>(element);
        }
        else {
            addForSizeGreaterThanZero(element);
        }
        size++;
    }

    @Override
    public void add(int index, T element) {
        if (index == 0) {
            addNewHead(element);
        }
        else {
            checkIsInBoundsInclusive(index);
            addForIndexGreaterThanZero(index, element);
        }
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void remove(int index) {
        checkIsInBoundsExclusive(index);
        if (index == 0) {
            head = head.next;
        }
        else {
            removeForIndexGreaterThanZero(index);
        }
        size--;
    }


    @Override
    public boolean contains(T element) {
        Node<T> current = head;
        for (int i = 0; i < size(); i++) {
            if (current.contains(element)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public int indexOf(T element) {
        Node<T> current = head;
        for (int i = 0; i < size(); i++) {
            if (current.contains(element)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public T get(int index) {
        checkIsInBoundsExclusive(index);
        return (index == 0) ? head.getData() : getForIndexGreaterThanZero(index);
    }

    private void addNewHead(T element) {
        Node<T> temp = head;
        head = new Node<>(element);
        head.next = temp;

    }
    private void addForIndexGreaterThanZero(int index, T element) {
        Node<T> current = head;
        for (int i = 0; i < size(); i++) {
            if ((i + 1) == index) {
                Node<T> temp = current.next;
                current.next = new Node<>(element);
                current.next.next = temp;
                break;
            }
            current = current.next;
        }
    }

    private void addForSizeGreaterThanZero(T element) {
        Node<T> current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new Node<>(element);
    }

    private void checkIsInBoundsExclusive(int index) {
        if (index >= size()) {
            throw new IndexOutOfBoundsException(
                    format("Illegal index. List size = %s, Index received = %s", size(), index));
        }
    }

    private void checkIsInBoundsInclusive(int index) {
        if (index > size()) {
            throw new IndexOutOfBoundsException(
                    format("Illegal index. List size = %s, Index received = %s", size(), index));
        }
    }

    private void removeForIndexGreaterThanZero(int index) {
        Node<T> current = head;
        for (int i = 0; i < size(); i++) {
            if (i + 1 == index) {
                current.next = current.next.next;
                break;
            }
            current = current.next;
        }
    }

    private T getForIndexGreaterThanZero(int index) {
        Node<T> current = head;
        for (int i = 0; i < size(); i++) {
            if (i == index) {
                return current.getData();
            }
            current = current.next;
        }
        throw new IllegalStateException(format("Could not retrieve element at index %s", index));
    }

    private static class Node <T> {

        private T element;

        private Node<T> next;

        public Node(T element) {
            this.element = element;
        }

        public T getData() {
            return element;
        }

        public boolean contains(T element) {
            return getData().equals(element);
        }
    }

}
