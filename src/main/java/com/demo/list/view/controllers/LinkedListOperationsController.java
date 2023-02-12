package com.demo.list.view.controllers;

import com.demo.list.configuration.language.AppProperties;
import com.demo.list.view.screens.MainScreen;
import com.demo.list.view.states.ObservableListState;

import java.awt.event.ActionEvent;
import java.util.function.Consumer;

import static com.demo.list.util.Decision.decide;
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
        handleIntegerInputAction(input, state::addElementToList);
    }

    public void onRemoveElement(String input) {
        handleIntegerInputAction(input, this::removeElement);
    }

    public void onShowFirstAppearance(String input) {
        handleIntegerInputAction(input, this::showFirstAppearance);
    }

    public void onShowAllGreater(String input) {
        handleIntegerInputAction(input, this::tryShowAllGreater);
    }

    public void onShowAllLess(String input) {
        handleIntegerInputAction(input, this::tryShowAllLess);
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
        return listLastIndex() < index || index < 0;
    }

    private int listLastIndex() {
        return state.listSize() - 1;
    }

    private void handleNonExistentElement(int number) {
        showFormattedError("error.element.not.in.list", number);
    }

    private void handleIntegerInputAction(String input, Consumer<Integer> consumer) {
        decide(
                isInteger(input),
                () -> consumer.accept(parseInt(input)),
                () -> handleNonIntegerInput(input)
        );
    }

    private void removeElement(int number) {
        decide(
                (!isOutOfBounds(number)),
                () -> state.removeElementFromListAtIndex(number),
                () -> handleIndexOutOfBounds(number)
        );
    }

    private void showFirstAppearance(int number) {
        decide(
                state.listContains(number),
                () -> screen.showFirstAppearance(number),
                () -> handleNonExistentElement(number)
        );
    }

    private void tryShowAllGreater(int number) {
        handleListSizeDependentAction(() -> showAllGreaterThan(number));
    }

    private void tryShowAllLess(int number) {
        handleListSizeDependentAction(() -> showAllLessThan(number));
    }

    private void handleListSizeDependentAction(Runnable runnable) {
        decide((!state.isListEmpty()), runnable, this::handleEmptyList);
    }

    private void showAllGreaterThan(int number) {
        decide(
                state.max() > number,
                () -> screen.showAllGreaterThan(number),
                () -> showFormattedError("error.no.greater.elements", number)
        );
    }

    private void showAllLessThan(int number) {
        decide(
                state.min() < number,
                () -> screen.showAllLessThan(number),
                () -> showFormattedError("error.no.smaller.elements", number)
        );
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