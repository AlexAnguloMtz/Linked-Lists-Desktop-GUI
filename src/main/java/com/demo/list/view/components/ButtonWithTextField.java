package com.demo.list.view.components;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

import static java.awt.Font.PLAIN;
import static javax.swing.SwingConstants.CENTER;

public class ButtonWithTextField {

    public static Component create(
            String buttonText,
            Color buttonBackground,
            Color buttonForeground,
            Consumer<String> textFieldValueConsumer
    ) {
        return componentWith(buttonText, buttonBackground, buttonForeground, textField(), textFieldValueConsumer);
    }

    private static Component componentWith(
            String buttonText,
            Color buttonBackground,
            Color buttonForeground,
            JTextField textField,
            Consumer<String> textFieldValueConsumer
    ) {
        return new Column(
                textField,
                button(buttonText, buttonBackground, buttonForeground, textField, textFieldValueConsumer)
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
            Color backgroundColor,
            Color foregroundColor,
            JTextField textField,
            Consumer<String> textFieldValueConsumer
    ) {
        return ClickableButton.create(
                buttonText,
                click -> handlePrimaryAction(textFieldValueConsumer, textField),
                backgroundColor,
                foregroundColor,
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