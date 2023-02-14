package com.demo.list.view.components;

import com.demo.list.configuration.language.AppProperties;
import com.demo.list.observer.Observer;
import com.demo.list.view.states.ObservableListState;

import java.awt.*;

import static com.demo.list.view.layouts.GridBagConstraintsBuilder.constraints;
import static java.awt.GridBagConstraints.BOTH;

public class LinkedListContainer extends BaseComponent implements Observer {

    private final ObservableListState observableListState;

    public LinkedListContainer(
            AppProperties props,
            ObservableListState observableListState
    ) {
        super(props);
        this.observableListState = observableListState;
        observableListState.addObserver(this);
        setLayout(new GridBagLayout());
        addChildrenComponents();
    }

    @Override
    public void update() {
        removeAll();
        addChildrenComponents();
        validate();
        repaint();
    }

    private Component linkedListVisualizationPanel() {
        return new ScrollableLinkedList(observableListState, string("text.empty.list.content"));
    }

    private void addChildrenComponents() {
        add(
                linkedListVisualizationPanel(),
                constraints(0, 0, 1, 1, BOTH, 1, 1)
        );
    }

}