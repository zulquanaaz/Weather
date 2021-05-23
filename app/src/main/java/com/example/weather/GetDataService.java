package com.example.weather;


import com.example.weather.model.Example;

import retrofit2.Call;
import retrofit2.http.GET;


public interface GetDataService {

    @GET("forecast.json?key="+BuildConfig.API_KEY+"&q=Montreal&days=5&aqi=no&alerts=no")
    Call<Example> getMontrealWeather();

    @GET("forecast.json?key="+BuildConfig.API_KEY+"&q=London&days=5&aqi=no&alerts=no/")
    Call<Example> getLondonWeather();

    @GET("forecast.json?key="+BuildConfig.API_KEY+"&q=Mumbai&days=5&aqi=no&alerts=no/")
    Call<Example> getMumbaiWeather();

    @GET("forecast.json?key="+BuildConfig.API_KEY+"&q=Vancouver&days=5&aqi=no&alerts=no/")
    Call<Example> getVancouverWeather();

    @GET("forecast.json?key="+BuildConfig.API_KEY+"&q=Toronto&days=5&aqi=no&alerts=no/")
    Call<Example> getTorontoWeather();
}
