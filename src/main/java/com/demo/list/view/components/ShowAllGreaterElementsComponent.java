package com.demo.list.view.components;

import com.demo.list.configuration.language.TextProvider;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.function.Consumer;


public class ShowAllGreaterElementsComponent {

    public static Component create(
            TextProvider textProvider,
            Consumer<String> textFieldValueConsumer
    ) {
        return new GenericOperationComponent(
                textProvider.getText("button.show.all.greater"),
                textFieldValueConsumer
        );
    }

}
