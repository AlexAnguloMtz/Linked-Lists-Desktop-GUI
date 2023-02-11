package com.demo.list.view.components;

import com.demo.list.configuration.language.TextProvider;

import java.awt.*;
import java.util.function.Consumer;

class AddElementToListComponent {

    static Component create(
            TextProvider textProvider,
            Consumer<String> textFieldValueConsumer
    ) {
        return new GenericOperationComponent(
                textProvider.getText("button.add.element"),
                textFieldValueConsumer
        );
    }

}