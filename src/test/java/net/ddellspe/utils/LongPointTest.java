package net.ddellspe.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class LongPointTest {
  @Test
  public void testEqualsAndHash() {
    LongPoint orig = new LongPoint(10, 20);
    LongPoint same = new LongPoint(10, 20);
    assertEquals(orig, same);
    assertEquals(orig.hashCode(), same.hashCode());
    assertEquals(orig, orig);
    //noinspection SimplifiableAssertion, ConstantConditions
    assertFalse(orig.equals(null));
    assertEquals("LongPoint [x=10, y=20]", orig.toString());
  }

  @Test
  public void testSetters() {
    LongPoint orig = new LongPoint(10, 20);
    LongPoint same = new LongPoint(30, 40);
    orig.setX(30);
    orig.setY(40);
    assertEquals(orig, same);
  }

  @Test
  public void testGetters() {
    LongPoint orig = new LongPoint(10, 20);
    assertEquals(10, orig.getX());
    assertEquals(20, orig.getY());
  }
}
