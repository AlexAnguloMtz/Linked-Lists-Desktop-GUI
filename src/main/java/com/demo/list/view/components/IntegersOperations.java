package com.demo.list.view.components;

import com.demo.list.configuration.language.TextProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static com.demo.list.view.layouts.GridBagConstraintsBuilder.constraints;
import static java.awt.Color.*;
import static java.awt.Font.PLAIN;
import static java.awt.GridBagConstraints.BOTH;

public class IntegersOperations {

    public static JComponent create(
            TextProvider textProvider,
            ActionListener onRemoveEvenNumbers,
            ActionListener onRemoveOddNumbers,
            ActionListener onRemovePositiveNumbers,
            ActionListener onRemoveNegativeNumbers
    ) {
        var panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.add(
                button(textProvider.text("button.remove.even"), onRemoveEvenNumbers),
                constraints(0, 0, 1, 1, BOTH, 1, 1)
        );
        panel.add(
                button(textProvider.text("button.remove.odd"), onRemoveOddNumbers),
                constraints(0, 1, 1, 1, BOTH, 1, 1)
        );
        panel.add(
                button(textProvider.text("button.remove.positive"), onRemovePositiveNumbers),
                constraints(0, 2, 1, 1, BOTH, 1, 1)
        );
        panel.add(
                button(textProvider.text("button.remove.negative"), onRemoveNegativeNumbers),
                constraints(0, 3, 1, 1, BOTH, 1, 1)
        );
        return panel;
    }

    private static JButton button(String text, ActionListener actionListener) {
        return ClickableButton.create(
                text,
                actionListener,
                decode("#490049"),
                WHITE,
                new Font("Arial", PLAIN, 20)
        );
    }

}
