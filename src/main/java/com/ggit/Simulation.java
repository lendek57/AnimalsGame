package com.ggit;

public class Simulation {
    public static final int ANIMAL_ENERGY = 30;
    public static final int PLANT_ENERGY = 10;

    private final WorldMap map;

    public Simulation(int width, int height, int noOfPlants, int noOfAnimals) {
        map = new AnimalsEnclosure(width, height, noOfPlants, noOfAnimals);
    }

    public WorldMap getMap() {
        return map;
    }

    public void simulateDay(int dayNumber) {
        map.startDay(dayNumber);
        map.run();
        map.feed();
        map.endDay();
    }
}
