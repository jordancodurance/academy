package com.codurance.landscape;

public class Grid {

    public static final int BOUNDARY = 10;

    private final Coordinate[][] grid = new Coordinate[BOUNDARY][BOUNDARY];

    public void add(Coordinate coordinate) {
        grid[coordinate.x][coordinate.y] = coordinate;
    }

    public Coordinate get(Coordinate coordinate) {
        return grid[coordinate.x][coordinate.y];
    }

}
