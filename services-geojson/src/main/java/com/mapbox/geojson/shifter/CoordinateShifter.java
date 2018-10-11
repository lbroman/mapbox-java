package com.mapbox.geojson.shifter;

public interface CoordinateShifter {

  double shiftLat(double lat);

  double shiftLon(double lon);

}
