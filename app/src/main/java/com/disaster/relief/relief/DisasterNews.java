package com.disaster.relief.relief;

/**
 * Created by aryan on 26/10/18.
 */

public class DisasterNews {
    private String name;
    private String description;
    private String date;
    private double latitude;
    private double longitude;

    public DisasterNews(){

    }

    public DisasterNews(String name, String description, String date, double latitude, double longitude)
    {
        this.name=name;
        this.description=description;
        this.date= date;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
