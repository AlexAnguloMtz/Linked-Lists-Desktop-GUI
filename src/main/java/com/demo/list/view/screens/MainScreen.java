package com.demo.list.view.screens;

import com.demo.list.configuration.language.AppProperties;
import com.demo.list.view.components.*;
import com.demo.list.view.controllers.LinkedListOperationsController;
import com.demo.list.view.states.ObservableListState;

import java.awt.*;

import static com.demo.list.view.layouts.GridBagConstraintsBuilder.constraints;
import static java.awt.GridBagConstraints.BOTH;
import static java.lang.String.format;

public class MainScreen extends Screen {

    private final AppProperties props;
    private final ObservableListState state;
    private final ScrollableLinkedListWindow scrollableLinkedListWindowComponent;
    private final MessageModal messageModal;

    public MainScreen(AppProperties props) {
        this.props = props;
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
                string("text.window.all.less.title"),
                format(string("text.window.all.less.header"), number),
                string("text.empty.list.content"),
                this,
                state.withAllLessThan(number)
        );
    }

    public void showAllGreaterThan(int number) {
        scrollableLinkedListWindowComponent.show(
                string("text.window.all.greater.title"),
                format(string("text.window.all.greater.header"), number),
                string("text.empty.list.content"),
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
                props.string("message.first.appearance"),
                number,
                state.firstAppearance(number)
        );
    }

    private Component linkedListsOperationComponent() {
        return new LinkedListAdminPanel(
                props,
                new LinkedListOperationsController(state, props, this),
                new ListSize(state),
                new SortingState(state, props)
        );
    }

    private void addChildrenComponents() {
        setLayout(new GridBagLayout());
        add(
                new LinkedListContainer(props, state),
                constraints(0, 0, 1, 1, BOTH, 1, 0.5)
        );
        add(
                linkedListsOperationComponent(),
                constraints(0, 1, 1, 1, BOTH, 1, 0.5)
        );
    }

    private String string(String key) {
        return props.string(key);
    }

}