package com.igorgrigorewski.weatherapp.api;

import com.igorgrigorewski.weatherapp.content.CurrentWeatherData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiService {
    @GET
    Call<CurrentWeatherData> getWeather(@Url String location);
}
