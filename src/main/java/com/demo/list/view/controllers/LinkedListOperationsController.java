package com.demo.list.view.controllers;

import com.demo.list.configuration.language.AppProperties;
import com.demo.list.view.screens.MainScreen;
import com.demo.list.view.states.ObservableListState;

import java.awt.event.ActionEvent;
import java.util.function.Consumer;

import static com.demo.list.util.IntegerUtils.isInteger;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;

public class LinkedListOperationsController {

    private final ObservableListState state;
    private final AppProperties props;
    private final MainScreen screen;

    public LinkedListOperationsController(
            ObservableListState state,
            AppProperties props,
            MainScreen screen
    ) {
        this.state = state;
        this.props = props;
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
        runIfIsInteger(input, state::addElementToList);
    }

    public void onRemoveElement(String input) {
        runIfIsInteger(input, this::removeElement);
    }

    public void onShowFirstAppearance(String input) {
        runIfIsInteger(input, this::showFirstAppearance);
    }

    public void onShowAllGreater(String input) {
        runIfIsInteger(input, this::tryShowAllGreater);
    }

    public void onShowAllLess(String input) {
        runIfIsInteger(input, this::tryShowAllSmaller);
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
        showFormattedError("error.not.a.number", input);
    }

    private void handleIndexOutOfBounds(int index) {
        showFormattedError("error.index.out.of.bounds", index);
    }

    private void handleEmptyList() {
        showError(string("error.empty.list"));
    }

    private boolean isOutOfBounds(int index) {
        return state.isOutOfBounds(index);
    }

    private void handleNonExistentElement(int number) {
        showFormattedError("error.element.not.in.list", number);
    }

    private void runIfIsInteger(String input, Consumer<Integer> consumer) {
        if (!isInteger(input)) {
            handleNonIntegerInput(input);
            return;
        }
        consumer.accept(parseInt(input));
    }

    private void removeElement(int number) {
        if(isOutOfBounds(number)) {
            handleIndexOutOfBounds(number);
            return;
        }
        state.removeAtIndex(number);
    }

    private void showFirstAppearance(int number) {
        if(!state.listContains(number)) {
            handleNonExistentElement(number);
            return;
        }
        screen.showFirstAppearance(number);
    }

    private void tryShowAllGreater(int number) {
        runIfListIsNotEmpty(() -> showAllGreaterThan(number));
    }

    private void tryShowAllSmaller(int number) {
        runIfListIsNotEmpty(() -> showAllLessThan(number));
    }

    private void runIfListIsNotEmpty(Runnable runnable) {
        if (state.listIsEmpty()) {
            handleEmptyList();
            return;
        }
        runnable.run();
    }

    private void showAllGreaterThan(int number) {
        if (state.isGreaterOrEqualToMax(number)) {
            showFormattedError("error.no.greater.elements", number);
            return;
        }
        screen.showAllGreaterThan(number);
    }

    private void showAllLessThan(int number) {
        if (state.isSmallerOrEqualToMin(number)) {
            showFormattedError("error.no.smaller.elements", number);
            return;
        }
        screen.showAllSmallerThan(number);
    }

    private void showFormattedError(String key, Object...objects) {
        showError(format(string(key), objects));
    }

    private void showError(String error) {
        screen.showError(error);
    }

    private String string(String key) {
        return props.string(key);
    }

}