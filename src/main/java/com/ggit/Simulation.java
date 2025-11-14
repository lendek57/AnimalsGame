package com.ggit;

public class Simulation {
    private WorldMap map;

    Simulation(int width, int height) {
        map = new AnimalsEnclosure(width, height);
    }
}
