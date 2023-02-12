package com.demo.list.view.components;

import com.demo.list.configuration.language.AppProperties;
import com.demo.list.view.controllers.LinkedListOperationsController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static com.demo.list.view.layouts.GridBagConstraintsBuilder.constraints;
import static java.awt.Font.PLAIN;
import static java.awt.GridBagConstraints.BOTH;

public class IntegersOperations {

    public static JComponent create(
            AppProperties props,
            LinkedListOperationsController controller
    ) {
        var panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        Color background = props.color("primary.action");
        Color foreground = props.color("on.primary.action");
        panel.add(
                button(
                        props.string("button.remove.even"),
                        background,
                        foreground,
                        controller::onRemoveEvenNumbers
                ),
                constraints(0, 0, 1, 1, BOTH, 1, 1)
        );
        panel.add(
                button(
                        props.string("button.remove.odd"),
                        background,
                        foreground,
                        controller::onRemoveOddNumbers
                ),
                constraints(0, 1, 1, 1, BOTH, 1, 1)
        );
        panel.add(
                button(
                        props.string("button.remove.positive"),
                        background,
                        foreground,
                        controller::onRemovePositiveNumbers
                ),
                constraints(0, 2, 1, 1, BOTH, 1, 1)
        );
        panel.add(
                button(
                        props.string("button.remove.negative"),
                        background,
                        foreground,
                        controller::onRemoveNegativeNumbers
                ),
                constraints(0, 3, 1, 1, BOTH, 1, 1)
        );
        return panel;
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
