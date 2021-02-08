package com.example.weatherapp.restapi;

public class Main {
    private float temp;
    private float feels_like;
    private int humidity;

    public Main(float temp, float feels_like, int humidity) {
        this.temp = temp;
        this.feels_like = feels_like;
        this.humidity = humidity;
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

}
