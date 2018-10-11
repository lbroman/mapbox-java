package com.mapbox.geojson.shifter;

import com.mapbox.geojson.BoundingBox;
import com.mapbox.geojson.Point;

import static com.mapbox.core.TestUtils.DELTA;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ShifterTest {

  static class TestCoordinateShifter implements CoordinateShifter {
    @Override
    public double shiftLat(double lat) {
      return lat + 5;
    }
    @Override
    public double shiftLon(double lon) {
      // algorithm
      return lon + 5;
    }
  };

  @Test
  public void sanity() throws Exception {

    Point southwest = Point.fromLngLat(2.0, 2.0);
    Point northeast = Point.fromLngLat(4.0, 4.0);


    CoordinateShifter shifter = new TestCoordinateShifter();
    CoordinateShifterManager.setCoordinateShifter(shifter);

    Point southwestShifted = Point.fromLngLat(southwest.longitude(), southwest.latitude());
    Point northeastShifted = Point.fromLngLat(northeast.longitude(), northeast.latitude());

    assertEquals(shifter.shiftLat(southwest.latitude()),
      southwestShifted.latitude(), DELTA);
    assertEquals(shifter.shiftLon(southwest.longitude()),
      southwestShifted.longitude(), DELTA);

    assertEquals(shifter.shiftLat(northeast.latitude()),
      northeastShifted.latitude(), DELTA);
    assertEquals(shifter.shiftLon(northeast.longitude()),
      northeastShifted.longitude(), DELTA);

    BoundingBox boundingBoxFromDouble = BoundingBox.fromLngLats(2.0, 2.0, 4.0, 4.0);
    BoundingBox boundingBoxFromPoints = BoundingBox.fromPoints(southwestShifted, northeastShifted);

    assertEquals(boundingBoxFromDouble, boundingBoxFromPoints);

    CoordinateShifterManager.setCoordinateShifter(null);
  }
}
