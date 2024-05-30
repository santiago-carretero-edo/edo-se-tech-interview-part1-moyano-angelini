package com.odigeo.interview.coding.battleshipservice.model.vessels;

public abstract class Vessel {
    private int size;
    private int startXPosition;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
