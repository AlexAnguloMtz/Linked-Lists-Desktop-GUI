package com.demo.list.view.components;

import com.demo.list.configuration.language.TextProvider;

import javax.swing.*;

import java.awt.*;
import java.util.function.Consumer;

public class RemoveElementFromListComponent {

    public static Component create(
            TextProvider textProvider,
            Consumer<String> textFieldValueConsumer
    ) {
        return GenericOperationComponent.create(
                textProvider.getText("button.remove.element"),
                textFieldValueConsumer
        );
    }

}