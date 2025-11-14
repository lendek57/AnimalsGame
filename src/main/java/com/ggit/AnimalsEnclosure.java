package com.ggit;

import java.util.Random;

public class AnimalsEnclosure extends AbstractWorldMap {
    private Animal animal;
    private static final Random random = new Random();

    public AnimalsEnclosure(int width, int height) {
        super(width, height);
        animal = new Animal(Vector2D.random(width, height));
    }

    @Override
    public void run() {
        MapDirection[] directions = MapDirection.values();
        animal.move(directions[random.nextInt(directions.length)]);
    }
}
