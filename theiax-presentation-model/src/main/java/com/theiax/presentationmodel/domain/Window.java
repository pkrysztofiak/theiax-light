package com.theiax.presentationmodel.domain;

import com.theiax.presentationmodel.services.PerspectiveService;
import com.theiax.presentationmodel.services.perspective.PerspectiveServiceImpl;
import com.theiax.reactor.Additions;
import com.theiax.reactor.Values;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;

public class Window {

    private final int index;

    private final PerspectiveService perspectiveService = new PerspectiveServiceImpl();

    private final Property<Bounds> boundsProperty = new SimpleObjectProperty<>();
    public final Flux<Bounds> boundsChanged = Values.of(boundsProperty);

    private final ObservableList<Perspective> perspectives = FXCollections.observableArrayList();
    private final Flux<Perspective> perspectiveAdded = Additions.of(perspectives).startWith(perspectives).subscribeOn(Schedulers.single());

    private final Property<Perspective> selectedPerspectiveProperty = new SimpleObjectProperty<>();
    private final Flux<Perspective> perspectiveSelected = Values.of(selectedPerspectiveProperty).subscribeOn(Schedulers.single());

    public Window(int index, Bounds bounds) {
        this.index = index;
        boundsProperty.setValue(bounds);

        perspectiveService.getInitialAsync().publishOn(Schedulers.single()).subscribe(this::initPerspectives);
    }

    private void initPerspectives(List<Perspective> perspectives) {
        this.perspectives.setAll(perspectives);
        if (!perspectives.isEmpty()) {
            selectedPerspectiveProperty.setValue(perspectives.get(0));
        }
    }

    public Flux<Perspective> perspectiveAdded() {
        return perspectiveAdded;
    }

    public Flux<Perspective> perspectiveSelected() {
        return perspectiveSelected;
    }

    public void selectPerspective(Perspective perspective) {
        selectedPerspectiveProperty.setValue(perspective);
    }

    public int getIndex() {
        return index;
    }
}
