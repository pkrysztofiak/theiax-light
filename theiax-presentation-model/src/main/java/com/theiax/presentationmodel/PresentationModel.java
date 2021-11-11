package com.theiax.presentationmodel;

import com.theiax.presentationmodel.domain.Window;
import com.theiax.presentationmodel.services.WindowService;
import com.theiax.presentationmodel.services.window.WindowServiceImpl;
import com.theiax.reactor.Additions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class PresentationModel {

    private final WindowService windowService = new WindowServiceImpl();

    private final ObservableList<Window> windows = FXCollections.observableArrayList();
    private final Flux<Window> windowAdded = Additions.of(windows).startWith(windows).subscribeOn(Schedulers.single());

    public PresentationModel() {
        windowService.getAllAsync().publishOn(Schedulers.single()).subscribe(windows::setAll);
    }

    public Flux<Window> windowAdded() {
        return windowAdded;
    }
}
