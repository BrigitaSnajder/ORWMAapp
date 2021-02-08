package com.example.weatherapp.restapi;

import java.util.List;

public class ResponseToday {
    private List<Hourly> hourly;

    public List<Hourly> getHourly() {
        return hourly;
    }
}
