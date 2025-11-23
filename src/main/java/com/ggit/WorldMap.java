package com.ggit;

public interface WorldMap {
    int getHeight();
    int getWidth();
    void run();
    void feed();
    void startDay(int dayNumber);
    void endDay();
}
