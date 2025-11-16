package com.ggit;

public class Main {
    private static final int MAP_SIZE = 3;
    private static final int NO_OF_DAYS = 10;

    static void main(String[] args) {
        int noOfDays = args.length > 0 ? Integer.parseInt(args[0]) : NO_OF_DAYS;
        Simulation simulation = new Simulation(MAP_SIZE, MAP_SIZE);
        for (int i = 0; i < noOfDays; i++) {
            System.out.println("Day " + i);
            simulation.simulateDay();
        }
    }
}
