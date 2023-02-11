package com.demo.list.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.String.format;

public class PropertiesLoader {

    public Properties loadProperties(String fileName) {
        try (InputStream propertiesFile = getClass().getClassLoader().getResourceAsStream(fileName)) {
            Properties properties = new Properties();
            properties.load(propertiesFile);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(format("Could not load properties from file: %s", fileName));
        }
    }

}