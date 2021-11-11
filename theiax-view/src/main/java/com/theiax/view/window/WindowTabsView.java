package com.theiax.view.window;

import com.theiax.presentationmodel.domain.Window;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WindowTabsView implements Initializable {

    @FXML
    private HBox root;

    private final Window window;

    public WindowTabsView(Window window) {
        this.window = window;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        window.perspectiveAdded().subscribe(perspective -> {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("WindowTabView.fxml"));
            fxmlLoader.setControllerFactory(clazz -> {
                if (clazz.equals(WindowTabView.class)) {
                    return new WindowTabView(perspective);
                } else {
                    throw new IllegalArgumentException();
                }
            });
            try {
                root.getChildren().add(fxmlLoader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
