package com.demo.list.view.components;

import com.demo.list.configuration.language.AppProperties;
import com.demo.list.observer.Observer;
import com.demo.list.view.states.ObservableListState;

import java.awt.*;

import static com.demo.list.view.layouts.GridBagConstraintsBuilder.constraints;
import static java.awt.GridBagConstraints.BOTH;

public class LinkedListContainer extends BasePanel implements Observer {

    private final ObservableListState observableListState;
    private final AppProperties props;

    public LinkedListContainer(
            AppProperties textProvider,
            ObservableListState observableListState
    ) {
        this.props = textProvider;
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
        return new ScrollableLinkedList(observableListState, getText("text.empty.list.content"));
    }

    private void addChildrenComponents() {
        add(
                linkedListVisualizationPanel(),
                constraints(0, 0, 1, 1, BOTH, 1, 1)
        );
    }

    private String getText(String key) {
        return props.string(key);
    }

}