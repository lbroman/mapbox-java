package com.mapbox.geojson.shifter;

public final class CoordinateShifterManager {

  private static final CoordinateShifter DEFAULT = new CoordinateShifter() {
    @Override
    public double shiftLat(double lat) {
      return lat;
    }

    @Override
    public double shiftLon(double lon) {
      return lon;
    }
  };

  private static volatile CoordinateShifter coordinateShifter = DEFAULT;

  public static CoordinateShifter getCoordinateShifter() {
    return coordinateShifter;
  }

  public static void setCoordinateShifter(CoordinateShifter coordinateShifter) {
    CoordinateShifterManager.coordinateShifter = coordinateShifter == null ? DEFAULT : coordinateShifter;
  }

}
