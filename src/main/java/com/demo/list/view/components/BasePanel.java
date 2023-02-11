package com.demo.list.view.components;

import javax.swing.*;

import java.awt.*;

import static java.util.Arrays.stream;

public class BasePanel extends JComponent {

    public void addAll(Component... components) {
        stream(components).forEach(this::add);
    }

}