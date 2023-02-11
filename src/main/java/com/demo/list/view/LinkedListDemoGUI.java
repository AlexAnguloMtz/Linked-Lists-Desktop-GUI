package com.demo.list.view;

import com.demo.list.configuration.language.Language;
import com.demo.list.configuration.language.TextProvider;
import com.demo.list.view.screens.LanguageSelectionScreen;
import com.demo.list.view.screens.MainScreen;
import com.demo.list.view.screens.Screen;

import javax.swing.*;

public class LinkedListDemoGUI extends JFrame {

    private final WindowConfigurer windowConfigurer;
    private final ScreenManager screenManager;
    private final LanguageSelectionScreen languageConfigurationScreen;

    public LinkedListDemoGUI(String windowTitle, TextProvider textProvider) {
        this.windowConfigurer = new WindowConfigurer();
        this.screenManager = new ScreenManager();
        this.languageConfigurationScreen = createLanguageSelectionScreen(textProvider);
        setContentPane(screenManager.getContentPane());
        setActiveScreen(this.languageConfigurationScreen);
        configureWindow(windowTitle);
    }

    private LanguageSelectionScreen createLanguageSelectionScreen(TextProvider textProvider) {
        return new LanguageSelectionScreen(
                clickEvent -> showMainScreen(Language.SPANISH, textProvider),
                clickEvent -> showMainScreen(Language.ENGLISH, textProvider),
                clickEvent -> showMainScreen(Language.FRENCH, textProvider)
        );
    }

    private void showMainScreen(Language language, TextProvider textProvider) {
        textProvider.setLanguage(language);
        setActiveScreen(new MainScreen(textProvider));
    }

    private void configureWindow(String title) {
        windowConfigurer.configureWindow(this, title);
    }

    private void setActiveScreen(Screen screen) {
        screenManager.setActiveScreen(screen);
    }

}