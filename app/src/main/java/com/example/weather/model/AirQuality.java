package com.example.weather.model;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AirQuality implements Parcelable
{

    @SerializedName("co")
    @Expose
    private Double co;
    @SerializedName("no2")
    @Expose
    private Double no2;
    @SerializedName("o3")
    @Expose
    private Double o3;
    @SerializedName("so2")
    @Expose
    private Double so2;
    @SerializedName("pm2_5")
    @Expose
    private Double pm25;
    @SerializedName("pm10")
    @Expose
    private Double pm10;
    @SerializedName("us-epa-index")
    @Expose
    private Integer usEpaIndex;
    @SerializedName("gb-defra-index")
    @Expose
    private Integer gbDefraIndex;
    public final static Creator<AirQuality> CREATOR = new Creator<AirQuality>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AirQuality createFromParcel(android.os.Parcel in) {
            return new AirQuality(in);
        }

        public AirQuality[] newArray(int size) {
            return (new AirQuality[size]);
        }

    }
            ;

    protected AirQuality(android.os.Parcel in) {
        this.co = ((Double) in.readValue((Double.class.getClassLoader())));
        this.no2 = ((Double) in.readValue((Double.class.getClassLoader())));
        this.o3 = ((Double) in.readValue((Double.class.getClassLoader())));
        this.so2 = ((Double) in.readValue((Double.class.getClassLoader())));
        this.pm25 = ((Double) in.readValue((Double.class.getClassLoader())));
        this.pm10 = ((Double) in.readValue((Double.class.getClassLoader())));
        this.usEpaIndex = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.gbDefraIndex = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public AirQuality() {
    }

    public Double getCo() {
        return co;
    }

    public void setCo(Double co) {
        this.co = co;
    }

    public Double getNo2() {
        return no2;
    }

    public void setNo2(Double no2) {
        this.no2 = no2;
    }

    public Double getO3() {
        return o3;
    }

    public void setO3(Double o3) {
        this.o3 = o3;
    }

    public Double getSo2() {
        return so2;
    }

    public void setSo2(Double so2) {
        this.so2 = so2;
    }

    public Double getPm25() {
        return pm25;
    }

    public void setPm25(Double pm25) {
        this.pm25 = pm25;
    }

    public Double getPm10() {
        return pm10;
    }

    public void setPm10(Double pm10) {
        this.pm10 = pm10;
    }

    public Integer getUsEpaIndex() {
        return usEpaIndex;
    }

    public void setUsEpaIndex(Integer usEpaIndex) {
        this.usEpaIndex = usEpaIndex;
    }

    public Integer getGbDefraIndex() {
        return gbDefraIndex;
    }

    public void setGbDefraIndex(Integer gbDefraIndex) {
        this.gbDefraIndex = gbDefraIndex;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(co);
        dest.writeValue(no2);
        dest.writeValue(o3);
        dest.writeValue(so2);
        dest.writeValue(pm25);
        dest.writeValue(pm10);
        dest.writeValue(usEpaIndex);
        dest.writeValue(gbDefraIndex);
    }

    public int describeContents() {
        return 0;
    }

}
