package com.demo.list.view.components;

import com.demo.list.configuration.language.AppProperties;
import com.demo.list.view.controllers.LinkedListOperationsController;

import javax.swing.border.EmptyBorder;
import java.awt.*;

import static com.demo.list.view.layouts.GridBagConstraintsBuilder.constraints;
import static java.awt.GridBagConstraints.BOTH;

public class LinkedListAdminPanel extends BasePanel {

    public LinkedListAdminPanel(
            AppProperties props,
            LinkedListOperationsController controller,
            Component listSizeComponent,
            Component sortingStateComponent
    ) {
        configureLayout();

        add(
                ButtonWithTextField.create(
                        props.string("button.add.element"),
                        props.color("primary.action"),
                        props.color("on.primary.action"),
                        controller::onAddElement
                ),
                constraints(0,0,1,2, BOTH,1,1)
        );

        add(
                ButtonWithTextField.create(
                        props.string("button.remove.element"),
                        props.color("primary.action"),
                        props.color("on.primary.action"),
                        controller::onRemoveElement
                ),
                constraints(1,0,1,2, BOTH,1,1)
        );

        add(
                ButtonWithTextField.create(
                        props.string("button.show.first.appearance"),
                        props.color("primary.action"),
                        props.color("on.primary.action"),
                        controller::onShowFirstAppearance
                ),
                constraints(2,0,1,2, BOTH,1,1)
        );

        add(
                new Column(listSizeComponent, sortingStateComponent),
                constraints(3,0,1,2, BOTH,1,1)
        );

        add(
                ButtonWithTextField.create(
                        props.string("button.show.all.greater"),
                        props.color("primary.action"),
                        props.color("on.primary.action"),
                        controller::onShowAllGreater
                ),
                constraints(0,2,1,2, BOTH,1,1)
        );

        add(
                ButtonWithTextField.create(
                        props.string("button.show.all.less"),
                        props.color("primary.action"),
                        props.color("on.primary.action"),
                        controller::onShowAllLess
                ),
                constraints(1,2,1,2, BOTH,1,1)
        );
        add(
                IntegersOperations.create(props, controller),
                constraints(2,2,1,2, BOTH,1,1)
        );
        add(
                ResetListButtons.create(
                        props,
                        controller::onNewAscendingList,
                        controller::onNewDescendingList,
                        controller::onNewUnsortedList
                ),
                constraints(3,3,1,2, BOTH,1,1)
        );

    }

    private void configureLayout() {
        setLayout(new GridBagLayout());
        setPadding();
    }

    private void setPadding() {
        setBorder(new EmptyBorder(10, 20, 20, 20));
    }

}