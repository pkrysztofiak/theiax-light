package com.theiax.view.window;

import com.theiax.presentationmodel.domain.Window;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class WindowView {

    private final Window window;
    public Parent parent;

    public WindowView(Window window) {
        this.window = window;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("WindowView.fxml"));
        fxmlLoader.setControllerFactory(clazz -> {
            if (clazz.equals(WindowView.class)) {
                return this;
            } else if (clazz.equals(TabPaneView.class)) {
                return new TabPaneView(window);
            } else {
                throw new IllegalArgumentException();
            }
        });
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Parent getParent() {
        return parent;
    }
}