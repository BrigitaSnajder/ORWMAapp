package com.example.weatherapp.restapi;

import java.util.List;

public class ResponseForecast {
    private int cod;
    private String message;
    private int cnt;
    private List<Data> list;
    private City city;

    public int getCod() {
        return cod;
    }

    public String getMessage() {
        return message;
    }

    public int getCnt() {
        return cnt;
    }

    public List<Data> getList() {
        return list;
    }

    public City getCity() {
        return city;
    }
}
