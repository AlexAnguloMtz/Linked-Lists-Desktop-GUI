package com.demo.list.view.components;

import com.demo.list.configuration.language.TextProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static com.demo.list.view.layouts.GridBagConstraintsBuilder.constraints;
import static java.awt.Color.WHITE;
import static java.awt.Color.decode;
import static java.awt.Font.PLAIN;
import static java.awt.GridBagConstraints.BOTH;

public class ResetListButtons {

    public static Component create(
            TextProvider textProvider,
            ActionListener onNewAscendingList,
            ActionListener onNewDescendingList,
            ActionListener onNewUnsortedList
    ) {
        var panel = new JPanel();
        addButtons(panel, textProvider, onNewAscendingList, onNewDescendingList, onNewUnsortedList);
        return panel;
    }

    private static void addButtons(
            JPanel panel,
            TextProvider textProvider,
            ActionListener onNewAscendingList,
            ActionListener onNewDescendingList,
            ActionListener onNewUnsortedList
    ) {
        panel.setLayout(new GridBagLayout());
        panel.add(
                button(textProvider.text("button.new.ascending.list"), onNewAscendingList),
                constraints(0,0,1,1, BOTH,1,1)
        );

        panel.add(
                button(textProvider.text("button.new.descending.list"), onNewDescendingList),
                constraints(0,1,1,1, BOTH,1,1)
        );

        panel.add(
                button(textProvider.text("button.new.unsorted.list"), onNewUnsortedList),
                constraints(0,2,1,1, BOTH,1,1)
        );
    }

    private static JButton button(String text, ActionListener actionListener) {
        return ClickableButton.create(
                text,
                actionListener,
                decode("#b30000"),
                WHITE,
                new Font("Arial", PLAIN, 20)
        );
    }

}