package com.theiax.reactor;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Events {

    public static <T extends Event> Flux<T> of(Node node, EventType<T> eventType) {
        return Flux.<T>create(sink -> {
            EventHandler<T> eventHandler = sink::next;
            node.addEventHandler(eventType, eventHandler);
            sink.onDispose(() -> Platform.runLater(() -> node.removeEventHandler(eventType, eventHandler)));
        }).subscribeOn(Schedulers.fromExecutor(Platform::runLater));
    }
}
