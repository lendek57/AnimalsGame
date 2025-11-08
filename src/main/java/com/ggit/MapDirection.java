package com.ggit;

public enum MapDirection {
    NORTH(0, 1), SOUTH(0, -1), EAST(1, 0), WEST(-1, 0);

    private final Vector2D unitVector;

    MapDirection(int x, int y) {
        unitVector = new Vector2D(x, y);
    }

    public Vector2D getUnitVector() {
        return unitVector;
    }
}
