package com.ggit;

import java.util.Random;

public class Main {
    private static int MAP_SIZE = 100;

    static void main() {
        Animal animal = new Animal(Vector2D.random(MAP_SIZE, MAP_SIZE));
        System.out.println("Animal created at position " + animal.getPosition());
    }
}
