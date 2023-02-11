package com.demo.list.view.components;

import com.demo.list.configuration.language.TextProvider;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

import static com.demo.list.view.layouts.GridBagConstraintsBuilder.constraints;
import static java.awt.GridBagConstraints.BOTH;

public class LinkedListAdminPanelComponent extends BasePanel {
    public LinkedListAdminPanelComponent(
            TextProvider textProvider,
            Consumer<String> addElementValueConsumer,
            Consumer<String> removeElementValueConsumer,
            Consumer<String> showFirstAppearanceValueConsumer,
            Consumer<String> showAllGreaterValueConsumer,
            Consumer<String> showAllLessValueConsumer,
            ActionListener onNewAscendingList,
            ActionListener onNewDescendingList,
            ActionListener onNewUnsortedList,
            ActionListener onRemoveEvenNumbers,
            ActionListener onRemoveOddNumbers,
            ActionListener onRemovePositiveNumbers,
            ActionListener onRemoveNegativeNumbers,
            Component listSizeComponent,
            Component sortingStateComponent
    ) {

        setLayout(new GridBagLayout());

        add(
                AddElementToListComponent.create(textProvider, addElementValueConsumer),
                constraints(0,0,1,2, BOTH,1,1)
        );

        add(
                RemoveElementFromListComponent.create(textProvider, removeElementValueConsumer),
                constraints(1,0,1,2, BOTH,1,1)
        );

        add(
                ShowFirstAppearanceComponent.create(textProvider, showFirstAppearanceValueConsumer),
                constraints(2,0,1,2, BOTH,1,1)
        );

        add(
                verticalGridPanel(listSizeComponent, sortingStateComponent),
                constraints(3,0,1,2, BOTH,1,1)
        );

        add(
                ShowAllGreaterElementsComponent.create(textProvider, showAllGreaterValueConsumer),
                constraints(0,2,1,2, BOTH,1,1)
        );

        add(
                ShowAllLessElementsComponent.create(textProvider, showAllLessValueConsumer),
                constraints(1,2,1,2, BOTH,1,1)
        );
        add(
                IntegersOperationsComponent.create(
                        textProvider,
                        onRemoveEvenNumbers,
                        onRemoveOddNumbers,
                        onRemovePositiveNumbers,
                        onRemoveNegativeNumbers
                ),
                constraints(2,2,1,2, BOTH,1,1)
        );
        add(
                ResetListButtonsComponent.create(textProvider, onNewAscendingList, onNewDescendingList, onNewUnsortedList),
                constraints(3,3,1,2, BOTH,1,1)
        );

        setPadding();

    }

    private Component verticalGridPanel(Component listSizeComponent, Component sortingStateComponent) {
        var panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.add(listSizeComponent);
        panel.add(sortingStateComponent);
        return panel;
    }

    private void setPadding() {
        setBorder(new EmptyBorder(10, 20, 20, 20));
    }
}