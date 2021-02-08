package com.example.weatherapp.restapi;

public class Weather {
    private String main;
    private String icon;

    public Weather(String main, String icon) {
        this.main = main;
        this.icon = icon;
    }

    public String getMain() {
        return main;
    }

    public String getIcon() {
        return icon;
    }
}
