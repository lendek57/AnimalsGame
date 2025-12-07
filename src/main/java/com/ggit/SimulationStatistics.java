package com.ggit;

public record SimulationStatistics(
        int dayNumber,
        double meanAge,
        double meanNumberOfChildren,
        double meanEnergy,
        int animalsCount
) {
}
