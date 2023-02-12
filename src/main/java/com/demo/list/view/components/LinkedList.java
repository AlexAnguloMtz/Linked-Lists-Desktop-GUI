package com.demo.list.view.components;

import com.demo.list.view.states.ObservableListState;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static java.awt.Color.WHITE;

public class LinkedList extends JPanel {

    public LinkedList(ObservableListState observableListState, String emptyListContent) {
        setLayout(new GridBagLayout());
        addNodesAndArrows(observableListState, emptyListContent);
        setBackground(backgroundColor());
        setPadding();
    }

    private void addNodesAndArrows(ObservableListState observableListState, String emptyListContent) {
        if (observableListState.listSize() == 0) {
            add(Node.withoutArrows(emptyListContent, 0));
            return;
        }
        if (observableListState.listSize() == 1) {
            add(Node.withoutArrows(element(observableListState, 0), 0));
            return;
        }
        addNodesAndArrowsForSizeGreaterThanOne(observableListState);
    }

    private void addNodesAndArrowsForSizeGreaterThanOne(ObservableListState observableListState) {
        addComponent(Node.withoutArrows(element(observableListState, 0), 0));
        for (int i = 1; i < observableListState.listSize(); i++) {
            addComponent(Node.withArrows(element(observableListState, i), i));
        }
    }

    private void addComponent(Component component) {
        add(component, new GridBagConstraints());
    }

    private String element(ObservableListState observableListState, int index) {
        return String.valueOf(observableListState.getListElement(index));
    }

    private void setPadding() {
        setBorder(new EmptyBorder(0, 20, 0, 20));
    }

    private Color backgroundColor() {
        return WHITE;
    }

}
