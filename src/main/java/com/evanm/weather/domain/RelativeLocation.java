package com.evanm.weather.domain;

import org.locationtech.jts.geom.Coordinate;

public class RelativeLocation {
    private Coordinate coordinate;
    private RelativeAddress address;

    public RelativeLocation(Coordinate coordinate, RelativeAddress address) {
        this.coordinate = coordinate;
        this.address = address;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public RelativeAddress getAddress() {
        return address;
    }
}
