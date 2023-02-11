package com.demo.list.view.components;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.function.Consumer;

import static java.awt.Color.WHITE;
import static java.awt.Color.decode;
import static java.awt.Font.PLAIN;
import static javax.swing.SwingConstants.CENTER;

public class GenericOperationComponent extends BasePanel {

    private final JTextField textField;
    private final Consumer<String> textFieldValueConsumer;

    public GenericOperationComponent(
            String buttonText,
            Consumer<String> textFieldValueConsumer
    ) {
        this.textField = textField();
        this.textFieldValueConsumer = textFieldValueConsumer;
        configureComponentLayout();
        addChildComponents(buttonText);
    }

    private void addChildComponents(String buttonText) {
        add(this.textField);
        add(button(buttonText));
    }

    private Component button(String buttonText) {
        var button = ClickableButton.create(buttonText);
        customizeLayout(button);
        customizeStyle(button);
        button.addActionListener(this::handlePrimaryAction);
        return button;
    }

    private void resetTextField() {
        textField.setText("");
    }

    private void handlePrimaryAction(ActionEvent actionEvent) {
        textFieldValueConsumer.accept(getTextFieldValue());
        resetTextField();
    }

    private JTextField textField() {
        var textField = new JTextField();
        customizeLayout(textField);
        return textField;
    }

    private void configureComponentLayout() {
        setLayout(new GridLayout(0, 1));
        setAlignmentX(CENTER_ALIGNMENT);
    }

    private void customizeLayout(JButton button) {
        button.setHorizontalAlignment(CENTER);
        button.setFont(new Font("Arial", PLAIN, 23));
    }

    private void customizeStyle(JButton button) {
        button.setBackground(decode("#490049"));
        button.setForeground(WHITE);
    }

    private void customizeLayout(JTextField textField) {
        textField.setHorizontalAlignment(CENTER);
        textField.setFont(new Font("Arial", PLAIN, 35));
    }

    private String getTextFieldValue() {
        return textField.getText();
    }

}