package com.evanm.weather.converters;

import org.geotools.geometry.jts.WKTReader2;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.io.ParseException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class BytesToPolygonConverter implements Converter<byte[], Polygon> {
    private final WKTReader2 reader;

    public BytesToPolygonConverter() {
        reader = new WKTReader2();
    }
    
    @Override
    public Polygon convert(final byte[] source) {
        try {
            return (Polygon) reader.read(new String(source));
        } catch (ParseException pe) {
            return null;
        }
    }

}