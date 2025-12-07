package com.ggit.simulation;

public interface WorldMap {
    int getHeight();
    int getWidth();
    void run();
    void feed();
    void breed();
    void startDay(int dayNumber);
    void endDay();
}
