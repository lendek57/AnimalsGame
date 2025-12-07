package com.ggit.simulation;

import com.ggit.json.JsonParser;
import com.ggit.json.SimulationParams;

import java.util.*;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

public class AnimalsEnclosure extends AbstractWorldMap {
    private AnimalsMapping animals = new AnimalsMapping();
    private Map<Vector2D, Plant> plants = new HashMap<>();
    private final int animalEnergy;
    private final int plantEnergy;
    private int dayNumber = 0;

    public AnimalsEnclosure(SimulationParams config) {
        super(config.width(), config.height());
        createAnimals(config.animalsCount());
        createPlants(config.plantsCount());
        this.animalEnergy = config.animalEnergy();
        this.plantEnergy = config.plantEnergy();
    }

    @Override
    public void run() {
        animals.moveAnimals();
    }

    @Override
    public void feed() {
        animals.animalsByPosition.forEach((position, animalsOnPosition) -> {
            if (isPositionOccupiedByPlant(position)) {
                animalsOnPosition.stream().max(Animal::compareTo).ifPresent(animal -> {
                    removePlant(position);
                    animal.eat(plantEnergy);
                    growPlant();
                    System.out.printf("Animal %d ate plant and now has energy level: %d\n", animal.getId(), animal.getEnergy());
                });
            }
        });
    }

    @Override
    public void breed() {
        List<Animal> children = animals.animalsByPosition.values().stream()
                .map(this::chooseParents)
                .filter(parents -> parents.size() == 2)
                .map(parents -> parents.getFirst().reproduce(parents.get(1)))
                .toList();
        children.forEach(animals::addAnimal);
    }

    private List<Animal> chooseParents(List<Animal> animals) {
        return animals.stream()
                .filter(animal -> animal.getEnergy() >= animalEnergy / 2)
                .sorted(Comparator.reverseOrder())
                .limit(2)
                .toList();
    }

    @Override
    public void startDay(int dayNumber) {
        this.dayNumber = dayNumber;
        System.out.println("Day number " + dayNumber);
    }

    @Override
    public void endDay() {
        int animalsCount = animals.allAnimals.size();
        animals.updateAllAnimals(
                animals.allAnimals.stream()
                        .map(Animal::ageOneDay)
                        .filter(animal -> animal.getEnergy() > 0)
                        .collect(Collectors.toList()), false);
        System.out.printf("There were %d animals, %d animals left\n", animalsCount, animals.allAnimals.size());
        JsonParser.dumpStatsToFile(daySummary());
    }

    private SimulationStatistics daySummary() {
        return new SimulationStatistics(
                dayNumber,
                mean(Animal::getAge),
                mean(Animal::getNoOfChildren),
                mean(Animal::getEnergy),
                animals.allAnimals.size()
        );
    }

    private double mean(ToDoubleFunction<Animal> mapper) {
        return animals.allAnimals.stream().mapToDouble(mapper).average().orElse(0);
    }

    private void removePlant(Vector2D position) {
        plants.remove(position);
    }

    private void createAnimals(int noOfAnimals) {
        for (int i = 0; i < noOfAnimals; i++) {
            animals.addAnimal(new Animal(Vector2D.random(width, height), animalEnergy));
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
        while (isPositionOccupiedByPlant(newPosition)) {
            newPosition = Vector2D.random(width, height);
        }
        plants.put(newPosition, new Plant(newPosition));
    }

    private boolean isPositionOccupiedByPlant(Vector2D position) {
        return plants.containsKey(position);
    }

    private class AnimalsMapping {
        private final List<Animal> allAnimals = new ArrayList<>();
        private final Map<Vector2D, List<Animal>> animalsByPosition = new HashMap<>();

        public void addAnimal(Animal animal) {
            allAnimals.add(animal);
            positionAnimalOnMap(animal);
        }

        public void moveAnimals() {
            allAnimals.forEach(animal -> animal.moveBasedOnGenome(AnimalsEnclosure.this));
            rebuildMap();
        }

        public void updateAllAnimals(List<Animal> animals, boolean rebuildMap) {
            allAnimals.clear();
            allAnimals.addAll(animals);
            if (rebuildMap) rebuildMap();
        }

        private void rebuildMap() {
            animalsByPosition.clear();
            allAnimals.forEach(this::positionAnimalOnMap);
        }

        private void positionAnimalOnMap(Animal animal) {
            animalsByPosition.computeIfAbsent(animal.getPosition(), _ -> new LinkedList<>()).add(animal);
        }
    }
}
