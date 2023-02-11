package com.demo.list.observer;

import java.util.Collection;

public interface Observable {

    void addObserver(Observer observer);

    void addAllObservers(Collection<Observer> observers);

    void notifyAllObservers();

}