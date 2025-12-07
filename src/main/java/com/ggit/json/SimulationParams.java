package com.ggit.json;

public record SimulationParams(
        int width,
        int height,
        int animalsCount,
        int plantsCount,
        int animalEnergy,
        int plantEnergy
) {
    public SimulationParams() {
        this(10, 10, 50, 90, 20, 10);
    }
}
