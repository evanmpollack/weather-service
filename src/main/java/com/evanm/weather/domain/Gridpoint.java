package com.evanm.weather.domain;

import java.io.Serializable;

public class Gridpoint implements Serializable {
    private String office;
    private int gridX;
    private int gridY;
    private String wktPolygon;

    public Gridpoint(String office, int gridX, int gridY, String wktPolygon) {
        this.office = office;
        this.gridX = gridX;
        this.gridY = gridY;
        this.wktPolygon = wktPolygon;
    }

    public String getOffice() {
        return office;
    }

    public int getGridX() {
        return gridX;
    }

    public int getGridY() {
        return gridY;
    }

    public String getWktPolygon() {
        return wktPolygon;
    }
}
