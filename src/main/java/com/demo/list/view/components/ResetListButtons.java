package com.demo.list.view.components;

import com.demo.list.configuration.language.AppProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static com.demo.list.view.layouts.GridBagConstraintsBuilder.constraints;
import static java.awt.Font.PLAIN;
import static java.awt.GridBagConstraints.BOTH;

public class ResetListButtons {

    public static Component create(
            AppProperties props,
            ActionListener onNewAscendingList,
            ActionListener onNewDescendingList,
            ActionListener onNewUnsortedList
    ) {
        var panel = new JPanel();
        addButtons(panel, props, onNewAscendingList, onNewDescendingList, onNewUnsortedList);
        return panel;
    }

    private static void addButtons(
            JPanel panel,
            AppProperties props,
            ActionListener onNewAscendingList,
            ActionListener onNewDescendingList,
            ActionListener onNewUnsortedList
    ) {
        panel.setLayout(new GridBagLayout());
        Color background = props.color("destructive.action");
        Color foreground = props.color("on.destructive.action");
        panel.add(
                button(
                        props.string("button.new.ascending.list"),
                        background,
                        foreground,
                        onNewAscendingList
                ),
                constraints(0,0,1,1, BOTH,1,1)
        );

        panel.add(
                button(
                        props.string("button.new.descending.list"),
                        background,
                        foreground,
                        onNewDescendingList),
                constraints(0,1,1,1, BOTH,1,1)
        );

        panel.add(
                button(
                        props.string("button.new.unsorted.list"),
                        background,
                        foreground,
                        onNewUnsortedList
                ),
                constraints(0,2,1,1, BOTH,1,1)
        );
    }

    private static JButton button(
            String text,
            Color background,
            Color foreground,
            ActionListener actionListener
    ) {
        return ClickableButton.create(
                text,
                actionListener,
                background,
                foreground,
                new Font("Arial", PLAIN, 20)
        );
    }

}