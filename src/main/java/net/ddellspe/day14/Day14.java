package net.ddellspe.day14;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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
    Point newSand = placeSand(sand, origin, yMax);
    while (newSand != null) {
      sand.add(newSand);
      newSand = placeSand(sand, origin, yMax);
    }
    sand.removeAll(rocks);
    //    printPuzzle(rocks, sand);
    return sand.size();
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
    long yMax = rocks.stream().map(Point::getY).distinct().max(Comparator.naturalOrder()).get() + 2;
    for (int x = -500; x <= 1500; x++) {
      rocks.add(new Point(x, yMax));
    }
    Set<Point> sand = new HashSet<>(rocks);
    Point origin = new Point(500, 0);
    Point newSand = placeSand(sand, origin, yMax);
    while (newSand != null) {
      sand.add(newSand);
      newSand = placeSand(sand, origin, yMax);
    }
    sand.removeAll(rocks);
    //    printPuzzle(rocks, sand);
    return sand.size();
  }

  public static Point placeSand(Set<Point> sand, Point origin, long yMax) {
    if (origin.getY() >= yMax || sand.contains(origin)) {
      return null;
    }
    long x = origin.getX();
    Optional<Long> lastY =
        sand.stream()
            .filter(pt -> pt.getX() == origin.getX() && pt.getY() >= origin.getY())
            .map(Point::getY)
            .distinct()
            .min(Comparator.naturalOrder());
    if (lastY.isEmpty()) {
      return null;
    }
    long y = lastY.get() - 1;
    if (sand.contains(new Point(x - 1, y + 1))) {
      if (sand.contains(new Point(x + 1, y + 1))) {
        return new Point(x, y);
      } else {
        return placeSand(sand, new Point(x + 1, y + 1), yMax);
      }
    } else {
      return placeSand(sand, new Point(x - 1, y + 1), yMax);
    }
  }

  //  public static void printPuzzle(Set<Point> rocks, Set<Point> sand) {
  //    int xMin = sand.stream().map(Point::getX).distinct().min(Comparator.naturalOrder()).get() -
  // 3;
  //    int xMax = sand.stream().map(Point::getX).distinct().max(Comparator.naturalOrder()).get() +
  // 3;
  //    int yMin = sand.stream().map(Point::getY).distinct().min(Comparator.naturalOrder()).get();
  //    int yMax = sand.stream().map(Point::getY).distinct().max(Comparator.naturalOrder()).get() +
  // 3;
  //    for (int y = Math.min(yMin, 0); y <= yMax; y++) {
  //      for (int x = xMin; x <= xMax; x++) {
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
