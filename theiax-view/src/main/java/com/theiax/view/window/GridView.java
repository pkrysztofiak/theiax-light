package com.theiax.view.window;

import com.theiax.presentationmodel.PresentationModel;
import com.theiax.presentationmodel.domain.*;
import com.theiax.reactor.Additions;
import com.theiax.reactor.Values;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GridView implements Initializable {

    private final PresentationModel presentationModel;
    private final Window window;
    private final Perspective perspective;
    private final Grid grid;

    private final ObservableList<GridCellView> cells = FXCollections.observableArrayList();
    private final Flux<GridCellView> cellAdded = Additions.of(cells).startWith(cells).subscribeOn(Schedulers.single());

    @FXML
    private Pane pane;

    public GridView(PresentationModel presentationModel, Window window, Perspective perspective, Grid grid) {
        this.presentationModel = presentationModel;
        this.window = window;
        this.perspective = perspective;
        this.grid = grid;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GridView.fxml"));
        fxmlLoader.setControllerFactory(clazz -> {
            return this;
        });

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        cellAdded.publishOn(Schedulers.fromExecutor(Platform::runLater)).subscribe(gridCellView -> {
            pane.getChildren().add(gridCellView.getRoot());
        });

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        registerListeners();
    }

    private void registerListeners() {
        grid.cellAdded().subscribe(gridCell -> {

            cells.add(new GridCellView(gridCell));

            Values.of(pane.layoutBoundsProperty())
                    .subscribeOn(Schedulers.fromExecutor(Platform::runLater))
                    .publishOn(Schedulers.single())
                    .subscribe(bounds -> {
                        gridCell.updateBounds(new Bounds(bounds));
                    });
        });

        window.selectedPerspectiveFlux()
                .subscribe(selectedPerspective -> {
                    if (perspective.equals(selectedPerspective)) {
                        Platform.runLater(() -> {
                            pane.toFront();
                        });
                    }
                });
    }

    public Parent getRoot() {
        return pane;
    }
}
