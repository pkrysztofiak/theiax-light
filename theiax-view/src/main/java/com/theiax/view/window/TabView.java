package com.theiax.view.window;

import com.theiax.presentationmodel.domain.Perspective;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TabView implements Initializable {

    @FXML
    private Parent root;

    @FXML
    private Label nameLabel;

    private final Perspective perspective;

    public TabView(Perspective perspective) {
        this.perspective = perspective;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TabView.fxml"));
        fxmlLoader.setControllerFactory(clazz -> {
            return this;
        });
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameLabel.setText(perspective.getName());
    }

    public Parent getRoot() {
        return root;
    }
}
