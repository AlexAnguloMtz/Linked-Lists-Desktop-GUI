package com.demo.list.view.controllers;

import com.demo.list.configuration.language.TextProvider;
import com.demo.list.view.screens.MainScreen;
import com.demo.list.view.states.ObservableListState;

import java.awt.event.ActionEvent;
import java.util.function.Consumer;

import static com.demo.list.util.IntegerUtils.isInteger;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;

public class LinkedListOperationsController {

    private final ObservableListState state;
    private final TextProvider strings;
    private final MainScreen screen;

    public LinkedListOperationsController(
            ObservableListState state,
            TextProvider strings,
            MainScreen screen
    ) {
        this.state = state;
        this.strings = strings;
        this.screen = screen;
    }

    public void onRemoveEvenNumbers(ActionEvent event) {
        state.removeAllThatMatch(number -> (number % 2) == 0);
    }

    public void onRemoveOddNumbers(ActionEvent event) {
        state.removeAllThatMatch(number -> (number % 2) != 0);
    }

    public void onRemovePositiveNumbers(ActionEvent event) {
        state.removeAllThatMatch(number -> number > 0);
    }

    public void onRemoveNegativeNumbers(ActionEvent event) {
        state.removeAllThatMatch(number -> number < 0);
    }

    public void onAddElement(String input) {
        consumeIntegerOrShowInputError(input, state::addElementToList);
    }

    public void onRemoveElement(String input) {
        consumeIntegerOrShowInputError(input, this::removeElement);
    }

    public void onShowFirstAppearance(String input) {
        consumeIntegerOrShowInputError(input, this::showFirstAppearance);
    }

    public void onShowAllGreater(String input) {
        consumeIntegerOrShowInputError(input, this::showAllGreater);
    }

    public void onShowAllLess(String input) {
        consumeIntegerOrShowInputError(input, this::showAllLess);
    }

    public void onNewUnsortedList(ActionEvent actionEvent) {
        state.resetWithNewUnsortedList();
    }

    public void onNewDescendingList(ActionEvent actionEvent) {
        state.resetWithNewDescendingList();
    }

    public void onNewAscendingList(ActionEvent actionEvent) {
        state.resetWithNewAscendingList();
    }

    private void handleNonIntegerInput(String input) {
        showError(format(strings.get("error.not.a.number"), input));
    }

    private void handleIndexOutOfBounds(int index) {
        showError(format(strings.get("error.index.out.of.bounds"), index));
    }

    private void handleNoGreaterElement(int number) {
        showError(format(strings.get("error.no.greater.elements"), number));
    }

    private void handleNoSmallerElement(int number) {
        showError(format(strings.get("error.no.smaller.elements"), number));
    }

    private void handleEmptyList() {
        showError(strings.get("error.empty.list"));
    }

    private boolean isOutOfBounds(int index) {
        return listLastIndex() < index || index < 0;
    }

    private int listLastIndex() {
        return state.listSize() - 1;
    }

    private void handleNonExistentElement(int number) {
        showError(format(strings.get("error.element.not.in.list"), number));
    }

    private void showError(String error) {
        screen.showError(error);
    }

    private void consumeIntegerOrShowInputError(String input, Consumer<Integer> consumer) {
        if (!isInteger(input)) {
            handleNonIntegerInput(input);
            return;
        }
        consumer.accept(parseInt(input));
    }

    private void removeElement(int number) {
        if (isOutOfBounds(number))  {
            handleIndexOutOfBounds(number);
            return;
        }
        state.removeElementFromListAtIndex(number);
    }

    private void showFirstAppearance(int number) {
        if (!state.listContains(number)) {
            handleNonExistentElement(number);
            return;
        }
        screen.showFirstAppearance(number);
    }

    private void showAllGreater(int number) {
        if (state.isListEmpty()) {
            handleEmptyList();
            return;
        }
        if (state.max() <= number) {
            handleNoGreaterElement(number);
            return;
        }
        screen.showAllGreaterThan(number);
    }

    private void showAllLess(int number) {
        if (state.isListEmpty()) {
            handleEmptyList();
            return;
        }
        if (state.min() >= number) {
            handleNoSmallerElement(number);
            return;
        }
        screen.showAllLessThan(number);
    }

}