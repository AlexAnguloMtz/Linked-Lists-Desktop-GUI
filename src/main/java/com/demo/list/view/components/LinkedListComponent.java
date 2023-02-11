package com.demo.list.view.components;

import com.demo.list.view.resources.Resources;
import com.demo.list.view.states.ObservableListState;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static java.awt.Color.WHITE;

public class LinkedListComponent extends JPanel {

    public LinkedListComponent(ObservableListState observableListState, String emptyListContent) {
        setLayout(new GridBagLayout());
        addNodesAndArrows(observableListState, emptyListContent);
        setBackground(backgroundColor());
        setPadding();
    }

    private void addNodesAndArrows(ObservableListState observableListState, String emptyListContent) {
        if (observableListState.listSize() == 0) {
            add(new VisualNodeComponent(emptyListContent));
            return;
        }
        if (observableListState.listSize() == 1) {
            add(new VisualNodeComponent(element(observableListState, 0)));
            return;
        }
        addNodesAndArrowsForSizeGreaterThanOne(observableListState);
    }

    private void addNodesAndArrowsForSizeGreaterThanOne(ObservableListState observableListState) {
        for (int i = 0; i < observableListState.listSize(); i++) {
            addComponent(new VisualNodeComponent(element(observableListState, i)));
            if (i < observableListState.listSize() - 1) {
                addComponent(arrow());
            }
        }
    }

    private void addComponent(Component component) {
        add(component, new GridBagConstraints());
    }

    private String element(ObservableListState observableListState, int index) {
        return String.valueOf(observableListState.getListElement(index));
    }

    private Component arrow() {
        Component arrow = Resources.arrow();
        arrow.setMaximumSize(new Dimension(70, 40));
        return arrow;
    }

    private void setPadding() {
        setBorder(new EmptyBorder(0, 20, 0, 20));
    }

    private Color backgroundColor() {
        return WHITE;
    }

}
