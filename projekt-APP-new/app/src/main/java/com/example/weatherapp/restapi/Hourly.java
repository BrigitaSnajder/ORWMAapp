package com.example.weatherapp.restapi;

import java.util.List;

public class Hourly {
    private int dt;
    private float temp;
    private float feels_like;
    private int humidity;
    private List<Weather> weather;

    public int getDt() {
        return dt;
    }

    public float getTemp() {
        return temp;
    }

    public float getFeels_like() {
        return feels_like;
    }

    public int getHumidity() {
        return humidity;
    }

    public List<Weather> getWeather() {
        return weather;
    }
}
