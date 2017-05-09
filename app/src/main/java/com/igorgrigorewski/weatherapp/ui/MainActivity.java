package com.igorgrigorewski.weatherapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.igorgrigorewski.weatherapp.R;
import com.igorgrigorewski.weatherapp.WeatherController;
import com.igorgrigorewski.weatherapp.api.ApiService;
import com.igorgrigorewski.weatherapp.api.RetroClient;
import com.igorgrigorewski.weatherapp.content.CurrentWeatherData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView TemperatureTextView;
    private TextView CityTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TemperatureTextView = (TextView) findViewById(R.id.temperature);
        CityTextView = (TextView) findViewById(R.id.city);

        onWeatherUpdate();
    }

    private void onWeatherUpdate(){
        RetroClient.getApiService().getWeather().enqueue(new Callback<CurrentWeatherData>() {
            @Override
            public void onResponse(Call<CurrentWeatherData> call, Response<CurrentWeatherData> response) {
                if (response.isSuccessful()){
                    CurrentWeatherData currentWeatherData = response.body();
                    CityTextView.setText(currentWeatherData.getName());
                    TemperatureTextView.setText(currentWeatherData.getMain().getTemp().toString());
                }
            }

            @Override
            public void onFailure(Call<CurrentWeatherData> call, Throwable t) {
                Log.d("ERROR", "api response isn't successful");
            }
        });
    }
}
