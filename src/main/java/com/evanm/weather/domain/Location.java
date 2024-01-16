package com.evanm.weather.domain;

import org.locationtech.jts.geom.Coordinate;

public class Location {
    private Address address;
    private Coordinate coordinate;

    public Location(Coordinate coordinate) {
        setCoordinate(coordinate);
    }

    public Location(Address address) {
        setAddress(address);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        if (this.address == null) { this.address = address; }
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        if (this.coordinate == null) { this.coordinate = coordinate; }
    }
}
