package com.theiax.presentationmodel.domain.impl;

import com.theiax.presentationmodel.domain.Bounds;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridCellImplTest {

    //TODO Test null. Test parent bounds width = 0, height = 0. Test parametrized for bounds combinations.

    @Test
    void should_UpdateBounds_When_ParentBoundsNotNull_1() {
        //arrange
        Bounds bounds = new Bounds(0.0, 0.0, 1.0, 1.0);
        GridCellImpl gridCell = new GridCellImpl(bounds);
        Bounds parentBounds = new Bounds(0.0, 0.0, 100, 100);

        //act
        gridCell.updateBounds(parentBounds);

        //assert
        Bounds actual = gridCell.getBounds();
        assertNotNull(actual);
        assertAll(
                () -> assertEquals(0.0, actual.getX()),
                () -> assertEquals(0.0, actual.getY()),
                () -> assertEquals(100.0, actual.getWidth()),
                () -> assertEquals(100.0, actual.getHeight())
        );
    }

    @Test
    void should_UpdateBounds_When_ParentBoundsNotNull_2() {
        //arrange
        Bounds bounds = new Bounds(0.0, 0.0, 0.5, 1.0);
        GridCellImpl gridCell = new GridCellImpl(bounds);
        Bounds parentBounds = new Bounds(0.0, 0.0, 100, 100);

        //act
        gridCell.updateBounds(parentBounds);

        //assert
        Bounds actual = gridCell.getBounds();
        assertNotNull(actual);
        assertAll(
                () -> assertEquals(0.0, actual.getX()),
                () -> assertEquals(0.0, actual.getY()),
                () -> assertEquals(50.0, actual.getWidth()),
                () -> assertEquals(100.0, actual.getHeight())
        );
    }

    @Test
    void should_UpdateBounds_When_ParentBoundsNotNull_3() {
        //arrange
        Bounds bounds = new Bounds(0.0, 0.0, 1.0, 0.5);
        GridCellImpl gridCell = new GridCellImpl(bounds);
        Bounds parentBounds = new Bounds(0.0, 0.0, 100, 100);

        //act
        gridCell.updateBounds(parentBounds);

        //assert
        Bounds actual = gridCell.getBounds();
        assertNotNull(actual);
        assertAll(
                () -> assertEquals(0.0, actual.getX()),
                () -> assertEquals(0.0, actual.getY()),
                () -> assertEquals(100.0, actual.getWidth()),
                () -> assertEquals(50.0, actual.getHeight())
        );
    }

    @Test
    void should_UpdateBounds_When_ParentBoundsNotNull_4() {
        //arrange
        Bounds bounds = new Bounds(0.5, 0.0, 0.5, 1.0);
        GridCellImpl gridCell = new GridCellImpl(bounds);
        Bounds parentBounds = new Bounds(0.0, 0.0, 100, 100);

        //act
        gridCell.updateBounds(parentBounds);

        //assert
        Bounds actual = gridCell.getBounds();
        assertNotNull(actual);
        assertAll(
                () -> assertEquals(50.0, actual.getX()),
                () -> assertEquals(0.0, actual.getY()),
                () -> assertEquals(50.0, actual.getWidth()),
                () -> assertEquals(100.0, actual.getHeight())
        );
    }
}