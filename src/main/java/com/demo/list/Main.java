package com.demo.list;

import com.demo.list.configuration.PropertiesLoader;
import com.demo.list.view.LinkedListDemoGUI;
import com.demo.list.configuration.language.AppProperties;

import java.awt.*;

import static javax.swing.SwingUtilities.invokeLater;

public class Main {
    public static void main(String[] args) {
        invokeLater(Main::startGUI);
    }

    private static void startGUI() {
        gui().setVisible(true);
    }

    private static Component gui() {
        return new LinkedListDemoGUI(
                "Linked Lists Demonstration",
                appProperties()
        );
    }

    private static AppProperties appProperties() {
        var propertiesLoader = new PropertiesLoader();
        return new AppProperties(
                propertiesLoader.load("text/english.properties"),
                propertiesLoader.load("text/spanish.properties"),
                propertiesLoader.load("text/french.properties"),
                propertiesLoader.load("colors/colors.properties")
        );
    }

}