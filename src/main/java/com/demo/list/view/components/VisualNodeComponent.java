package com.demo.list.view.components;

import com.demo.list.view.resources.Resources;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static com.demo.list.view.layouts.GridBagConstraintsBuilder.constraints;
import static java.awt.Color.*;
import static java.awt.Font.PLAIN;
import static java.awt.GridBagConstraints.BOTH;
import static java.lang.String.format;
import static javax.swing.BorderFactory.createMatteBorder;
import static javax.swing.SwingConstants.CENTER;

public class VisualNodeComponent {

    public static Component withArrows(String content, int index) {
        var panel = initialPanel();
        addPanelsWithArrows(panel, content, index);
        return panel;
    }

    public static Component withoutArrows(String content, int index) {
        var panel = initialPanel();
        addPanelsWithoutArrow(panel, content, index);
        return panel;
    }

    private static void addPanelsWithArrows(JPanel panel, String content, int index) {
        panel.add(
            arrowsPanel(),
            constraints(0, 0, 1, 1, BOTH, 1, 1)
        );
        panel.add(
            leftPointerPanel(),
            constraints(1, 0, 1, 1, BOTH, 0.3, 1)
        );
        panel.add(
            nodeDataPanel(content),
            constraints(2, 0, 1, 1, BOTH, 0.3, 1)
        );
        panel.add(
            rightPointerPanel(),
            constraints(3, 0, 1, 1, BOTH, 0.3, 1)
        );
        panel.add(
            emptyInvisiblePanel(),
            constraints(0, 1, 1, 1, BOTH, 0.5, 1)
        );
        panel.add(
            indexPanel(index),
            constraints(1, 1, 3, 1, BOTH, 0.5, 1)
        );
    }

    private static void addPanelsWithoutArrow(JPanel panel, String content, int index) {
        panel.add(
            leftPointerPanel(),
            constraints(0, 0, 1, 1, BOTH, 1, 1)
        );
        panel.add(
            nodeDataPanel(content),
            constraints(1,0,1,1,BOTH,1,1)
        );
        panel.add(
            rightPointerPanel(),
            constraints(2,0,1,1,BOTH,1,1)
        );
        panel.add(
            indexPanel(index),
            constraints(0,1,3,1,BOTH,1,1)
        );
    }

    private static Component nodeDataPanel(String theContent) {
        var leftPanel = new JPanel();
        leftPanel.setBorder(createMatteBorder(2, 2, 2, 1, BLACK));
        leftPanel.add(content(theContent));
        leftPanel.setBackground(leftPanelBackground());
        return leftPanel;
    }

    private static Component leftPointerPanel() {
        return pointerPanel(2, 0);
    }

    private static Component rightPointerPanel() {
        return pointerPanel(1, 2);
    }

    private static Component pointerPanel(int leftBorder, int rightBorder) {
        var panel = new JPanel();
        panel.add(content(null));
        panel.setBorder(createMatteBorder(2, leftBorder, 2, rightBorder, BLACK));
        panel.setBackground(rightPanelBackground());
        return panel;
    }

    private static Component indexPanel(int index) {
        var panel = new JPanel();
        var label = new JLabel(format("[%d]", index));
        label.setFont(new Font("Arial", PLAIN, 25));
        label.setHorizontalAlignment(CENTER);
        panel.add(label);
        panel.setBackground(indexPanelBackground());
        return panel;
    }

    private static Component content(String theContent) {
        var content = new JLabel(theContent);
        content.setFont(new Font("Arial", PLAIN, 30));
        content.setBorder(new EmptyBorder(2, 6, 2, 6));
        content.setForeground(foreground());
        return content;
    }

    private static Color foreground() {
        return WHITE;
    }

    private static Color leftPanelBackground() {
        return decode("#088587");
    }

    private static Color rightPanelBackground() {
        return decode("#28BCC1");
    }

    private static Color indexPanelBackground() {
        return WHITE;
    }

    private static JPanel initialPanel() {
        var panel = emptyInvisiblePanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(null);
        return panel;
    }

    private static JPanel emptyInvisiblePanel() {
        var panel = new JPanel();
        panel.setBackground(null);
        return panel;
    }

    private static Component arrowsPanel() {
        return Resources.arrow();
    }

}