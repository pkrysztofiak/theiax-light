package com.theiax.view;

import com.theiax.presentationmodel.PresentationModel;
import javafx.application.Application;
import javafx.stage.Stage;
import reactor.core.scheduler.Schedulers;

public class Launcher extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Schedulers.single().schedule(() -> {


            PresentationModel presentationModel = new PresentationModel();
            new View(presentationModel);


        });
    }
}
