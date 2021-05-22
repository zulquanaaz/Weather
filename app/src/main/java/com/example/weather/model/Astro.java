package com.example.weather.model;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Astro implements Parcelable
{

    @SerializedName("sunrise")
    @Expose
    private String sunrise;
    @SerializedName("sunset")
    @Expose
    private String sunset;
    @SerializedName("moonrise")
    @Expose
    private String moonrise;
    @SerializedName("moonset")
    @Expose
    private String moonset;
    @SerializedName("moon_phase")
    @Expose
    private String moonPhase;
    @SerializedName("moon_illumination")
    @Expose
    private String moonIllumination;
    public final static Creator<Astro> CREATOR = new Creator<Astro>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Astro createFromParcel(android.os.Parcel in) {
            return new Astro(in);
        }

        public Astro[] newArray(int size) {
            return (new Astro[size]);
        }

    }
            ;

    protected Astro(android.os.Parcel in) {
        this.sunrise = ((String) in.readValue((String.class.getClassLoader())));
        this.sunset = ((String) in.readValue((String.class.getClassLoader())));
        this.moonrise = ((String) in.readValue((String.class.getClassLoader())));
        this.moonset = ((String) in.readValue((String.class.getClassLoader())));
        this.moonPhase = ((String) in.readValue((String.class.getClassLoader())));
        this.moonIllumination = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Astro() {
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getMoonrise() {
        return moonrise;
    }

    public void setMoonrise(String moonrise) {
        this.moonrise = moonrise;
    }

    public String getMoonset() {
        return moonset;
    }

    public void setMoonset(String moonset) {
        this.moonset = moonset;
    }

    public String getMoonPhase() {
        return moonPhase;
    }

    public void setMoonPhase(String moonPhase) {
        this.moonPhase = moonPhase;
    }

    public String getMoonIllumination() {
        return moonIllumination;
    }

    public void setMoonIllumination(String moonIllumination) {
        this.moonIllumination = moonIllumination;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(sunrise);
        dest.writeValue(sunset);
        dest.writeValue(moonrise);
        dest.writeValue(moonset);
        dest.writeValue(moonPhase);
        dest.writeValue(moonIllumination);
    }

    public int describeContents() {
        return 0;
    }

}
