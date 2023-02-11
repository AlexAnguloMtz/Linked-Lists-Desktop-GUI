package com.demo.list.view.components;

import com.demo.list.view.states.ObservableListState;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class ScrollableLinkedListComponent extends JScrollPane {

    public ScrollableLinkedListComponent(
            ObservableListState observableListState,
            String emptyListText
    ) {
        super(
                new LinkedListComponent(observableListState, emptyListText),
                VERTICAL_SCROLLBAR_NEVER,
                HORIZONTAL_SCROLLBAR_AS_NEEDED
        );
        setPadding();
    }

    private void setPadding() {
        setBorder(new EmptyBorder(20, 20, 10, 20));
    }

}
