package com.demo.list.view.components;

import com.demo.list.configuration.language.Language;
import com.demo.list.view.resources.Resources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static java.awt.Font.PLAIN;
import static javax.swing.BorderFactory.createEmptyBorder;

class LanguageButton {

    public static Component create(Language language, ActionListener actionListener) {
        var button = ClickableButton.create();
        button.setMaximumSize(new Dimension(200, 100));
        button.add(flagFor(language));
        button.add(labelFor(language));
        button.addActionListener(actionListener);
        return button;
    }

    private static Component labelFor(Language language) {
        var label = new JLabel(language.getLanguageName());
        label.setFont(new Font("Arial", PLAIN, 20));
        label.setBorder(createEmptyBorder(0, 50, 0, 0));
        label.setOpaque(false);
        return label;
    }

    private static Component flagFor(Language language) {
        return Resources.flagForLanguage(language);
    }

}