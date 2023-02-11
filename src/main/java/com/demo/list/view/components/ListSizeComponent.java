package com.demo.list.view.components;

import com.demo.list.observer.Observer;
import com.demo.list.view.states.ObservableListState;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;
import static java.awt.Color.WHITE;
import static java.awt.Font.PLAIN;

public class ListSizeComponent extends JPanel implements Observer {

    private final ObservableListState state;
    private final String text;

    public ListSizeComponent(ObservableListState state, String text) {
        this.state = state;
        this.text = text;
        state.addObserver(this);
        configureLayout();
        addChildren();
    }

    @Override
    public void update() {
        removeAll();
        addChildren();
        validate();
        repaint();
    }

    private void configureLayout() {
        setLayout(new BorderLayout());
        setBackground(WHITE);
    }

    private void addChildren() {
        add(counter(), CENTER);
        add(description(), SOUTH);
    }

    private JLabel counter() {
        var label = new JLabel(String.valueOf(state.listSize()));
        label.setFont(new Font("Arial", PLAIN, 70));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    private JLabel description() {
        var label = new JLabel(text);
        label.setFont(new Font("Arial", PLAIN, 30));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(new EmptyBorder(0, 0, 20, 0));
        return label;
    }

}