package com.demo.list.view.components;

import com.demo.list.configuration.language.TextProvider;

import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

import static com.demo.list.view.layouts.GridBagConstraintsBuilder.constraints;
import static java.awt.GridBagConstraints.BOTH;

public class LinkedListAdminPanel extends BasePanel {
    public LinkedListAdminPanel(
            TextProvider strings,
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
                ButtonWithTextField.create(strings.text("button.add.element"), addElementValueConsumer),
                constraints(0,0,1,2, BOTH,1,1)
        );

        add(
                RemoveElementFromList.create(strings, removeElementValueConsumer),
                constraints(1,0,1,2, BOTH,1,1)
        );

        add(
                ShowFirstAppearanceComponent.create(strings, showFirstAppearanceValueConsumer),
                constraints(2,0,1,2, BOTH,1,1)
        );

        add(
                new Column(listSizeComponent, sortingStateComponent),
                constraints(3,0,1,2, BOTH,1,1)
        );

        add(
                ShowAllGreaterElements.create(strings, showAllGreaterValueConsumer),
                constraints(0,2,1,2, BOTH,1,1)
        );

        add(
                ShowAllLessElementsComponent.create(strings, showAllLessValueConsumer),
                constraints(1,2,1,2, BOTH,1,1)
        );
        add(
                IntegersOperations.create(
                        strings,
                        onRemoveEvenNumbers,
                        onRemoveOddNumbers,
                        onRemovePositiveNumbers,
                        onRemoveNegativeNumbers
                ),
                constraints(2,2,1,2, BOTH,1,1)
        );
        add(
                ResetListButtons.create(strings, onNewAscendingList, onNewDescendingList, onNewUnsortedList),
                constraints(3,3,1,2, BOTH,1,1)
        );

        setPadding();

    }

    private void setPadding() {
        setBorder(new EmptyBorder(10, 20, 20, 20));
    }

}