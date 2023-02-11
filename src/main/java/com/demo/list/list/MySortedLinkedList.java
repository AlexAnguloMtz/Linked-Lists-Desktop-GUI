package com.demo.list.list;

import java.util.function.Predicate;

public class MySortedLinkedList<T extends Comparable<T>> implements MyLinkedList<Comparable<T>> {

    private final MyLinkedList<Comparable<T>> list;
    private final Predicate<Integer> comparisonTester;

    public MySortedLinkedList(Predicate<Integer> comparisonTester) {
        this.list = new MyLinkedListImplementation<>();
        this.comparisonTester = comparisonTester;
    }

    @Override
    public void add(Comparable<T> element) {
        if (isEmpty()) {
            list.add(element);
            return;
        }
        list.add(calculateIndex(element), element);
    }

    @Override
    public void add(int index, Comparable<T> comparable) {
        throw new UnsupportedOperationException(
            "Cannot add element at a given index because this is a sorted list");
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void remove(int index) {
        list.remove(index);
    }

    @Override
    public boolean contains(Comparable<T> element) {
        return list.contains(element);
    }

    @Override
    public int indexOf(Comparable<T> element) {
        return list.indexOf(element);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Comparable<T> get(int index) {
        return list.get(index);
    }

    private int calculateIndex(Comparable<T> element) {
        for (int i = 0; i < size(); i++) {
            if (testComparison(get(i).compareTo((T) element))) {
                return i;
            }
        }
        return size();
    }

    private boolean testComparison(int comparisonValue) {
        return comparisonTester.test(comparisonValue);
    }

}
