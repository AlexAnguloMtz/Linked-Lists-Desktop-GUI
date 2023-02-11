package com.demo.list.view.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static java.awt.Color.*;
import static java.awt.Font.PLAIN;
import static javax.swing.BorderFactory.createMatteBorder;
import static javax.swing.BoxLayout.X_AXIS;

public class VisualNodeComponent extends JPanel {

    private final String content;

    public VisualNodeComponent(String theContent) {
        this.content = theContent;
        setLayout(new BoxLayout(this, X_AXIS));
        add(leftPanel(content));
        add(rightPanel());
    }

    @Override
    public String toString() {
        return "VisualNodeComponent{" +
                "content='" + content + '\'' +
                '}';
    }

    private Component leftPanel(String theContent) {
        var leftPanel = new JPanel();
        leftPanel.setBorder(createMatteBorder(2, 2, 2, 1, BLACK));
        leftPanel.add(content(theContent));
        leftPanel.setBackground(leftPanelBackground());
        return leftPanel;
    }

    private Component rightPanel() {
        var rightPanel = new JPanel();
        rightPanel.add(content(null));
        rightPanel.setBorder(createMatteBorder(2, 1, 2, 2, BLACK));
        rightPanel.setBackground(rightPanelBackground());
        return rightPanel;
    }

    private Component content(String theContent) {
        var content = new JLabel(theContent);
        content.setFont(new Font("Arial", PLAIN, 30));
        content.setBorder(new EmptyBorder(2, 6, 2, 6));
        content.setForeground(foreground());
        return content;
    }

    private Color foreground() {
        return WHITE;
    }

    private Color leftPanelBackground() {
        return decode("#088587");
    }

    private Color rightPanelBackground() {
        return decode("#28BCC1");
    }

}