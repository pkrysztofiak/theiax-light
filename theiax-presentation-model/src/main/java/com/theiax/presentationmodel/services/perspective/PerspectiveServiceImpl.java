package com.theiax.presentationmodel.services.perspective;

import com.theiax.presentationmodel.domain.*;
import com.theiax.presentationmodel.services.PerspectiveService;

import java.util.ArrayList;
import java.util.List;

public class PerspectiveServiceImpl implements PerspectiveService {

    private final static List<Perspective> perspectives = new ArrayList<>();

    static {
        List<Grid> grids = new ArrayList<>();
        Perspective searchPerspective = createSearchPerspective();
        Perspective ctPerspective = createCTPerspective();
        perspectives.add(searchPerspective);
        perspectives.add(ctPerspective);
    }

    private static Perspective createSearchPerspective() {
        GridCell cell1_1 = new GridCell(new Bounds(0, 0, 1, 0.5), ViewType.SEARCH_BROWSER);
        GridCell cell1_2 = new GridCell(new Bounds(0, 0.5, 1, 0.5), ViewType.SEARCH_BROWSER);
        List<GridCell> cells1 = new ArrayList<>();
        cells1.add(cell1_1);
        cells1.add(cell1_2);
        Grid grid1 = new Grid(cells1);

        GridCell cell2_1 = new GridCell(new Bounds(0, 0, 1, 0.5), ViewType.SEARCH_BROWSER);
        GridCell cell2_2 = new GridCell(new Bounds(0, 0.5, 1, 0.5), ViewType.SEARCH_BROWSER);
        List<GridCell> cells2 = new ArrayList<>();
        cells2.add(cell2_1);
        cells2.add(cell2_2);
        Grid grid2 = new Grid(cells2);

        List<Grid> grids = new ArrayList<>();
        grids.add(grid1);
        grids.add(grid2);

        Perspective searchPerspective = new Perspective("Search", PerspectiveType.SEARCH, grids);

        return searchPerspective;
    }

    private static Perspective createCTPerspective() {
        GridCell cell1_1 = new GridCell(new Bounds(0, 0, 0.25, 1), ViewType.EXPLORER);
        GridCell cell1_2 = new GridCell(new Bounds(0.25, 0, 0.75, 1), ViewType.HANGING_PROTOCOL);
        List<GridCell> cells1 = new ArrayList<>();
        cells1.add(cell1_1);
        cells1.add(cell1_2);
        Grid grid1 = new Grid(cells1);

        GridCell cell2_1 = new GridCell(new Bounds(0, 0, 1, 1), ViewType.HANGING_PROTOCOL);
        List<GridCell> cells2 = new ArrayList<>();
        cells2.add(cell2_1);
        Grid grid2 = new Grid(cells2);

        List<Grid> grids = new ArrayList<>();
        grids.add(grid1);
        grids.add(grid2);

        return new Perspective("CT", PerspectiveType.CT, grids);
    }

    @Override
    public List<Perspective> getInitial() {
        return perspectives;
    }
}