package com.ggit.simulation;

import java.util.Objects;
import java.util.Random;

public record Vector2D(int x, int y) {
    private static Random random = new Random();

    public Vector2D add(Vector2D v) {
        return new Vector2D(x + v.x, y + v.y);
    }

    public Vector2D opposite() {
        return new Vector2D(-x, -y);
    }

    public Vector2D subtract(Vector2D v) {
        return add(v.opposite());
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Vector2D v)) return false;
        return v.x == x && v.y == y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }

    public static Vector2D random(int maxX, int maxY) {
        return new Vector2D(random.nextInt(maxX), random.nextInt(maxY));
    }
}
