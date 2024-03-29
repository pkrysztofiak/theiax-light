package com.theiax.view;

import com.theiax.presentationmodel.PresentationModel;
import com.theiax.presentationmodel.domain.Window;
import com.theiax.view.window.WindowView;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class View {

    private final PresentationModel presentationModel;

    public View(PresentationModel presentationModel) {
        this.presentationModel = presentationModel;

        presentationModel.windowAdded()
                .flatMap(window -> Mono.just(window).subscribeOn(Schedulers.boundedElastic())
                        .map(this::createWindowView))
                .publishOn(Schedulers.fromExecutor(Platform::runLater))
                .subscribe(this::onWindowViewCreated);

    }

    private WindowView createWindowView(Window window) {
        return new WindowView(presentationModel, window);
    }

    private void onWindowViewCreated(WindowView windowView) {
        Stage stage = new Stage();
        Scene scene = new Scene(windowView.getParent(), 400, 400);
//        scene.getStylesheets().add(getClass().getClassLoader().getResource("styles/style.css").toExternalForm());
        System.out.println(getClass().getResource(getClass().getSimpleName() + ".class"));
        System.out.println(getClass().getClassLoader().getResource("/"));
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}