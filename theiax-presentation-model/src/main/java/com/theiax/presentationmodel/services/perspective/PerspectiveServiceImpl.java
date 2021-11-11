package com.theiax.presentationmodel.services.perspective;

import com.theiax.presentationmodel.domain.Grid;
import com.theiax.presentationmodel.domain.Perspective;
import com.theiax.presentationmodel.domain.PerspectiveType;
import com.theiax.presentationmodel.services.PerspectiveService;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class PerspectiveServiceImpl implements PerspectiveService {

    private final static List<Perspective> perspectives = new ArrayList<>();

    static {
        List<Grid> grids = new ArrayList<>();
        Perspective searchPerspective = new Perspective("Search", PerspectiveType.SEARCH, grids);
        Perspective ctPerspective = new Perspective("CT", PerspectiveType.CT, grids);
        perspectives.add(searchPerspective);
        perspectives.add(ctPerspective);
    }

    @Override
    public List<Perspective> getInitial() {
        return perspectives;
    }
}