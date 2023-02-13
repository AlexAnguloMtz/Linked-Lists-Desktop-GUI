package com.demo.list.view.components;

import java.awt.*;

import static java.awt.Font.PLAIN;
import static java.awt.Label.CENTER;
import static java.util.Optional.ofNullable;

public class LabelBuilder {

    private String text;
    private Font font;
    private Dimension maximumSize;
    private Integer alignment;

    private LabelBuilder(String text) {
        this.text = text;
    }

    public static LabelBuilder withText(String text) {
        return new LabelBuilder(text);
    }

    public LabelBuilder font(String family, int type, int size) {
        this.font = new Font(family, type, size);
        return this;
    }

    public LabelBuilder alignment(int alignment) {
        this.alignment = alignment;
        return this;
    }

    public LabelBuilder maxSize(int width, int height) {
        this.maximumSize = new Dimension(width, height);
        return this;
    }

    public Label build() {
        var label = new Label(text);
        label.setFont(ofNullable(font).orElse(defaultFont()));
        label.setAlignment(ofNullable(alignment).orElse(CENTER));
        label.setMaximumSize(ofNullable(maximumSize).orElse(defaultMaxSize()));
        return label;
    }

    private Font defaultFont() {
        return new Font("Arial", PLAIN, 12);
    }

    private Dimension defaultMaxSize() {
        return new Dimension(200, 200);
    }

}