package com.theiax.view;

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
            return this;
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