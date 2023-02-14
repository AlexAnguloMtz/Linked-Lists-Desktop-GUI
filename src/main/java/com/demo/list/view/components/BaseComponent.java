package com.demo.list.view.components;

import com.demo.list.configuration.language.AppProperties;

import javax.swing.*;
import java.awt.*;

public class BaseComponent extends JPanel {

    private final AppProperties properties;

    public BaseComponent(AppProperties properties) {
        this.properties = properties;
    }

    protected String string(String key) {
        return properties.string(key);
    }

    protected Color color(String key) {
        return properties.color(key);
    }

    protected AppProperties getProps() {
        return properties;
    }

}
