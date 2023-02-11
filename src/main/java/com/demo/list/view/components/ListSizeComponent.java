package com.demo.list.view.components;

import com.demo.list.observer.Observer;
import com.demo.list.view.states.ObservableListState;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static java.awt.BorderLayout.CENTER;
import static java.awt.Color.WHITE;
import static java.awt.Font.PLAIN;

public class ListSizeComponent extends JPanel implements Observer {

    private final ObservableListState state;

    public ListSizeComponent(ObservableListState state) {
        this.state = state;
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
    }

    private JLabel counter() {
        var label = new JLabel(String.valueOf(state.listSize()));
        label.setFont(new Font("Arial", PLAIN, 70));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

}