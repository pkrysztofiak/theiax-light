package com.theiax.presentationmodel.services;

import com.theiax.presentationmodel.domain.Perspective;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

public interface PerspectiveService {

    default Mono<List<Perspective>> getInitialAsync() {
        return Mono.fromCallable(this::getInitial).subscribeOn(Schedulers.boundedElastic());
    }

    List<Perspective> getInitial();
}
