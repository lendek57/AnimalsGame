package com.ggit;

public abstract class AbstractWorldMap implements WorldMap {
    protected int width;
    protected int height;

    protected AbstractWorldMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }
}
