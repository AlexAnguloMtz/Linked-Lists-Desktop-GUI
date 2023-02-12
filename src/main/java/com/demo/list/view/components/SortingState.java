package com.demo.list.view.components;

import com.demo.list.configuration.language.TextProvider;
import com.demo.list.observer.Observer;
import com.demo.list.view.states.ObservableListState;

import javax.swing.*;

import java.awt.*;

import static java.awt.Color.*;
import static java.awt.Font.PLAIN;
import static javax.swing.SwingConstants.CENTER;

public class SortingState extends JPanel implements Observer {

    private final ObservableListState observableListState;
    private final TextProvider textProvider;

    public SortingState(ObservableListState observableListState, TextProvider textProvider) {
        this.observableListState = observableListState;
        this.textProvider = textProvider;
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
        label.setForeground(WHITE);
        label.setHorizontalAlignment(CENTER);
        setLayout(new BorderLayout());
        add(label, BorderLayout.CENTER);
    }

    private String getText(String key) {
        return textProvider.get(key);
    }

    private boolean allTheSame() {
        return observableListState.allTheSame();
    }

    private Color neutralColor() {
        return decode("#36454F");
    }

    private Color ascendingColor() {
        return decode("#228B22");
    }

    private Color descendingColor() {
        return decode("#191970");
    }

    private Color unsortedColor() {
        return decode("#E49B0F");
    }

}