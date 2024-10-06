package com.bloomtech;

import java.util.Objects;

public class Heading {
    private String direction;
    private float time;
    private float speedInKnots;

    public Heading(String direction, float time, float speedInKnots) {
        this.direction = direction;
        this.time = time;
        this.speedInKnots = speedInKnots;
    }

    public String getDirection() {
        // TODO: implement
        return this.direction;
    }

    public float getTime() {
        // TODO: implement
        return this.time;
    }

    public float getSpeedInKnots() {
        // TODO: implement
        return this.speedInKnots;
    }

    @Override
    public String toString() {

        return String.format("direction= %s, time= %.2f, speed=%.2f",direction,time,speedInKnots);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Heading heading = (Heading) o;
        return Float.compare(time, heading.time) == 0 && Float.compare(speedInKnots, heading.speedInKnots) == 0 && Objects.equals(direction, heading.direction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(direction, time, speedInKnots);
    }
}
