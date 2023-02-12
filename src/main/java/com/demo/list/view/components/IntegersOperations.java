package com.demo.list.view.components;

import com.demo.list.configuration.language.TextProvider;
import com.demo.list.view.controllers.LinkedListOperationsController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static com.demo.list.view.layouts.GridBagConstraintsBuilder.constraints;
import static java.awt.Color.*;
import static java.awt.Font.PLAIN;
import static java.awt.GridBagConstraints.BOTH;

public class IntegersOperations {

    public static JComponent create(
            TextProvider strings,
            LinkedListOperationsController controller
    ) {
        var panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.add(
                button(strings.get("button.remove.even"), controller::onRemoveEvenNumbers),
                constraints(0, 0, 1, 1, BOTH, 1, 1)
        );
        panel.add(
                button(strings.get("button.remove.odd"), controller::onRemoveOddNumbers),
                constraints(0, 1, 1, 1, BOTH, 1, 1)
        );
        panel.add(
                button(strings.get("button.remove.positive"), controller::onRemovePositiveNumbers),
                constraints(0, 2, 1, 1, BOTH, 1, 1)
        );
        panel.add(
                button(strings.get("button.remove.negative"), controller::onRemoveNegativeNumbers),
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
