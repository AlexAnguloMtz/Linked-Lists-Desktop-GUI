package com.demo.list.view.resources;

import com.demo.list.configuration.language.Language;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

import static java.lang.String.format;
import static javax.imageio.ImageIO.read;

public class Resources {

    private static final String FLAGS_DIRECTORY = "/img/flags/";
    private static final String ARROW_PATH = "/img/arrow.png";

    public static Component flagForLanguage(Language language) {
        return loadPicture(flagPathForLanguage(language));
    }

    public static Component arrow() {
        return loadPicture(ARROW_PATH);
    }

    private static JComponent loadPicture(String path) {
        try {
            var picture = read(resource(path));
            var labelWrapper = new JLabel(new ImageIcon(picture));
            labelWrapper.setAlignmentX(Label.CENTER_ALIGNMENT);
            return labelWrapper;
        } catch (IOException e) {
            throw new RuntimeException(format("Could not load image: %s", path));
        }
    }

    private static String flagPathForLanguage(Language language) {
        return switch (language) {
            case ENGLISH -> FLAGS_DIRECTORY + "uk.png";
            case SPANISH -> FLAGS_DIRECTORY + "spain.png";
            case FRENCH -> FLAGS_DIRECTORY + "france.jpg";
        };
    }

    private static InputStream resource(String path) {
        return Resources.class.getResourceAsStream(path);
    }

}