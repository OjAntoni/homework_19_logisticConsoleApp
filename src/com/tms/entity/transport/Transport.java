package com.tms.entity.transport;

import com.tms.entity.transport.transport_type.TransportType;

import java.util.Objects;

public class Transport {
    private int id;
    private String name;
    private int speed;
    private int peopleCapacity;
    private int loadCapacity;
    private TransportType transportType;
    private int pricePerKm;

    public Transport(int id, String name, int speed, int peopleCapacity, int loadCapacity,
                     TransportType transportType, int pricePerKm) {
        this.id = id;
        this.name = name;
        this.speed = speed;
        this.peopleCapacity = peopleCapacity;
        this.loadCapacity = loadCapacity;
        this.transportType = transportType;
        this.pricePerKm = pricePerKm;
    }

    public String getType(){
        return transportType.getType();
    }

    public String toString(){
        return id+" "+name+" "+speed+" "+peopleCapacity+" "+loadCapacity+" "+transportType.getType()+" "+pricePerKm;
    }

    public String toStringForSave(){
        return id+" "+name.replaceAll(" ","-")+" "+speed+" "+peopleCapacity+" "+loadCapacity+
                " "+transportType.getType()+" "+pricePerKm;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getSpeed() {
        return speed;
    }

    public int getPeopleCapacity() {
        return peopleCapacity;
    }

    public int getLoadCapacity() {
        return loadCapacity;
    }

    public int getPricePerKm() {
        return pricePerKm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transport transport = (Transport) o;
        return id == transport.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
