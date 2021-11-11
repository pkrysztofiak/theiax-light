package com.theiax.view.window;

import com.theiax.presentationmodel.domain.Perspective;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class WindowTabView implements Initializable {

    @FXML
    private Label nameLabel;

    private final Perspective perspective;

    public WindowTabView(Perspective perspective) {
        this.perspective = perspective;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameLabel.setText(perspective.getName());
    }
}
