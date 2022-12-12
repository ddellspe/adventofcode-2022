package net.ddellspe.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class Point3DTest {
  @Test
  public void testEqualsAndHash() {
    Point3D orig = new Point3D(10, 20, 30);
    Point3D same = new Point3D(10, 20, 30);
    assertEquals(orig, same);
    assertEquals(orig.hashCode(), same.hashCode());
    assertEquals(orig, orig);
    //noinspection SimplifiableAssertion, ConstantConditions
    assertFalse(orig.equals(null));
    assertFalse(orig.equals(""));
    assertNotEquals(orig, new Point3D(10, 20, 31));
    assertNotEquals(orig, new Point3D(10, 21, 30));
    assertNotEquals(orig, new Point3D(11, 20, 30));
    assertEquals("Point3D [x=10, y=20, z=30]", orig.toString());
  }

  @Test
  public void testSetters() {
    Point3D orig = new Point3D(10, 20, 30);
    Point3D same = new Point3D(40, 50, 60);
    orig.setX(40);
    orig.setY(50);
    orig.setZ(60);
    assertEquals(orig, same);
  }

  @Test
  public void testGetters() {
    Point3D orig = new Point3D(10, 20, 30);
    assertEquals(10, orig.getX());
    assertEquals(20, orig.getY());
    assertEquals(30, orig.getZ());
  }

  @Test
  public void testManhattanDistance() {
    Point3D pt1 = new Point3D(10, 20, 30);
    Point3D pt2 = new Point3D(40, 50, 60);
    assertEquals(90L, pt1.manhattanDistance(pt2));
  }

  @Test
  public void testDifferenceCalculatesOriginal() {
    Point3D pt1 = new Point3D(10, 20, 30);
    Point3D pt2 = new Point3D(40, 50, 60);
    List<Point3D> differences = pt1.getDifference(pt2);
    for (int i = 0; i < differences.size(); i++) {
      assertEquals(pt1, pt2.getPointWithDiff(differences.get(i), i));
    }
  }

  @Test
  public void testNeighbors() {
    Point3D orig = new Point3D(10, 20, 30);
    Set<Point3D> neighbors = new HashSet<>();
    neighbors.add(new Point3D(9, 20, 30));
    neighbors.add(new Point3D(11, 20, 30));
    neighbors.add(new Point3D(10, 19, 30));
    neighbors.add(new Point3D(10, 21, 30));
    neighbors.add(new Point3D(10, 20, 29));
    neighbors.add(new Point3D(10, 20, 31));
    assertEquals(neighbors, orig.getDirectNeighbors());
    neighbors.add(new Point3D(9, 19, 29));
    neighbors.add(new Point3D(9, 19, 30));
    neighbors.add(new Point3D(9, 19, 31));
    neighbors.add(new Point3D(9, 20, 29));
    neighbors.add(new Point3D(9, 20, 31));
    neighbors.add(new Point3D(9, 21, 29));
    neighbors.add(new Point3D(9, 21, 30));
    neighbors.add(new Point3D(9, 21, 31));
    neighbors.add(new Point3D(10, 19, 29));
    neighbors.add(new Point3D(10, 19, 31));
    neighbors.add(new Point3D(10, 21, 29));
    neighbors.add(new Point3D(10, 21, 31));
    neighbors.add(new Point3D(11, 19, 29));
    neighbors.add(new Point3D(11, 19, 30));
    neighbors.add(new Point3D(11, 19, 31));
    neighbors.add(new Point3D(11, 20, 29));
    neighbors.add(new Point3D(11, 20, 31));
    neighbors.add(new Point3D(11, 21, 29));
    neighbors.add(new Point3D(11, 21, 30));
    neighbors.add(new Point3D(11, 21, 31));
    assertEquals(neighbors, orig.getAllNeighbors());
  }
}
