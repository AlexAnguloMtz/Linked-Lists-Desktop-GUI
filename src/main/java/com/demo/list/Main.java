package com.demo.list;

import com.demo.list.configuration.PropertiesLoader;
import com.demo.list.view.LinkedListDemoGUI;
import com.demo.list.configuration.language.TextProvider;

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
                textProvider()
        );
    }

    private static TextProvider textProvider() {
        var propertiesLoader = new PropertiesLoader();
        return new TextProvider(
                propertiesLoader.loadProperties("text/english.txt"),
                propertiesLoader.loadProperties("text/spanish.txt"),
                propertiesLoader.loadProperties("text/french.txt")
        );
    }

}