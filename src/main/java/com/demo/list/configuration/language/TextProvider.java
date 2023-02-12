package com.demo.list.configuration.language;

import java.util.Properties;

public class TextProvider {

    private final Properties spanishProperties;
    private final Properties englishProperties;
    private final Properties frenchProperties;
    private Properties activeLanguageProperties;

    public TextProvider(
            Properties englishProperties,
            Properties spanishProperties,
            Properties frenchProperties
    ) {
        this.englishProperties = englishProperties;
        this.spanishProperties = spanishProperties;
        this.frenchProperties = frenchProperties;
    }

    public void setLanguage(Language language) {
        this.activeLanguageProperties = switch (language) {
            case SPANISH -> spanishProperties;
            case ENGLISH -> englishProperties;
            case FRENCH -> frenchProperties;
        };
    }

    public String text(String key) {
        return (String) activeLanguageProperties.get(key);
    }

}