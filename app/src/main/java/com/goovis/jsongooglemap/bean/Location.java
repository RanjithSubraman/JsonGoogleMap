package com.goovis.jsongooglemap.bean;

/**
 * Created by Ranjith Subramaniam on 03/28/2017
 */
public class Location {
    private int id;
    private String name;
    private String address;
    private double longitude;
    private double latitude;
    private String time;

    public Location(int id, String name, String address, double longitude, double latitude, String time) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getTime() {
        return time;
    }

}
