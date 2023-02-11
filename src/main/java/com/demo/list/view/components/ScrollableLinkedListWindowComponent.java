package com.demo.list.view.components;

import com.demo.list.view.states.ObservableListState;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.Dialog.ModalityType.DOCUMENT_MODAL;
import static java.awt.Font.BOLD;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class ScrollableLinkedListWindowComponent {

    public void show(
            String title,
            String header,
            String emptyListText,
            Component parent,
            ObservableListState observableListState
    ) {
        var dialog = new JDialog(null, title, DOCUMENT_MODAL);
        configure(dialog);
        addChildrenComponents(dialog, header, observableListState, emptyListText);
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }

    private void configure(JDialog dialog) {
        dialog.setLayout(new BorderLayout());
        dialog.setMinimumSize(new Dimension(700, 500));
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private JLabel configuredLabel(String text) {
        var label = new JLabel(text);
        label.setFont(new Font("Arial", BOLD, 30));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(new EmptyBorder(20, 0, 0, 0));
        return label;
    }

    private void addChildrenComponents(
            JDialog dialog,
            String header,
            ObservableListState observableListState,
            String emptyListText
    ) {
        dialog.add(configuredLabel(header), NORTH);
        dialog.add(new ScrollableLinkedListComponent(observableListState, emptyListText), CENTER);
    }

}
