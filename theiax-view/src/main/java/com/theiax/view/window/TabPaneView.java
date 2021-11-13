package com.theiax.view.window;

import com.theiax.presentationmodel.domain.Window;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TabPaneView implements Initializable {

    private final Window window;

    @FXML
    private HBox tabsPane;
    @FXML
    private StackPane stackPane;

    public TabPaneView(Window window) {
        this.window = window;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        window.perspectiveAdded().subscribe(perspective -> {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TabView.fxml"));
            fxmlLoader.setControllerFactory(clazz -> {
                if (clazz.equals(TabView.class)) {
                    return new TabView(perspective);
                } else {
                    throw new IllegalArgumentException();
                }
            });
            try {
                tabsPane.getChildren().add(fxmlLoader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
