package com.codurance.landscape;

import static com.codurance.landscape.Grid.BOUNDARY;

public class Coordinate {

    public final int x;
    public final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getNextXAxis() {
        return getNextAxis(x);
    }

    private int getNextAxis(int axis) {
        return (axis + 1) % BOUNDARY;
    }

    public int getNextYAxis() {
        return getNextAxis(y);
    }

    public int getPreviousYAxis() {
        return getPreviousAxis(y);
    }

    protected int getPreviousAxis(int axis) {
        return (axis - 1 + BOUNDARY) % BOUNDARY;
    }

    public int getPreviousXAxis() {
        return getPreviousAxis(x);
    }
}
