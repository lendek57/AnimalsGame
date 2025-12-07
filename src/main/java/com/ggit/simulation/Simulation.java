package com.ggit.simulation;

import com.ggit.json.SimulationParams;

public class Simulation {
    private final WorldMap map;

    public Simulation(SimulationParams config) {
        map = new AnimalsEnclosure(config);
    }

    public WorldMap getMap() {
        return map;
    }

    public void simulateDay(int dayNumber) {
        map.startDay(dayNumber);
        map.run();
        map.feed();
        map.breed();
        map.endDay();
    }
}
