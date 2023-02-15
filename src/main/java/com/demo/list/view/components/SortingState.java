package com.demo.list.view.components;

import com.demo.list.configuration.language.AppProperties;
import com.demo.list.observer.Observer;
import com.demo.list.view.states.ObservableListState;

import javax.swing.*;

import java.awt.*;

import static java.awt.Font.PLAIN;
import static javax.swing.SwingConstants.CENTER;

public class SortingState extends BaseComponent implements Observer {

    private record Layout(String text, Color background) {}

    private final ObservableListState observableListState;

    public SortingState(ObservableListState observableListState, AppProperties props) {
        super(props);
        this.observableListState = observableListState;
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
        configureWith(layoutFor(state));
    }

    private Layout layoutFor(ObservableListState state) {
        if (state.listIsEmpty())
            return layout("text.description.list.empty", "neutral");

        if (state.allEqual())
            return layout("text.description.list.neutral", "neutral");

        if (state.isSortedAscending())
            return layout("text.description.list.ascending", "ascending");

        if (state.isSortedDescending())
            return layout("text.description.list.descending", "descending");

        return layout("text.description.list.unsorted", "unsorted");
    }

    private Layout layout(String stringKey, String colorKey) {
        return new Layout(string(stringKey), color(colorKey));
    }

    private void configureWith(Layout layout) {
        setBackground(layout.background());
        add(labelFor(layout.text()), BorderLayout.CENTER);
    }

    private JLabel labelFor(String text) {
        var label = new JLabel(text);
        label.setFont(new Font("Arial", PLAIN, 30));
        label.setForeground(foregroundColor());
        label.setHorizontalAlignment(CENTER);
        return label;
    }

    private Color foregroundColor() {
        return color("on.list.sorting.state");
    }

}