package com.evanm.weather.converters;

import org.geotools.geometry.jts.WKTWriter2;
import org.locationtech.jts.geom.Polygon;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class PolygonToBytesConverter implements Converter<Polygon, byte[]> {
    private final WKTWriter2 writer;

    public PolygonToBytesConverter() {
        writer = new WKTWriter2();
    }
    
    @Override
    public byte[] convert(final Polygon source) {
        return writer.write(source).getBytes();
    }
}
