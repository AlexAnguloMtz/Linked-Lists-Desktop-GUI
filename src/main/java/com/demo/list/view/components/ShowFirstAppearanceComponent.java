package com.demo.list.view.components;

import com.demo.list.configuration.language.TextProvider;

import java.awt.*;
import java.util.function.Consumer;

public class ShowFirstAppearanceComponent {

    public static Component create(
            TextProvider textProvider,
            Consumer<String> textFieldValueConsumer
    ) {
        return ButtonWithTextField.create(
                textProvider.text("button.show.first.appearance"),
                textFieldValueConsumer
        );
    }

}