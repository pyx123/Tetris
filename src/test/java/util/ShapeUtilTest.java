package util;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShapeUtilTest {
    int[][] mat = new int[][] {
            {0,0,0,0,0},
            {0,0,1,0,0},
            {0,1,0,1,0},
            {0,1,1,1,0},
            {0,0,0,0,0}};
    int[][] mat1 = new int[][] {
            {0,0,0,0,0},
            {0,0,1,0,0},
            {0,1,0,1,0},
            {1,1,1,1,1},
            {0,0,0,0,0}};

    @org.junit.Test
    public void getLandingHeight() {
        assertEquals(4, ShapeUtil.getLandingHeight(mat));
    }

    @org.junit.Test
    public void getErodedPieceCellsMetric() {
        assertEquals(1, ShapeUtil.getErodedPieceCellsMetric(mat1));
        assertEquals(0, ShapeUtil.getErodedPieceCellsMetric(mat));
    }

    @org.junit.Test
    public void getBoardRowTransitions() {
        assertEquals(8, ShapeUtil.getBoardRowTransitions(mat));
    }

    @org.junit.Test
    public void getBoardColTransitions() {
        assertEquals(8, ShapeUtil.getBoardColTransitions(mat));
    }

    @Test
    public void getBoardBuriedHoles() {
        assertEquals(4, ShapeUtil.getBoardBuriedHoles(mat));
        assertEquals(6, ShapeUtil.getBoardBuriedHoles(mat1));
    }

    @Test
    public void getBoardWells() {
        assertEquals(7, ShapeUtil.getBoardWells(mat));
        assertEquals(3, ShapeUtil.getBoardWells(mat1));
    }

    @Test
    public void getScore() {
        assertEquals(-1774, ShapeUtil.score(mat, null));
        assertEquals(-2090, ShapeUtil.score(mat1, null));
    }

    @Test
    public void getAllStations() {
        ShapeUtil.getAllStation(mat1);
    }
}