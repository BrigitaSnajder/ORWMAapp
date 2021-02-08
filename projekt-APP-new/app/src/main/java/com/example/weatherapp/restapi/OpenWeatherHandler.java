package com.example.weatherapp.restapi;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OpenWeatherHandler {
    private static final String URL = "https://api.openweathermap.org/data/2.5/";
    private static final String EXCLUDE = "minutely,daily";
    private static final String APPID = "3c6d609b2273dfcc883ae805d2d51df6";
    private static final String UNITS = "metric";
    private static IJsonPlaceholder iJsonPlaceholder;


    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static Call<ResponseToday> TodayWeather(float lat, float lon){
        iJsonPlaceholder = retrofit.create(IJsonPlaceholder.class);
        return iJsonPlaceholder.TodayWeather(lat, lon, EXCLUDE, APPID, UNITS);
    }

    public static Call<ResponseForecast> ForecastWeather(String query){
        iJsonPlaceholder = retrofit.create(IJsonPlaceholder.class);
        return iJsonPlaceholder.ForecastWeather(query, APPID, UNITS);
    }

}
