package com.theiax.view;

import com.theiax.presentationmodel.PresentationModel;
import com.theiax.presentationmodel.domain.Window;
import com.theiax.presentationmodel.services.ConfigService;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class View {

    private final PresentationModel presentationModel;
    private final ConfigService configService;

    public View(PresentationModel presentationModel) {
        this.presentationModel = presentationModel;
        configService = presentationModel.configService;

        presentationModel.windowAdded()
                .flatMap(window -> Mono.just(window).subscribeOn(Schedulers.boundedElastic())
                        .map(this::createWindowView))
                .publishOn(Schedulers.fromExecutor(Platform::runLater))
                .subscribe(this::onWindowViewCreated);

    }

    private WindowView createWindowView(Window window) {
        return new WindowView(window);
    }

    private void onWindowViewCreated(WindowView windowView) {
        Stage stage = new Stage();
        stage.setScene(new Scene(windowView.getParent(), 400, 400));
        stage.show();
    }
}