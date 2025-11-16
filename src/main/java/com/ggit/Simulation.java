package com.ggit;

public class Simulation {
    private final WorldMap map;

    public Simulation(int width, int height, int noOfPlants, int noOfAnimals) {
        map = new AnimalsEnclosure(width, height, noOfPlants, noOfAnimals);
    }

    public WorldMap getMap() {
        return map;
    }

    public void simulateDay() {
        map.run();
    }
}
