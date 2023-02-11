package com.demo.list.view;

import javax.swing.*;

import java.awt.*;

import static java.awt.Frame.MAXIMIZED_BOTH;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

class WindowConfigurer {

    public void configureWindow(JFrame frame, String title) {
        frame.setTitle(title);
        frame.setExtendedState(MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setResizable(true);
        frame.setMinimumSize(new Dimension(950, 700));
    }

}
