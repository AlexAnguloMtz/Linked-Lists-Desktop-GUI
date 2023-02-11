package com.demo.list.view.screens;

import com.demo.list.configuration.language.TextProvider;
import com.demo.list.util.IntegerUtils;
import com.demo.list.view.components.*;
import com.demo.list.view.states.ObservableListState;

import java.awt.*;
import java.awt.event.ActionEvent;

import static com.demo.list.view.layouts.GridBagConstraintsBuilder.constraints;
import static java.awt.GridBagConstraints.BOTH;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;

public class MainScreen extends Screen {

    private final TextProvider textProvider;
    private final ObservableListState state;
    private final MessageModalComponent messageModal;
    private final ScrollableLinkedListWindowComponent scrollableLinkedListWindowComponent;

    public MainScreen(TextProvider textProvider) {
        this.textProvider = textProvider;
        this.state = new ObservableListState();
        this.messageModal = new MessageModalComponent();
        this.scrollableLinkedListWindowComponent = new ScrollableLinkedListWindowComponent();
        addChildrenComponents();
    }

    @Override
    public String getLayoutId() {
        return "MAIN_SCREEN";
    }

    private Component linkedListsOperationComponent() {
        return new LinkedListAdminPanelComponent(
                textProvider,
                this::handleAddElementValue,
                this::handleRemoveElementValue,
                this::handleShowFirstAppearance,
                this::handleShowAllGreater,
                this::handleShowAllLess,
                this::handleNewAscendingList,
                this::handleNewDescendingList,
                this::handleNewUnsortedList,
                this::handleRemoveEvenNumbers,
                this::handleRemoveOddNumbers,
                this::handleRemovePositiveNumbers,
                this::handleRemoveNegativeNumbers,
                new ListSizeComponent(state),
                new SortingStateComponent(state, textProvider)
        );
    }

    private void handleAddElementValue(String input) {
        if (!isInteger(input)) {
            handleNonIntegerInput(input);
            return;
        }
        state.addElementToList(input);
    }

    private void handleRemoveElementValue(String input) {
        if (!isInteger(input)) {
            handleNonIntegerInput(input);
            return;
        }
        if (isOutOfBounds(parseInt(input)))  {
            handleIndexOutOfBounds(parseInt(input));
            return;
        }
        state.removeElementFromListAtIndex(parseInt(input));
    }

    private boolean isOutOfBounds(int index) {
        return listLastIndex() < index || index < 0;
    }

    private void handleIndexOutOfBounds(int index) {
        showError(format(textProvider.getText("error.index.out.of.bounds"), index));
    }

    private void handleShowFirstAppearance(String input) {
        if (!isInteger(input)) {
            handleNonIntegerInput(input);
            return;
        }
        if (!state.listContains(parseInt(input))) {
            handleNonExistentElement(parseInt(input));
            return;
        }
        showMessage(firstAppearanceMessage(input));
    }

    private void handleShowAllGreater(String input) {
        if (!isInteger(input)) {
            handleNonIntegerInput(input);
            return;
        }
        if (state.isListEmpty()) {
            handleEmptyList();
            return;
        }
        if (state.max() <= parseInt(input)) {
            handleNoGreaterElement(parseInt(input));
            return;
        }
        showAllGreaterThan(parseInt(input));
    }

    private void handleShowAllLess(String input) {
        if (!isInteger(input)) {
            handleNonIntegerInput(input);
            return;
        }
        if (state.isListEmpty()) {
            handleEmptyList();
            return;
        }
        if (state.min() >= parseInt(input)) {
            handleNoSmallerElement(parseInt(input));
            return;
        }
        showAllLessThan(parseInt(input));
    }

    private void handleRemoveEvenNumbers(ActionEvent actionEvent) {
        state.removeAllThatMatch(number -> (number % 2) == 0);
    }

    private void handleRemoveOddNumbers(ActionEvent actionEvent) {
        state.removeAllThatMatch(number -> (number % 2) != 0);
    }

    private void handleRemovePositiveNumbers(ActionEvent actionEvent) {
        state.removeAllThatMatch(number -> number > 0);
    }

    private void handleRemoveNegativeNumbers(ActionEvent actionEvent) {
        state.removeAllThatMatch(number -> number < 0);
    }

    private void handleNoGreaterElement(int number) {
        showError(format(textProvider.getText("error.no.greater.elements"), number));
    }

    private void handleNoSmallerElement(int number) {
        showError(format(textProvider.getText("error.no.smaller.elements"), number));
    }

    private void handleEmptyList() {
        showError(textProvider.getText("error.empty.list"));
    }

    private void showAllGreaterThan(int number) {
        scrollableLinkedListWindowComponent.show(
            textProvider.getText("text.window.all.greater.title"),
            format(textProvider.getText("text.window.all.greater.header"), number),
            textProvider.getText("text.empty.list.content"),
            this,
            state.withAllGreaterThan(number)
        );
    }

    private void showAllLessThan(int number) {
        scrollableLinkedListWindowComponent.show(
                textProvider.getText("text.window.all.less.title"),
                format(textProvider.getText("text.window.all.less.header"), number),
                textProvider.getText("text.empty.list.content"),
                this,
                state.withAllLessThan(number)
        );
    }

    private String firstAppearanceMessage(String input) {
        return format(
            textProvider.getText("message.first.appearance"),
            input,
            state.firstAppearance(parseInt(input))
        );
    }

    private int listLastIndex() {
        return state.getList().size() - 1;
    }

    private boolean isInteger(String input) {
        return IntegerUtils.isInteger(input);
    }

    private void addChildrenComponents() {
        setLayout(new GridBagLayout());
        add(
                new LinkedListVisualizationComponent(textProvider, state),
                constraints(0, 0, 1, 1, BOTH, 1, 0.5)
        );
        add(
            linkedListsOperationComponent(),
            constraints(0, 1, 1, 1, BOTH, 1, 0.5)
        );
    }

    private void handleNonIntegerInput(String input) {
        showError(format(textProvider.getText("error.not.a.number"), input));
    }

    private void handleNonExistentElement(int number) {
        showError(format(textProvider.getText("error.element.not.in.list"), number));
    }

    private void handleNewUnsortedList(ActionEvent actionEvent) {
        state.resetWithNewUnsortedList();
    }

    private void handleNewDescendingList(ActionEvent actionEvent) {
        state.resetWithNewDescendingList();
    }

    private void handleNewAscendingList(ActionEvent actionEvent) {
        state.resetWithNewAscendingList();
    }

    private void showMessage(String message) {
        messageModal.showMessage(message);
    }

    private void showError(String message) {
        messageModal.showError(message);
    }

}