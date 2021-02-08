package com.example.weatherapp.restapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IJsonPlaceholder {
    @GET("onecall")
    Call<ResponseToday> TodayWeather(@Query("lat") float lat, @Query("lon") float lon, @Query("exclude") String exclude, @Query("appid") String appid, @Query("units") String units);

    @GET("forecast")
    Call<ResponseForecast> ForecastWeather(@Query("q") String query, @Query("appid") String appid, @Query("units") String units);
}
