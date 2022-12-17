package net.ddellspe.day14;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.ddellspe.utils.InputUtils;
import net.ddellspe.utils.Point;

public class Day14 {
  private Day14() {}

  public static long part1(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day14.class);
    Set<Point> rocks = new HashSet<>();
    for (String line : lines) {
      Point prevPoint = null;
      for (String coord : line.split(" -> ")) {
        if (prevPoint == null) {
          prevPoint = new Point(coord);
          continue;
        }
        Point point = new Point(coord);
        rocks.addAll(point.getPointsBetween(prevPoint));
        prevPoint = point;
      }
    }
    long yMax = rocks.stream().map(Point::getY).distinct().max(Comparator.naturalOrder()).get();
    Set<Point> sand = new HashSet<>(rocks);
    Point origin = new Point(500, 0);
    Point newSand = placeSandPart1(sand, origin, yMax);
    while (newSand != null) {
      sand.add(newSand);
      newSand = placeSandPart1(sand, origin, yMax);
    }
    sand.removeAll(rocks);
    //    printPuzzle(rocks, sand);
    return sand.size();
  }

  public static Point placeSandPart1(Set<Point> sand, Point origin, long yMax) {
    Point finalPoint = null;
    long x = origin.getX();
    long y = origin.getY();
    while (y <= yMax) {
      if (!sand.contains(new Point(x, y + 1))) {
        y++;
      } else if (!sand.contains(new Point(x - 1, y + 1))) {
        x--;
        y++;
      } else if (!sand.contains(new Point(x + 1, y + 1))) {
        x++;
        y++;
      } else {
        finalPoint = new Point(x, y);
        break;
      }
    }
    return finalPoint;
  }

  public static long part2(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day14.class);
    Set<Point> rocks = new HashSet<>();
    for (String line : lines) {
      Point prevPoint = null;
      for (String coord : line.split(" -> ")) {
        if (prevPoint == null) {
          prevPoint = new Point(coord);
          continue;
        }
        Point point = new Point(coord);
        rocks.addAll(point.getPointsBetween(prevPoint));
        prevPoint = point;
      }
    }
    long yMax = rocks.stream().mapToLong(Point::getY).distinct().max().getAsLong();
    Set<Point> sand = new HashSet<>(rocks);
    Point origin = new Point(500, 0);
    Point newSand = placeSandPart2(sand, origin, yMax);
    while (newSand != null) {
      sand.add(newSand);
      newSand = placeSandPart2(sand, origin, yMax);
    }
    sand.removeAll(rocks);
    //    printPuzzle(rocks, sand);
    return sand.size();
  }

  public static Point placeSandPart2(Set<Point> sand, Point origin, long yMax) {
    Point finalPoint = null;
    long x = origin.getX();
    long y = origin.getY();
    while (y <= yMax + 2) {
      if (sand.contains(new Point(x, y))) {
        break;
      } else if (y == yMax + 1) {
        finalPoint = new Point(x, y);
        y++;
      } else if (!sand.contains(new Point(x, y + 1))) {
        y++;
      } else if (!sand.contains(new Point(x - 1, y + 1))) {
        x--;
        y++;
      } else if (!sand.contains(new Point(x + 1, y + 1))) {
        x++;
        y++;
      } else {
        finalPoint = new Point(x, y);
        break;
      }
    }
    return finalPoint;
  }

  //  public static void printPuzzle(Set<Point> rocks, Set<Point> sand) {
  //    long xMin = sand.stream().mapToLong(Point::getX).min().orElseGet(() -> 0L) - 3L;
  //    long xMax = sand.stream().mapToLong(Point::getX).max().orElseGet(() -> 0L) + 3;
  //    long yMin = sand.stream().mapToLong(Point::getY).min().orElseGet(() -> 0L);
  //    long yMax = sand.stream().mapToLong(Point::getY).max().orElseGet(() -> 0L) + 3;
  //    for (long y = Math.min(yMin, 0); y <= yMax; y++) {
  //      for (long x = xMin; x <= xMax; x++) {
  //        Point pt = new Point(x, y);
  //        if (rocks.contains(pt)) {
  //          System.out.print("#");
  //        } else if (sand.contains(pt)) {
  //          System.out.print("o");
  //        } else if (x == 500 && y == 0) {
  //          System.out.print("+");
  //        } else {
  //          System.out.print(".");
  //        }
  //      }
  //      System.out.println();
  //    }
  //  }
}
