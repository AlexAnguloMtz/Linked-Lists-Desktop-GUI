package com.demo.list.configuration.language;

import java.awt.*;
import java.util.Properties;

import static java.awt.Color.decode;

public class AppProperties {

    private final Properties spanishProperties;
    private final Properties englishProperties;
    private final Properties frenchProperties;
    private final Properties colorProperties;
    private Properties activeLanguageProperties;

    public AppProperties(
            Properties englishProperties,
            Properties spanishProperties,
            Properties frenchProperties,
            Properties colorProperties
    ) {
        this.englishProperties = englishProperties;
        this.spanishProperties = spanishProperties;
        this.frenchProperties = frenchProperties;
        this.colorProperties = colorProperties;
    }

    public void setLanguage(Language language) {
        this.activeLanguageProperties = switch (language) {
            case SPANISH -> spanishProperties;
            case ENGLISH -> englishProperties;
            case FRENCH -> frenchProperties;
        };
    }

    public String string(String key) {
        return (String) activeLanguageProperties.get(key);
    }

    public Color color(String key) {
        return decode((String) colorProperties.get(key));
    }

}