package com.demo.list.view.components;

import javax.swing.*;
import java.awt.*;

import static java.util.Arrays.stream;

public class Column extends JPanel {

    private static final int ROWS = 0;
    private static final int COLUMNS = 1;

    public Column(Component...components) {
        setLayout(new GridLayout(ROWS, COLUMNS));
        setAlignmentX(CENTER_ALIGNMENT);
        stream(components).forEach(this::add);
    }

}