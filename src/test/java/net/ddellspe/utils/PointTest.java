package net.ddellspe.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class PointTest {
  @Test
  public void testEqualsAndHash() {
    Point orig = new Point(10, 20);
    Point same = new Point(10, 20);
    assertEquals(orig, same);
    assertEquals(orig.hashCode(), same.hashCode());
    assertEquals(orig, orig);
    //noinspection SimplifiableAssertion, ConstantConditions
    assertFalse(orig.equals(null));
    assertEquals("Point [x=10, y=20]", orig.toString());
  }

  @Test
  public void testSetters() {
    Point orig = new Point(10, 20);
    Point same = new Point(30, 40);
    orig.setX(30);
    orig.setY(40);
    assertEquals(orig, same);
  }

  @Test
  public void testGetters() {
    Point orig = new Point(10, 20);
    assertEquals(10, orig.getX());
    assertEquals(20, orig.getY());
  }
}
