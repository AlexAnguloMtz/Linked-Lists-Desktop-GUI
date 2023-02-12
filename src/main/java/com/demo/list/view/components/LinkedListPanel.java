package com.demo.list.view.components;

import com.demo.list.configuration.language.TextProvider;
import com.demo.list.observer.Observer;
import com.demo.list.view.states.ObservableListState;

import java.awt.*;

import static com.demo.list.view.layouts.GridBagConstraintsBuilder.constraints;
import static java.awt.GridBagConstraints.BOTH;

public class LinkedListPanel extends BasePanel implements Observer {

    private final ObservableListState observableListState;
    private final TextProvider textProvider;

    public LinkedListPanel(
            TextProvider textProvider,
            ObservableListState observableListState
    ) {
        this.textProvider = textProvider;
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
        add(linkedListVisualizationPanel(), constraints(0, 0, 1, 1, BOTH, 1, 1));
    }

    private String getText(String key) {
        return textProvider.text(key);
    }

}