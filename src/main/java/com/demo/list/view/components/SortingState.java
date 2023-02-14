package com.demo.list.view.components;

import com.demo.list.configuration.language.AppProperties;
import com.demo.list.observer.Observer;
import com.demo.list.util.Pair;
import com.demo.list.view.states.ObservableListState;

import javax.swing.*;

import java.awt.*;
import java.util.Map;
import java.util.List;
import java.util.function.Predicate;

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
        render(observableListState);
    }

    @Override
    public void update() {
        removeAll();
        render(observableListState);
        validate();
        repaint();
    }

    private void render(ObservableListState state) {
        setLayout(new BorderLayout());
        configureLayoutForState(evaluateState(state));
    }

    private State evaluateState(ObservableListState state) {
        return stateTests().stream()
                .filter(test -> test.apply(state))
                .map(StatePredicate::state)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Cannot determine state for this component"));
    }

    private List<StatePredicate> stateTests() {
        return List.of(
                new StatePredicate(this::isListEmpty, State.EMPTY),
                new StatePredicate(this::allEqual, State.ALL_EQUAL),
                new StatePredicate(this::isSortedAscending, State.ASCENDING),
                new StatePredicate(this::isSortedDescending, State.DESCENDING),
                new StatePredicate(state -> true, State.UNSORTED)
        );
    }

    private boolean isListEmpty(ObservableListState state) {
        return state.isListEmpty();
    }

    private boolean allEqual(ObservableListState state) {
        return state.allTheSame();
    }

    private boolean isSortedAscending(ObservableListState state) {
        return state.isSorted((x, y) -> (((Integer) x) - ((Integer) y)) <= 0);
    }

    private boolean isSortedDescending(ObservableListState state) {
        return state.isSorted((x, y) -> (((Integer) x) - ((Integer) y)) >= 0);
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


    private record StatePredicate(Predicate<ObservableListState> test, State state) {
        public boolean apply(ObservableListState state) {
            return test.test(state);
        }
    }

}