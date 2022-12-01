package net.ddellspe.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Point3D {
  private int x;
  private int y;
  private int z;

  public Point3D(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Point3D point = (Point3D) o;
    return x == point.x && y == point.y && z == point.z;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getZ() {
    return z;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public void setZ(int z) {
    this.z = z;
  }

  public List<Point3D> getDifference(Point3D pt) {
    List<Point3D> points = new ArrayList<>();
    for (int i = 0; i < 6; i++) {
      int x = pt.x;
      int y = pt.y;
      int z = pt.z;
      switch (i) {
        case 1:
          y = pt.z;
          z = pt.y;
          break;
        case 2:
          x = pt.y;
          y = pt.x;
          break;
        case 3:
          x = pt.y;
          y = pt.z;
          z = pt.x;
          break;
        case 4:
          x = pt.z;
          z = pt.x;
          break;
        case 5:
          x = pt.z;
          y = pt.x;
          z = pt.y;
          break;
      }
      for (int j = 0; j < 8; j++) {
        points.add(
            new Point3D(
                this.x + x * (j / 4 % 2 == 0 ? -1 : 1),
                this.y + y * (j / 2 % 2 == 0 ? -1 : 1),
                this.z + z * (j % 2 == 0 ? -1 : 1)));
      }
    }
    return points;
  }

  public Point3D getPointWithDiff(Point3D point, int orientation) {
    int x = this.x;
    int y = this.y;
    int z = this.z;
    switch (orientation / 8) {
      case 1:
        y = this.z;
        z = this.y;
        break;
      case 2:
        x = this.y;
        y = this.x;
        break;
      case 3:
        x = this.y;
        y = this.z;
        z = this.x;
        break;
      case 4:
        x = this.z;
        z = this.x;
        break;
      case 5:
        x = this.z;
        y = this.x;
        z = this.y;
        break;
    }
    int ori = orientation % 8;
    return new Point3D(
        point.getX() + (x * ((ori / 4 % 2 == 0) ? 1 : -1)),
        point.getY() + (y * ((ori / 2 % 2 == 0) ? 1 : -1)),
        point.getZ() + (z * ((ori % 2 == 0) ? 1 : -1)));
  }

  public long manhattanDistance(Point3D pt2) {
    return Math.abs(x - pt2.getX()) + Math.abs(y - pt2.getY()) + Math.abs(z - pt2.getZ());
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y, z);
  }

  @Override
  public String toString() {
    return "Point3D [x=" + x + ", y=" + y + ", z=" + z + "]";
  }
}
