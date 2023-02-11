package com.demo.list.view.components;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;

import static java.awt.Cursor.HAND_CURSOR;
import static java.awt.Cursor.getPredefinedCursor;

public class ClickableButton {

    public static JButton create() {
        return create("");
    }

    public static JButton create(String text) {
        return create(text, null);
    }

    public static JButton create(String text, ActionListener actionListener) {
        return create(text, actionListener, null);
    }

    public static JButton create(String text, ActionListener actionListener, Font font) {
        return create(text, actionListener, null, font);
    }

    public static JButton create(String text, ActionListener actionListener, Color background, Font font) {
        return create(text, actionListener, background, null, font);
    }

    public static JButton create(String text, ActionListener actionListener, Color background, Color foreground) {
        return create(text, actionListener, background, foreground, null);
    }

    public static JButton create(
            String text,
            ActionListener actionListener,
            Color background,
            Color foreground,
            Font font
    ) {
        var button = new JButton(text);
        customizeCursor(button);
        button.addActionListener(actionListener);
        if (font != null) {
            button.setFont(font);
        }
        if (background != null) {
            button.setBackground(background);
        }
        if (foreground != null) {
            button.setForeground(foreground);
        }
        return button;
    }

    private static void customizeCursor(JButton button) {
        button.setCursor(getPredefinedCursor(HAND_CURSOR));
    }

}