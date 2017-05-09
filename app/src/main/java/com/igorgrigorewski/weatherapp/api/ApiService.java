package com.igorgrigorewski.weatherapp.api;

import com.igorgrigorewski.weatherapp.content.CurrentWeatherData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/data/2.5/weather?q=Odessa&units=metric&appid=501001a080ff4c6d00cbf90caf41f64d")
    Call<CurrentWeatherData> getWeather();
}
