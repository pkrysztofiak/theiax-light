package com.theiax.presentationmodel;

import com.theiax.presentationmodel.domain.Bounds;
import com.theiax.presentationmodel.domain.Perspective;
import com.theiax.presentationmodel.domain.Window;
import com.theiax.presentationmodel.services.ConfigService;
import com.theiax.presentationmodel.services.config.ConfigServiceImpl;
import com.theiax.reactor.Additions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class PresentationModel {

    public final ConfigService configService = new ConfigServiceImpl();

    private final ObservableList<Perspective> perspectives = FXCollections.observableArrayList();
    private final ObservableList<Window> windows = FXCollections.observableArrayList();
    private final Flux<Window> windowAdded = Additions.of(windows).startWith(windows);

    public PresentationModel() {
        Window window1 = new Window(new Bounds(0, 0, 100, 100));
        Window window2 = new Window(new Bounds(200, 200, 100, 100));
        windows.addAll(window1, window2);
    }

    public void add(Window window) {
        windows.add(window);
    }

    public Flux<Window> windowAdded() {
        return windowAdded.subscribeOn(Schedulers.single());
    }
}
