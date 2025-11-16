package com.ggit;

public class Animal {
    private final int id;
    private Vector2D position;
    private static int counter = 0;

    public Animal(Vector2D position) {
        this.position = position;
        this.id = counter++;
    }

    public int getId() {
        return id;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void move(MapDirection direction, WorldMap map) {
        position = pbc(position.add(direction.getUnitVector()), map);
        System.out.printf("Animal %d moved to %s\n", id, position);
    }

    private Vector2D pbc(Vector2D newPosition, WorldMap map) {
        int x = newPosition.x();
        int y = newPosition.y();

        if (x < 0) return new Vector2D(x + map.getWidth(), y);
        if (y < 0) return new Vector2D(x, y + map.getHeight());
        if (x >= map.getWidth()) return new Vector2D(x - map.getWidth(), y);
        if (y >= map.getHeight()) return new Vector2D(x, y - map.getHeight());
        return newPosition;
    }
}
