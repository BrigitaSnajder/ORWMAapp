package com.example.weatherapp.restapi;

import java.util.List;

public class Data {
    private long dt;
    private Main main;
    private List<Weather> weather;

    public Data(long dt, Main main, List<Weather> weather) {
        this.dt = dt;
        this.main = main;
        this.weather = weather;
    }

    public long getDt() {
        return dt;
    }

    public Main getMain() {
        return main;
    }

    public List<Weather> getWeather() {
        return weather;
    }
}
