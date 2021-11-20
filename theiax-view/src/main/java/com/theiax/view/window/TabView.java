package com.theiax.view.window;

import com.theiax.presentationmodel.PresentationModel;
import com.theiax.presentationmodel.domain.Perspective;
import com.theiax.presentationmodel.domain.Window;
import com.theiax.reactor.Events;
import javafx.application.Platform;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TabView implements Initializable {

    @FXML
    private Parent root;

    @FXML
    private Label nameLabel;

    private final static PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");

    private final PresentationModel presentationModel;
    private final Window window;
    private final Perspective perspective;

    public TabView(PresentationModel presentationModel, Window window, Perspective perspective) {
        this.presentationModel = presentationModel;
        this.window = window;
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

        window.perspectiveSelected()
                .publishOn(Schedulers.fromExecutor(Platform::runLater))
                .subscribe(this::onSelectedPerspectiveChanged);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameLabel.setText(perspective.getName());

        registerListeners();
    }

    private void registerListeners() {
        Events.of(root, MouseEvent.MOUSE_CLICKED)
                .publishOn(Schedulers.single())
                .subscribe(event -> {
                    if (event.isControlDown()) {
                        window.selectPerspective(perspective);
                    } else {
                        presentationModel.selectPerspective(perspective);
                    }
                });
    }

    public Parent getRoot() {
        return root;
    }

    private void onSelectedPerspectiveChanged(Perspective selectedPerspective) {
        root.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, selectedPerspective == perspective);

//        if (selectedPerspective == perspective) {
//            root.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, true);
//            nameLabel.setText(nameLabel.getText() + "!");
//        } else {
//            nameLabel.setText(perspective.getName());
//        }
    }
}
