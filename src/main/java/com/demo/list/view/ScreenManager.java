package com.demo.list.view;

import com.demo.list.view.screens.Screen;

import javax.swing.*;
import java.awt.*;

import static java.util.Arrays.asList;

class ScreenManager {

    private final JPanel contentPane;

    public ScreenManager() {
        this.contentPane = customContentPane();
    }

    public void setActiveScreen(Screen screen) {
        if (!containsScreen(screen)) {
            addNewScreen(screen);
        }
        showScreen(screen);
    }

    public void addNewScreen(Screen screen) {
        contentPane.add(screen, screen.getLayoutId());
    }

    public Container getContentPane() {
        return contentPane;
    }

    private void showScreen(Screen screen) {
        getLayoutManager().show(contentPane, screen.getLayoutId());
    }

    private CardLayout getLayoutManager() {
        return (CardLayout) contentPane.getLayout();
    }

    private boolean containsScreen(Screen aScreen) {
        return asList(getScreens()).contains(aScreen);
    }

    private Component[] getScreens() {
        return getContentPane().getComponents();
    }

    private JPanel customContentPane() {
        var contentPane = new JPanel();
        contentPane.setLayout(new CardLayout());
        return contentPane;
    }

}