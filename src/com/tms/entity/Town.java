package com.tms.entity;

import java.util.Objects;

public class Town {
    private int id;
    private String name;
    private double longitude;
    private double latitude;
    private boolean hasAirport;
    private boolean hasSeaport;

    public Town(int id, String name, double longitude, double latitude, boolean hasAirport, boolean hasSeaport) {
        this.id = id;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.hasAirport = hasAirport;
        this.hasSeaport = hasSeaport;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public boolean isHasAirport() {
        return hasAirport;
    }

    public boolean isHasSeaport() {
        return hasSeaport;
    }

    public String toString(){
        return id+" "+name+" "+longitude+" "+latitude+" "+hasAirport+" "+hasSeaport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Town town = (Town) o;
        return id == town.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
