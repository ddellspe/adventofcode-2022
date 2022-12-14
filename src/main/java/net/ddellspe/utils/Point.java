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

  public Point(String point) {
    this.x = Integer.parseInt(point.split(",")[0]);
    this.y = Integer.parseInt(point.split(",")[1]);
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

  public Set<Point> getPointsBetween(Point point) {
    if (this.x != point.x && this.y != point.y) {
      throw new IllegalStateException("Points must either share an X or Y coordinate");
    }
    Set<Point> pointsBetween = new HashSet<>();
    if (this.x < point.x) {
      for (int x = this.x; x <= point.x; x++) {
        pointsBetween.add(new Point(x, this.y));
      }
    } else if (this.x > point.x) {
      for (int x = point.x; x <= this.x; x++) {
        pointsBetween.add(new Point(x, this.y));
      }
    } else {
      if (this.y < point.y) {
        for (int y = this.y; y <= point.y; y++) {
          pointsBetween.add(new Point(this.x, y));
        }
      } else {
        for (int y = point.y; y <= this.y; y++) {
          pointsBetween.add(new Point(this.x, y));
        }
      }
    }
    return pointsBetween;
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
