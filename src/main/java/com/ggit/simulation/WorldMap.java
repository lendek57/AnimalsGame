package com.ggit.simulation;

import java.util.List;
import java.util.Map;

public interface WorldMap {
    int getHeight();
    int getWidth();
    void run();
    void feed();
    void breed();
    void startDay(int dayNumber);
    void endDay();
    Map<Vector2D, List<Animal>> animalsMap();
    Map<Vector2D, Plant> plantsLocations();
}
