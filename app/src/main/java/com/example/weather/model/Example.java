package com.example.weather.model;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example implements Parcelable
{

    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("current")
    @Expose
    private Current current;
    @SerializedName("forecast")
    @Expose
    private Forecast forecast;
    public final static Creator<Example> CREATOR = new Creator<Example>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Example createFromParcel(android.os.Parcel in) {
            return new Example(in);
        }

        public Example[] newArray(int size) {
            return (new Example[size]);
        }

    }
            ;

    protected Example(android.os.Parcel in) {
        this.location = ((Location) in.readValue((Location.class.getClassLoader())));
        this.current = ((Current) in.readValue((Current.class.getClassLoader())));
        this.forecast = ((Forecast) in.readValue((Forecast.class.getClassLoader())));
    }

    public Example() {
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(location);
        dest.writeValue(current);
        dest.writeValue(forecast);
    }

    public int describeContents() {
        return 0;
    }

}