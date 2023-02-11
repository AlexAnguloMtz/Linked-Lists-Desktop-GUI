package com.demo.list.configuration.language;

import com.demo.list.util.StringUtils;

public enum Language {

    SPANISH,
    FRENCH,
    ENGLISH;

    public String getLanguageName() {
        return StringUtils.capitalize(name());
    }

}