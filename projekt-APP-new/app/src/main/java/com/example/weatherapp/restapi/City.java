package com.example.weatherapp.restapi;

public class City {
    private String name;
    private Coord coord;
    private int sunrise;
    private int sunset;

    public String getName() {
        return name;
    }

    public Coord getCoord() {
        return coord;
    }

    public int getSunrise() {
        return sunrise;
    }

    public int getSunset() {
        return sunset;
    }
}
