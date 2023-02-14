package com.demo.list.view.components;

import com.demo.list.configuration.language.AppProperties;
import com.demo.list.view.controllers.LinkedListOperationsController;

import javax.swing.border.EmptyBorder;
import java.awt.*;

import static com.demo.list.view.layouts.GridBagConstraintsBuilder.constraints;
import static java.awt.GridBagConstraints.BOTH;

public class LinkedListAdminPanel extends BaseComponent {

    public LinkedListAdminPanel(
            AppProperties props,
            LinkedListOperationsController controller,
            Component listSizeComponent,
            Component sortingStateComponent
    ) {
        super(props);
        configureLayout();
        addChildrenComponents(controller, listSizeComponent, sortingStateComponent);
    }

    private void configureLayout() {
        setLayout(new GridBagLayout());
        setPadding();
    }

    private void setPadding() {
        setBorder(new EmptyBorder(10, 20, 20, 20));
    }

    private void addChildrenComponents(
            LinkedListOperationsController controller,
            Component listSizeComponent,
            Component sortingStateComponent
    ) {
        add(
                ButtonWithTextField.create(
                        string("button.add.element"),
                        color("primary.action"),
                        color("on.primary.action"),
                        controller::onAddElement
                ),
                constraints(0,0,1,2, BOTH,1,1)
        );

        add(
                ButtonWithTextField.create(
                        string("button.remove.element"),
                        color("primary.action"),
                        color("on.primary.action"),
                        controller::onRemoveElement
                ),
                constraints(1,0,1,2, BOTH,1,1)
        );

        add(
                ButtonWithTextField.create(
                        string("button.show.first.appearance"),
                        color("primary.action"),
                        color("on.primary.action"),
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
                        string("button.show.all.greater"),
                        color("primary.action"),
                        color("on.primary.action"),
                        controller::onShowAllGreater
                ),
                constraints(0,2,1,2, BOTH,1,1)
        );

        add(
                ButtonWithTextField.create(
                        string("button.show.all.less"),
                        color("primary.action"),
                        color("on.primary.action"),
                        controller::onShowAllLess
                ),
                constraints(1,2,1,2, BOTH,1,1)
        );
        add(
                IntegersOperations.create(getProps(), controller),
                constraints(2,2,1,2, BOTH,1,1)
        );
        add(
                ResetListButtons.create(
                        getProps(),
                        controller::onNewAscendingList,
                        controller::onNewDescendingList,
                        controller::onNewUnsortedList
                ),
                constraints(3,3,1,2, BOTH,1,1)
        );
    }

}