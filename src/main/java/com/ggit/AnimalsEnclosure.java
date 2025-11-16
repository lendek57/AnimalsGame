package com.ggit;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class AnimalsEnclosure extends AbstractWorldMap {
    private List<Animal> animals = new LinkedList<>();
    private List<Plant> plants = new LinkedList<>();
    private static final Random random = new Random();

    public AnimalsEnclosure(int width, int height, int noOfPlants, int noOfAnimals) {
        super(width, height);
        createAnimals(noOfAnimals);
        createPlants(noOfPlants);
    }

    @Override
    public void run() {
        MapDirection[] directions = MapDirection.values();
        for (Animal animal : animals) {
            animal.move(directions[random.nextInt(directions.length)], this);
        }
    }

    @Override
    public void feed() {
        for (Animal animal : animals) {
            if (isPositionOccupied(animal.getPosition())) {
                System.out.printf("Animal %d ate plant", animal.getId());
                removePlant(animal.getPosition());
                growPlant();
            }
        }
    }

    private void removePlant(Vector2D position) {
        Iterator<Plant> it = plants.iterator();
        while (it.hasNext()) {
            if (it.next().getPosition().equals(position)) {
                it.remove();
                break;
            }
        }
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
        plants.add(new Plant(newPosition));
    }

    private boolean isPositionOccupied(Vector2D position) {
        for (Plant plant : plants) {
            if (plant.getPosition().equals(position)) return true;
        }
        return false;
    }
}
