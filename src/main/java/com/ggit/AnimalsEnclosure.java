package com.ggit;

import java.util.*;

public class AnimalsEnclosure extends AbstractWorldMap {
    private List<Animal> animals = new LinkedList<>();
    private Map<Vector2D, Plant> plants = new HashMap<>();
    private static final Random random = new Random();

    public AnimalsEnclosure(int width, int height, int noOfPlants, int noOfAnimals) {
        super(width, height);
        createAnimals(noOfAnimals);
        createPlants(noOfPlants);
    }

    @Override
    public void run() {
        MapDirection[] directions = MapDirection.values();
        animals.forEach(animal -> animal.move(directions[random.nextInt(directions.length)], this));
    }

    @Override
    public void feed() {
        animals.forEach(animal -> {
            if (isPositionOccupied(animal.getPosition())) {
                removePlant(animal.getPosition());
                animal.eat();
                growPlant();
                System.out.printf("Animal %d ate plant and now has energy level: %d\n", animal.getId(), animal.getEnergy());
            }
        });
    }

    @Override
    public void startDay(int dayNumber) {
        System.out.println("Day number " + dayNumber);
    }

    @Override
    public void endDay() {
        animals.forEach(Animal::ageOneDay);
    }

    private void removePlant(Vector2D position) {
        plants.remove(position);
    }

    private void createAnimals(int noOfAnimals) {
        for (int i = 0; i < noOfAnimals; i++) {
            animals.add(new Animal(Vector2D.random(width, height)));
        }
    }

    private void createPlants(int noOfPlants) {
        for (int i = 0; i < noOfPlants; i++) {
            growPlant();
        }
    }

    private void growPlant() {
        if (plants.size() == width * height) return;

        Vector2D newPosition = Vector2D.random(width, height);
        while (isPositionOccupied(newPosition)) {
            newPosition = Vector2D.random(width, height);
        }
        plants.put(newPosition, new Plant(newPosition));
    }

    private boolean isPositionOccupied(Vector2D position) {
        return plants.containsKey(position);
    }
}
