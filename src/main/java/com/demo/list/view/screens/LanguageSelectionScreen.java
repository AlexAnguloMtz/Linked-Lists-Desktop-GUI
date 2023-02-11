package com.demo.list.view.screens;

import com.demo.list.view.components.LanguageSelectionComponent;

import java.awt.*;
import java.awt.event.ActionListener;

import static java.awt.BorderLayout.PAGE_START;
import static java.awt.Font.BOLD;
import static java.awt.Label.CENTER;
import static javax.swing.BorderFactory.createEmptyBorder;

public class LanguageSelectionScreen extends Screen {

    private static final int TOP_PADDING = 20;

    public LanguageSelectionScreen(
            ActionListener onSpanish,
            ActionListener onEnglish,
            ActionListener onFrench
    ) {
        setLayout(new BorderLayout());
        addScreenTitle();
        setPadding();
        addLanguageSelectionComponent(onSpanish, onEnglish, onFrench);
    }

    private void addScreenTitle() {
        var label = new Label("Linked Lists Demonstration");
        label.setAlignment(CENTER);
        label.setFont(new Font("Arial", BOLD, 40));
        add(label, PAGE_START);
    }

    private void setPadding() {
        setBorder(createEmptyBorder(TOP_PADDING, 0, 0, 0));
    }

    private void addLanguageSelectionComponent(
            ActionListener onSpanish, ActionListener onEnglish, ActionListener onFrench) {

        add(
                new LanguageSelectionComponent(onSpanish, onEnglish, onFrench),
                BorderLayout.CENTER
        );

    }

    @Override
    public String getLayoutId() {
        return "LANGUAGE_SELECTION_SCREEN";
    }
}