package net.ddellspe.utils;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Point {
  private int x;
  private int y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Point point = (Point) o;
    return x == point.x && y == point.y;
  }

  public Set<Point> getDirectNeighbors() {
    Set<Point> points = new HashSet<>();
    points.add(new Point(x + 1, y));
    points.add(new Point(x - 1, y));
    points.add(new Point(x, y + 1));
    points.add(new Point(x, y - 1));
    return points;
  }

  public Set<Point> getAllNeighbors() {
    Set<Point> points = new HashSet<>();
    points.add(new Point(x + 1, y + 1));
    points.add(new Point(x + 1, y));
    points.add(new Point(x + 1, y - 1));
    points.add(new Point(x, y + 1));
    points.add(new Point(x, y - 1));
    points.add(new Point(x - 1, y + 1));
    points.add(new Point(x - 1, y));
    points.add(new Point(x - 1, y - 1));
    return points;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  @Override
  public String toString() {
    return "Point [x=" + x + ", y=" + y + "]";
  }
}
