package com.demo.list.view.components;

import com.demo.list.configuration.language.TextProvider;
import com.demo.list.view.controllers.LinkedListOperationsController;

import javax.swing.border.EmptyBorder;
import java.awt.*;

import static com.demo.list.view.layouts.GridBagConstraintsBuilder.constraints;
import static java.awt.GridBagConstraints.BOTH;

public class LinkedListAdminPanel extends BasePanel {

    public LinkedListAdminPanel(
            TextProvider strings,
            LinkedListOperationsController controller,
            Component listSizeComponent,
            Component sortingStateComponent
    ) {
        configureLayout();

        add(
                ButtonWithTextField.create(strings.get("button.add.element"), controller::onAddElement),
                constraints(0,0,1,2, BOTH,1,1)
        );

        add(
                ButtonWithTextField.create(strings.get("button.remove.element"), controller::onRemoveElement),
                constraints(1,0,1,2, BOTH,1,1)
        );

        add(
                ButtonWithTextField.create(strings.get("button.show.first.appearance"), controller::onShowFirstAppearance),
                constraints(2,0,1,2, BOTH,1,1)
        );

        add(
                new Column(listSizeComponent, sortingStateComponent),
                constraints(3,0,1,2, BOTH,1,1)
        );

        add(
                ButtonWithTextField.create(strings.get("button.show.all.greater"), controller::onShowAllGreater),
                constraints(0,2,1,2, BOTH,1,1)
        );

        add(
                ButtonWithTextField.create(strings.get("button.show.all.less"), controller::onShowAllLess),
                constraints(1,2,1,2, BOTH,1,1)
        );
        add(
                IntegersOperations.create(strings, controller),
                constraints(2,2,1,2, BOTH,1,1)
        );
        add(
                ResetListButtons.create(strings, controller::onNewAscendingList, controller::onNewDescendingList, controller::onNewUnsortedList),
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