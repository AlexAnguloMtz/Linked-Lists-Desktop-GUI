package com.demo.list.view.components;

import javax.swing.*;
import java.awt.*;

public class SimpleTextField {

    public static JTextField create(Font font, int alignment) {
        var textField = new JTextField();
        textField.setFont(font);
        textField.setHorizontalAlignment(alignment);
        return textField;
    }

}