package com.ggit;

import java.util.Random;

public class Main {
    private static Random random = new Random();
    private static int MAP_SIZE = 100;

    static void main() {
        Animal animal = new Animal(new Vector2D(random.nextInt(MAP_SIZE), random.nextInt(MAP_SIZE)));
        System.out.println("Animal created at position " + animal.getPosition());
    }
}
