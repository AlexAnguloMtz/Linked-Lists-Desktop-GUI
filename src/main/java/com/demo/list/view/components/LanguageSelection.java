package com.demo.list.view.components;

import com.demo.list.configuration.language.Language;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static java.awt.Font.PLAIN;
import static java.awt.Label.CENTER;
import static javax.swing.Box.createRigidArea;
import static javax.swing.BoxLayout.X_AXIS;
import static javax.swing.BoxLayout.Y_AXIS;

public class LanguageSelection extends BasePanel {

    private static final int SPACE_BETWEEN_BUTTONS = 30;

    public LanguageSelection(
            ActionListener onSpanish,
            ActionListener onEnglish,
            ActionListener onFrench
    ) {
        setLayout(new BoxLayout(this, Y_AXIS));
        add(label());
        add(buttonsPanel(onSpanish, onEnglish, onFrench));
    }

    private Component label() {
        return LabelBuilder.withText("Select your language")
                          .font("Arial", PLAIN, 38)
                          .alignment(CENTER)
                          .maxSize(500, 300)
                          .build();
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