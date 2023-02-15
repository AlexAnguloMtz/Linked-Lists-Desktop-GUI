package com.demo.list.view.states;

import com.demo.list.list.MyLinkedList;
import com.demo.list.list.MyLinkedListImplementation;
import com.demo.list.list.MySortedLinkedList;
import com.demo.list.observer.Observable;
import com.demo.list.observer.Observer;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class ObservableListState implements Observable {

    private MyLinkedList<Comparable<Integer>> list;
    private final Set<Observer> observers;

    public ObservableListState() {
        this.list = new MyLinkedListImplementation<>();
        this.observers = new HashSet<>();
    }

    public ObservableListState(MyLinkedList<Comparable<Integer>> list) {
        this.list = list;
        this.observers = new HashSet<>();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void addAllObservers(Collection<Observer> observers) {
        this.observers.addAll(observers);
    }

    @Override
    public void notifyAllObservers() {
        observers.forEach(Observer::update);
    }

    public void addElementToList(int number) {
        executeAndNotifyAllObservers(() -> list.add(number));
    }

    public void removeAtIndex(int index) {
        executeAndNotifyAllObservers(() -> list.remove(index));
    }

    public void resetWithNewUnsortedList() {
        executeAndNotifyAllObservers(() -> setList(new MyLinkedListImplementation<>()));
    }

    public void resetWithNewAscendingList() {
        executeAndNotifyAllObservers(() -> setList(ascendingList()));
    }

    public void resetWithNewDescendingList() {
        executeAndNotifyAllObservers(() -> setList(descendingList()));
    }

    public boolean listContains(int parseInt) {
        return list.contains(parseInt);
    }

    public int firstAppearance(int number) {
        return list.indexOf(number);
    }

    public int listSize() {
        return list.size();
    }

    public Comparable<Integer> getListElement(int index) {
        return list.get(index);
    }

    public ObservableListState withAllGreaterThan(int number) {
        return filter(num -> num.compareTo(number) > 0);
    }

    public ObservableListState withAllLessThan(int number) {
        return filter(num -> num.compareTo(number) < 0);
    }

    public ObservableListState filter(Predicate<Comparable<Integer>> selector) {
        MyLinkedList<Comparable<Integer>> list = new MyLinkedListImplementation<>();
        for (int i = 0; i < listSize(); i++) {
            Comparable<Integer> current = getListElement(i);
            if (selector.test(current)) {
                list.add(current);
            }
        }
        return new ObservableListState(list);
    }

    public boolean listIsEmpty() {
        return list.isEmpty();
    }

    public int max() {
        Comparable<Integer> max = list.get(0);
        for (int i = 0; i < listSize(); i++) {
            Comparable<Integer> current = getListElement(i);
            if (current.compareTo((Integer) max) > 0) {
                max = current;
            }
        }
        return (int) max;
    }

    public int min() {
        Comparable<Integer> min = list.get(0);
        for (int i = 0; i < listSize(); i++) {
            Comparable<Integer> current = getListElement(i);
            if (current.compareTo((Integer) min) < 0) {
                min = current;
            }
        }
        return (int) min;
    }

    public void removeAllThatMatch(Predicate<Integer> selector) {
        MyLinkedList<Comparable<Integer>> newList = new MyLinkedListImplementation<>();
        for (int i = 0; i < listSize(); i++) {
            var current = (Integer) getListElement(i);
            if (!selector.test(current)) {
                newList.add(current);
            }
        }
        executeAndNotifyAllObservers(() -> setList(newList));
    }

    public boolean isSorted(BiFunction<Comparable<Integer>, Comparable<Integer>, Boolean> comparator) {
        return list.isSorted(comparator);
    }

    public boolean allEqual() {
        return list.allEqual();
    }

    public boolean isSortedAscending() {
        return isSorted((x, y) -> (((Integer) x) - ((Integer) y)) <= 0);
    }

    public boolean isSortedDescending() {
        return isSorted((x, y) -> (((Integer) x) - ((Integer) y)) >= 0);
    }

    public boolean isOutOfBounds(int index) {
        return lastIndex() < index || index < 0;
    }

    public boolean isGreaterOrEqualToMax(int number) {
        return max() <= number;
    }

    public boolean isSmallerOrEqualToMin(int number) {
        return min() >= number;
    }

    private void setList(MyLinkedList<Comparable<Integer>> list) {
        this.list = list;
    }

    private void executeAndNotifyAllObservers(Runnable runnable) {
        runnable.run();
        notifyAllObservers();
    }

    private MyLinkedList<Comparable<Integer>> ascendingList() {
        return new MySortedLinkedList<>(comparison -> comparison > 0);
    }

    private MyLinkedList<Comparable<Integer>> descendingList() {
        return new MySortedLinkedList<>(comparison -> comparison < 0);
    }

    private int lastIndex() {
        return listSize() - 1;
    }

}