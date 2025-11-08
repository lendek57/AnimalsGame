package com.ggit;

import java.util.Objects;

public record Vector2D(int x, int y) {

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
}
