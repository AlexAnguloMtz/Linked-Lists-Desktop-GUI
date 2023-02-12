package com.demo.list.view.screens;

import com.demo.list.configuration.language.TextProvider;
import com.demo.list.view.components.*;
import com.demo.list.view.controllers.LinkedListOperationsController;
import com.demo.list.view.states.ObservableListState;

import java.awt.*;

import static com.demo.list.view.layouts.GridBagConstraintsBuilder.constraints;
import static java.awt.GridBagConstraints.BOTH;
import static java.lang.String.format;

public class MainScreen extends Screen {

    private final TextProvider strings;
    private final ObservableListState state;
    private final ScrollableLinkedListWindow scrollableLinkedListWindowComponent;
    private final MessageModal messageModal;

    public MainScreen(TextProvider textProvider) {
        this.strings = textProvider;
        this.state = new ObservableListState();
        this.scrollableLinkedListWindowComponent = new ScrollableLinkedListWindow();
        this.messageModal = new MessageModal();
        addChildrenComponents();
    }

    @Override
    public String getLayoutId() {
        return "MAIN_SCREEN";
    }

    public void showAllLessThan(int number) {
        scrollableLinkedListWindowComponent.show(
                strings.get("text.window.all.less.title"),
                format(strings.get("text.window.all.less.header"), number),
                strings.get("text.empty.list.content"),
                this,
                state.withAllLessThan(number)
        );
    }

    public void showAllGreaterThan(int number) {
        scrollableLinkedListWindowComponent.show(
                strings.get("text.window.all.greater.title"),
                format(strings.get("text.window.all.greater.header"), number),
                strings.get("text.empty.list.content"),
                this,
                state.withAllGreaterThan(number)
        );
    }

    public void showMessage(String message) {
        messageModal.showMessage(message);
    }

    public void showError(String error) {
        messageModal.showError(error);
    }

    public void showFirstAppearance(int number) {
        showMessage(firstAppearanceMessage(number));
    }

    private String firstAppearanceMessage(int number) {
        return format(
                strings.get("message.first.appearance"),
                number,
                state.firstAppearance(number)
        );
    }

    private Component linkedListsOperationComponent() {
        return new LinkedListAdminPanel(
                strings,
                new LinkedListOperationsController(state, strings, this),
                new ListSize(state),
                new SortingState(state, strings)
        );
    }

    private void addChildrenComponents() {
        setLayout(new GridBagLayout());
        add(
                new LinkedListContainer(strings, state),
                constraints(0, 0, 1, 1, BOTH, 1, 0.5)
        );
        add(
                linkedListsOperationComponent(),
                constraints(0, 1, 1, 1, BOTH, 1, 0.5)
        );
    }

}