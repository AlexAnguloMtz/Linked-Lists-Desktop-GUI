package com.demo.list.view.components;

import com.demo.list.configuration.language.AppProperties;
import com.demo.list.observer.Observer;
import com.demo.list.util.Pair;
import com.demo.list.view.states.ObservableListState;

import javax.swing.*;

import java.awt.*;
import java.util.Map;

import static java.awt.Font.PLAIN;
import static javax.swing.SwingConstants.CENTER;

public class SortingState extends BaseComponent implements Observer {

    private enum State {
        ALL_EQUAL, ASCENDING, DESCENDING, UNSORTED, EMPTY
    }

    private final ObservableListState observableListState;

    private final Map<State, Pair<String, Color>> renderConfigurations;

    public SortingState(ObservableListState observableListState, AppProperties props) {
        super(props);
        this.observableListState = observableListState;
        this.renderConfigurations = initializeRenderConfigurations();
        observableListState.addObserver(this);
        render();
    }

    @Override
    public void update() {
        removeAll();
        render();
        validate();
        repaint();
    }

    private void render() {
        setLayout(new BorderLayout());
        configureLayoutForState(evaluateState());
    }

    private State evaluateState() {
        if (isListEmpty()) return State.EMPTY;
        if (allEqual()) return State.ALL_EQUAL;
        if (isSortedAscending()) return State.ASCENDING;
        if (isSortedDescending()) return State.DESCENDING;
        return State.UNSORTED;
    }

    private boolean isListEmpty() {
        return observableListState.isListEmpty();
    }

    private boolean allEqual() {
        return observableListState.allTheSame();
    }

    private boolean isSortedAscending() {
        return observableListState.isSorted((x, y) -> (((Integer) x) - ((Integer) y)) <= 0);
    }

    private boolean isSortedDescending() {
        return observableListState.isSorted((x, y) -> (((Integer) x) - ((Integer) y)) >= 0);
    }

    private void configureLayoutForState(State state) {
        setBackground(backgroundFor(state));
        add(labelFor(state), BorderLayout.CENTER);
    }

    private Color backgroundFor(State state) {
        return config(state).right();
    }

    private JLabel labelFor(State state) {
        var label = new JLabel(textFor(state));
        label.setFont(new Font("Arial", PLAIN, 30));
        label.setForeground(foregroundColor());
        label.setHorizontalAlignment(CENTER);
        return label;
    }

    private String textFor(State state) {
        return config(state).left();
    }

    private Pair<String, Color> config(State state) {
        return renderConfigurations.get(state);
    }

    private Map<State, Pair<String, Color>> initializeRenderConfigurations() {
        return Map.of(
                State.EMPTY, config("text.description.list.empty", "neutral"),
                State.ALL_EQUAL, config("text.description.list.neutral", "neutral"),
                State.ASCENDING, config("text.description.list.ascending", "ascending"),
                State.DESCENDING, config("text.description.list.descending", "descending"),
                State.UNSORTED, config("text.description.list.unsorted", "unsorted")
        );
    }

    @SuppressWarnings("unchecked")
    private Pair<String, Color> config(String stringKey, String colorKey) {
        return Pair.of(string(stringKey), color(colorKey));
    }

    private Color foregroundColor() {
        return color("on.list.sorting.state");
    }

}