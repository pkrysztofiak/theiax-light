package com.theiax.view.window;

import com.theiax.presentationmodel.domain.GridCell;
import com.theiax.reactor.Additions;
import com.theiax.view.search.QueueView;
import com.theiax.view.search.SearchView;
import com.theiax.view.search.SourcesView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GridCellView implements Initializable {

    private final GridCell gridCell;

    private final ObservableList<GridCellTabView> tabs = FXCollections.observableArrayList();
    private final Flux<GridCellTabView> tabAddedFlux = Additions.of(tabs).startWith(tabs).subscribeOn(Schedulers.fromExecutor(Platform::runLater));

    private VBox root;

    @FXML
    private HBox tabsPane;

    @FXML
    private StackPane stackPane;

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
            root.setLayoutX(bounds.getX());
            root.setLayoutY(bounds.getY());
            System.out.println("width=" + bounds.getWidth() + ", height=" + bounds.getHeight());
            root.setPrefSize(bounds.getWidth(), bounds.getHeight());
        });

        gridCell.viewAddedFlux().subscribe(viewType -> {
            tabs.add(new GridCellTabView(viewType, gridCell));

            switch (viewType) {
                case SEARCH -> {
                    SearchView searchView = new SearchView(gridCell);
                    stackPane.getChildren().add(searchView.getRoot());
                }
                case QUEUE -> {
                    QueueView queueView = new QueueView(gridCell);
                    stackPane.getChildren().add(queueView.getRoot());
                }
                case SOURCES -> {
                    SourcesView sourcesView = new SourcesView(gridCell);
                    stackPane.getChildren().add(sourcesView.getRoot());
                }
            }
        });

        tabAddedFlux.subscribe(tabView -> {
            tabsPane.getChildren().add(tabView.getRoot());
        });
    }
}