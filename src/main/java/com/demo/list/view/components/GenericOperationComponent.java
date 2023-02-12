package com.demo.list.view.components;


import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

import static java.awt.Color.WHITE;
import static java.awt.Color.decode;
import static java.awt.Font.PLAIN;
import static javax.swing.SwingConstants.CENTER;

public class GenericOperationComponent {

    public static Component create(
            String buttonText,
            Consumer<String> textFieldValueConsumer
    ) {
        return componentWith(buttonText, textField(), textFieldValueConsumer);
    }

    private static Component componentWith(
            String buttonText,
            JTextField textField,
            Consumer<String> textFieldValueConsumer
    ) {
        return new Column(
                textField,
                button(buttonText, textField, textFieldValueConsumer)
        );
    }

    private static JTextField textField() {
        return SimpleTextField.create(
                new Font("Arial", PLAIN, 35),
                CENTER
        );
    }

    private static Component button(
            String buttonText,
            JTextField textField,
            Consumer<String> textFieldValueConsumer
    ) {
        return ClickableButton.create(
                buttonText,
                click -> handlePrimaryAction(textFieldValueConsumer, textField),
                decode("#490049"),
                WHITE,
                new Font("Arial", PLAIN, 23)
        );
    }

    private static void handlePrimaryAction(
            Consumer<String> textFieldValueConsumer,
            JTextField textField
    ) {
        textFieldValueConsumer.accept(textField.getText());
        textField.setText("");
    }

}