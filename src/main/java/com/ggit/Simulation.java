package com.ggit;

public class Simulation {
    private final WorldMap map;

    public Simulation(int width, int height) {
        map = new AnimalsEnclosure(width, height);
    }

    public WorldMap getMap() {
        return map;
    }

    public void simulateDay() {
        map.run();
    }
}
