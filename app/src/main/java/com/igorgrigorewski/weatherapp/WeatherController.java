package com.igorgrigorewski.weatherapp;

import android.os.Debug;
import android.util.Log;

import com.igorgrigorewski.weatherapp.api.ApiService;
import com.igorgrigorewski.weatherapp.api.RetroClient;
import com.igorgrigorewski.weatherapp.content.CurrentWeatherData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class WeatherController {
    static private ApiService api = RetroClient.getApiService();
    static private CurrentWeatherData currentWeatherData;

    public static void updateMainInformation(){
        if (api == null)
            api = RetroClient.getApiService();
        Call<CurrentWeatherData> call  = api.getWeather();
        call.enqueue(new Callback<CurrentWeatherData>() {
            @Override
            public void onResponse(Call<CurrentWeatherData> call, Response<CurrentWeatherData> response) {
                if (response.isSuccessful()){
                    currentWeatherData = response.body();
                }
            }

            @Override
            public void onFailure(Call<CurrentWeatherData> call, Throwable t) {
                Log.d("ERROR", "onFailure");
            }
        });
    }

    public static double getTemperature(){
        if (currentWeatherData != null)
            return currentWeatherData.getMain().getTemp();
        else return 0.0;
    }

    public static String getCity(){
        if (currentWeatherData != null)
            return currentWeatherData.getName();
        else return "NA";
    }
}
