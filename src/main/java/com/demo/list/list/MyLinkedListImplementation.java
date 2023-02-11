package com.demo.list.list;

import java.util.Comparator;
import java.util.function.BiFunction;

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
            removeHead();
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

    @Override
    public void sort(Comparator<T> comparator) {
        if (size() <= 1) return;
        for (Node<T> i = head; i.next != null; i = i.next) {
            for (Node<T> j = i.next; (j.previous != null && shouldSwitch(comparator, j.previous, j)); j = j.previous) {
                doSwitch(j.previous, j);
            }
        }
    }

    @Override
    public boolean isSorted(BiFunction<T, T, Boolean> comparator) {
        if (size() <= 1) return true;
        for (Node<T> current = head; current.next != null; current = current.next) {
            if (!comparator.apply(current.element, current.next.element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean allEqual() {
        if (size() <= 1) return true;
        T first = get(0);
        for (Node<T> current = head; current != null; current = current.next) {
            if (!first.equals(current.element)) {
                return false;
            }
        }
        return true;
    }

    private void addNewHead(T element) {
        if (head == null) {
            head = new Node<>(element);
            return;
        }
        Node<T> temp = head;
        head = new Node<>(element);
        head.next = temp;
        head.next.previous = head;
    }

    private void addForIndexGreaterThanZero(int index, T element) {
        Node<T> current = head;
        for (int i = 0; i < size(); i++) {
            if ((i + 1) == index) {
                Node<T> temp = current.next;
                current.next = new Node<>(element);
                current.next.previous = current;
                current.next.next = temp;
                if (current.next.next != null) {
                    current.next.next.previous = current.next;
                }
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
        current.next.previous = current;
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
                if (current.next.next != null) {
                    current.next.next.previous = current;
                }
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

    private boolean shouldSwitch(Comparator<T> comparator, Node<T> first, Node<T> second) {
        return comparator.compare(first.element, second.element) > 0;
    }

    private void doSwitch(Node<T> first, Node<T> second) {
        T temp = first.element;
        first.element = second.element;
        second.element = temp;
    }

    private void removeHead() {
        if (size() == 1) {
            head = null;
        }
        else {
            head.next.previous = head.previous;
            head = head.next;
        }
    }

    private static class Node <T> {

        private T element;

        private Node<T> next;

        private Node<T> previous;

        public Node(T element) {
            this.element = element;
        }

        public T getData() {
            return element;
        }

        public boolean contains(T element) {
            return getData().equals(element);
        }

        @Override
        public String toString() {
            return format("Node{element=%s}", element);
        }

    }

}
