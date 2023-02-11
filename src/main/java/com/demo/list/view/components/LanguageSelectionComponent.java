package com.demo.list.view.components;

import com.demo.list.configuration.language.Language;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static java.awt.Font.PLAIN;
import static javax.swing.Box.createRigidArea;
import static javax.swing.BoxLayout.X_AXIS;
import static javax.swing.BoxLayout.Y_AXIS;

public class LanguageSelectionComponent extends BasePanel {

    private static final int SPACE_BETWEEN_BUTTONS = 30;

    public LanguageSelectionComponent(
            ActionListener onSpanish,
            ActionListener onEnglish,
            ActionListener onFrench
    ) {
        setLayout(new BoxLayout(this, Y_AXIS));
        add(label());
        add(buttonsPanel(onSpanish, onEnglish, onFrench));
    }

    private Label label() {
        var label = new Label("Select your language");
        label.setAlignment(Label.CENTER);
        label.setFont(new Font("Arial", PLAIN, 38));
        label.setMaximumSize(new Dimension(500, 300));
        return label;
    }

    private BasePanel buttonsPanel(
            ActionListener onSpanish,
            ActionListener onEnglish,
            ActionListener onFrench
    ) {
        var buttonsPanel = new BasePanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, X_AXIS));
        buttonsPanel.addAll(
                LanguageButton.create(Language.ENGLISH, onEnglish),
                spaceBetweenButtons(),
                LanguageButton.create(Language.SPANISH, onSpanish),
                spaceBetweenButtons(),
                LanguageButton.create(Language.FRENCH, onFrench)
        );
        return buttonsPanel;
    }

    private Component spaceBetweenButtons() {
        return createRigidArea(new Dimension(SPACE_BETWEEN_BUTTONS, 0));
    }

}