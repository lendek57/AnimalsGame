package com.ggit;

import com.ggit.json.JsonParser;
import com.ggit.simulation.Simulation;

public class Main {
    private static final int NO_OF_DAYS = 10;

    static void main(String[] args) {
        int noOfDays = args.length > 0 ? Integer.parseInt(args[0]) : NO_OF_DAYS;
        Simulation simulation = new Simulation(JsonParser.readConfig());
        for (int i = 0; i < noOfDays; i++) {
            simulation.simulateDay(i);
        }
    }
}
