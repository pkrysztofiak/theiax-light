package com.theiax.view.window;

import com.theiax.presentationmodel.domain.GridCell;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GridCellView implements Initializable {

    private final GridCell gridCell;

    private VBox root;

    public GridCellView(GridCell gridCell) {
        this.gridCell = gridCell;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GridCellView.fxml"));
        fxmlLoader.setControllerFactory(clazz -> {
            return this;
        });

        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Parent getRoot() {
        return root;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gridCell.boundsFlux().publishOn(Schedulers.fromExecutor(Platform::runLater)).subscribe(bounds -> {
            System.out.println("new Bounds=" + bounds);
            root.setLayoutX(bounds.getX());
            root.setLayoutY(bounds.getY());
            root.setPrefSize(bounds.getWidth(), bounds.getHeight());
        });
    }
}
