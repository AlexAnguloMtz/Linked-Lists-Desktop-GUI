package com.demo.list.view.components;

import com.demo.list.configuration.language.AppProperties;
import com.demo.list.observer.Observer;
import com.demo.list.view.states.ObservableListState;

import javax.swing.*;

import java.awt.*;

import static java.awt.Font.PLAIN;
import static javax.swing.SwingConstants.CENTER;

public class SortingState extends JPanel implements Observer {

    private final ObservableListState observableListState;
    private final AppProperties props;

    public SortingState(ObservableListState observableListState, AppProperties textProvider) {
        this.observableListState = observableListState;
        this.props = textProvider;
        observableListState.addObserver(this);
        initialize();
    }

    @Override
    public void update() {
        removeAll();
        initialize();
        validate();
        repaint();
    }

    private void initialize() {
        if (isListEmpty())
            handleEmptyListState();

        else if (allTheSame())
            handleAllTheSame();

        else if (isSortedAscending())
            handleAscendingSortedListState();

        else if (isSortedDescending())
            handleDescendingSortedListState();

        else
            handleUnsortedListState();
    }

    private boolean isListEmpty() {
        return observableListState.isListEmpty();
    }

    private boolean isSortedAscending() {
        return observableListState.isSorted((x, y) -> (((Integer) x) - ((Integer) y)) <= 0);
    }

    private boolean isSortedDescending() {
        return observableListState.isSorted((x, y) -> (((Integer) x) - ((Integer) y)) >= 0);
    }

    private void handleEmptyListState() {
        configureLayout(
            getText("text.description.list.empty"),
            neutralColor()
        );
    }

    private void handleAllTheSame() {
        configureLayout(
            getText("text.description.list.neutral"),
            neutralColor()
        );
    }

    private void handleAscendingSortedListState() {
        configureLayout(
            getText("text.description.list.ascending"),
            ascendingColor()
        );
    }

    private void handleDescendingSortedListState() {
        configureLayout(
            getText("text.description.list.descending"),
            descendingColor()
        );
    }

    private void handleUnsortedListState() {
        configureLayout(
                getText("text.description.list.unsorted"),
                unsortedColor()
        );
    }

    private void configureLayout(String text, Color background) {
        setBackground(background);
        var label = new JLabel(text);
        label.setFont(new Font("Arial", PLAIN, 30));
        label.setForeground(foregroundColor());
        label.setHorizontalAlignment(CENTER);
        setLayout(new BorderLayout());
        add(label, BorderLayout.CENTER);
    }

    private String getText(String key) {
        return props.string(key);
    }

    private boolean allTheSame() {
        return observableListState.allTheSame();
    }

    private Color neutralColor() {
        return props.color("neutral");
    }

    private Color ascendingColor() {
        return props.color("ascending");
    }

    private Color descendingColor() {
        return props.color("descending");
    }

    private Color unsortedColor() {
        return props.color("unsorted");
    }

    private Color foregroundColor() {
        return props.color("on.list.sorting.state");
    }

}