package com.ggit;

import java.util.Random;

public enum MapDirection {
    NORTH(0, 1), SOUTH(0, -1), EAST(1, 0), WEST(-1, 0);

    private final Vector2D unitVector;
    private final Random random = new Random();

    MapDirection(int x, int y) {
        unitVector = new Vector2D(x, y);
    }

    public Vector2D getUnitVector() {
        return unitVector;
    }

    public static MapDirection random() {
        MapDirection[] directions = MapDirection.values();
        return directions[random.nextInt(directions.length)];
    }
}
