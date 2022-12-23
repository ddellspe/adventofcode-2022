package net.ddellspe.day22;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import net.ddellspe.utils.InputUtils;
import net.ddellspe.utils.Point;

public class Day22 {
  private Day22() {}

  public static long part1(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day22.class);
    String moves = lines.get(lines.size() - 1);
    Set<Point> points = new HashSet<>();
    Set<Point> walls = new HashSet<>();
    for (int y = 0; y < lines.size() - 2; y++) {
      String line = lines.get(y);
      for (int x = 0; x < line.length(); x++) {
        if (line.charAt(x) == '.') {
          points.add(new Point(x, y));
        } else if (line.charAt(x) == '#') {
          points.add(new Point(x, y));
          walls.add(new Point(x, y));
        }
      }
    }
    long maxX = points.stream().mapToLong(Point::getX).max().getAsLong();
    long maxY = points.stream().mapToLong(Point::getY).max().getAsLong();
    List<Point> xLimits =
        LongStream.range(0L, maxY + 1)
            .mapToObj(
                row -> points.stream().filter(p -> p.getY() == row).collect(Collectors.toList()))
            .map(
                l ->
                    new Point(
                        l.stream().mapToLong(Point::getX).min().getAsLong(),
                        l.stream().mapToLong(Point::getX).max().getAsLong()))
            .collect(Collectors.toList());
    List<Point> yLimits =
        LongStream.range(0L, maxX + 1)
            .mapToObj(
                col -> points.stream().filter(p -> p.getX() == col).collect(Collectors.toList()))
            .map(
                l ->
                    new Point(
                        l.stream().mapToLong(Point::getY).min().getAsLong(),
                        l.stream().mapToLong(Point::getY).max().getAsLong()))
            .collect(Collectors.toList());
    Point currentPoint =
        new Point(
            points.stream().filter(p -> p.getY() == 0L).mapToLong(Point::getX).min().getAsLong(),
            0);
    int direction = 0;
    while (moves.length() > 0) {
      int idx =
          !moves.contains("R")
              ? moves.indexOf("L")
              : (!moves.contains("L")
                  ? moves.indexOf("R")
                  : Math.min(moves.indexOf("R"), moves.indexOf("L")));
      long dist = Long.parseLong(moves.substring(0, idx == -1 ? moves.length() : idx));
      long xMove = 0;
      long yMove = 0;
      if (direction == 0) {
        xMove = 1;
      } else if (direction == 1) {
        yMove = 1;
      } else if (direction == 2) {
        xMove = -1;
      } else {
        yMove = -1;
      }
      for (long m = 0; m < dist; m++) {
        long newX = currentPoint.getX() + xMove;
        long newY = currentPoint.getY() + yMove;
        if (xMove == 0) {
          Point yLimit = yLimits.get((int) currentPoint.getX());
          if (newY < yLimit.getX()) {
            newY = yLimit.getY();
          } else if (newY > yLimit.getY()) {
            newY = yLimit.getX();
          }
        } else {
          Point xLimit = xLimits.get((int) currentPoint.getY());
          if (newX < xLimit.getX()) {
            newX = xLimit.getY();
          } else if (newX > xLimit.getY()) {
            newX = xLimit.getX();
          }
        }
        Point newPoint = new Point(newX, newY);
        if (walls.contains(newPoint)) {
          break;
        }
        currentPoint = newPoint;
      }
      if (idx == -1) {
        moves = "";
      } else {
        String move = String.valueOf(moves.charAt(idx));
        if (move.equals("R")) {
          direction++;
        } else {
          direction--;
        }
        direction = Math.floorMod(direction, 4);
        moves = moves.substring(idx + 1);
      }
    }
    //    printPuzzle(points, walls, maxX, maxY);
    return (currentPoint.getY() + 1) * 1000 + (currentPoint.getX() + 1) * 4 + direction;
  }

  //  public static void printPuzzle(Set<Point> points, Set<Point> walls, long maxX, long maxY) {
  //    for (int y = 0; y <= maxY; y++) {
  //      for (int x = 0; x <= maxX; x++) {
  //        Point pt = new Point(x, y);
  //        if (walls.contains(pt)) {
  //          System.out.print("#");
  //        } else if (points.contains(pt)) {
  //          System.out.print(".");
  //        } else {
  //          System.out.print(" ");
  //        }
  //      }
  //      System.out.println();
  //    }
  //  }

  public static long part2(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day22.class);
    return 0L;
  }
}
