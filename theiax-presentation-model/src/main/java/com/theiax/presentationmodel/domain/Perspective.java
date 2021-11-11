package com.theiax.presentationmodel.domain;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Optional;

public class Perspective {

    private final PerspectiveType perspectiveType;
    private final Property<String> nameProperty = new SimpleObjectProperty<>();
    private final ObservableList<Grid> grids = FXCollections.observableArrayList();

    public Perspective(String name, PerspectiveType perspectiveType, List<Grid> grids) {
        nameProperty.setValue(name);
        this.perspectiveType = perspectiveType;
        this.grids.setAll(grids);
    }

    public List<Grid> getGrids() {
        return grids;
    }

    public String getName() {
        return Optional.of(nameProperty.getValue()).orElse("Empty");
    }
}
