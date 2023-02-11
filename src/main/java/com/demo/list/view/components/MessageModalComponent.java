package com.demo.list.view.components;

import javax.swing.*;
import java.awt.*;

import static java.awt.Font.BOLD;
import static javax.swing.JOptionPane.*;

public class MessageModalComponent {

    public void showError(String message) {
        showMessageDialog(null, label(message), "ERROR", ERROR_MESSAGE);
    }

    public void showMessage(String message) {
        showMessageDialog(null, label(message), "MESSAGE", INFORMATION_MESSAGE);
    }

    private JLabel label(String text) {
        var label = new JLabel(text);
        label.setFont(new Font("Arial", BOLD, 25));
        return label;
    }

}