package net.ddellspe.day22;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
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
  //
  //  public static void printPuzzle(Set<Point> walls, long maxX, long maxY) {
  //    System.out.println(walls);
  //    for (int y = 0; y < maxY; y++) {
  //      for (int x = 0; x < maxX; x++) {
  //        Point pt = new Point(x, y);
  //        if (walls.contains(pt)) {
  //          System.out.print("#");
  //        } else {
  //          System.out.print(".");
  //        }
  //      }
  //      System.out.println();
  //    }
  //  }

  public static Set<Point> rotateCounterClockwise(Set<Point> points, long size) {
    return points.stream()
        .map(p -> new Point(p.getY(), size - p.getX() - 1))
        .collect(Collectors.toSet());
  }

  public static Set<Point> rotateClockwise(Set<Point> points, long size) {
    return rotateCounterClockwise(
        rotateCounterClockwise(rotateCounterClockwise(points, size), size), size);
  }

  public static long part2(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day22.class);
    String moves = lines.get(lines.size() - 1);
    Set<Point> walls = new HashSet<>();
    for (int y = 0; y < lines.size() - 2; y++) {
      String line = lines.get(y);
      for (int x = 0; x < line.length(); x++) {
        if (line.charAt(x) == '#') {
          walls.add(new Point(x, y));
        }
      }
    }
    Set<Point> top; //     1
    Set<Point> left; //    2
    Set<Point> front; //   3
    Set<Point> right; //   4
    Set<Point> bottom; //  5
    Set<Point> back; //    6
    Map<Integer, Function<Point, Point>> pointTransform = new HashMap<>();
    Map<Integer, Integer> directionTransform = new HashMap<>();
    int size = lines.size() < 50 ? 4 : 50;
    if (size == 4) {
      //      pointTransform.put(1, p -> new Point(p.getX() + 8, p.getY()));
      pointTransform.put(2, p -> new Point(p.getX() + 4, p.getY() + 4));
      //      pointTransform.put(3, p -> new Point(p.getX() + 8, p.getY() + 4));
      //      pointTransform.put(4, p -> new Point(12 + size - p.getY() - 1, p.getX() + 8));
      //      pointTransform.put(5, p -> new Point(p.getX() + 8, p.getY() + 8));
      //      pointTransform.put(6, p -> new Point(p.getX(), p.getY() + 4));
      directionTransform.put(1, 0);
      directionTransform.put(2, 0);
      directionTransform.put(3, 0);
      directionTransform.put(4, 1);
      directionTransform.put(5, 0);
      directionTransform.put(6, 0);
      top =
          LongStream.range(0, 4)
              .mapToObj(y -> LongStream.range(8, 12).mapToObj(x -> new Point(x, y)))
              .flatMap(s -> s)
              .filter(walls::contains)
              .map(p -> new Point(p.getX() - 8, p.getY()))
              .collect(Collectors.toSet());
      front =
          LongStream.range(4, 8)
              .mapToObj(y -> LongStream.range(8, 12).mapToObj(x -> new Point(x, y)))
              .flatMap(s -> s)
              .filter(walls::contains)
              .map(p -> new Point(p.getX() - 8, p.getY() - 4))
              .collect(Collectors.toSet());
      bottom =
          LongStream.range(8, 12)
              .mapToObj(y -> LongStream.range(8, 12).mapToObj(x -> new Point(x, y)))
              .flatMap(s -> s)
              .filter(walls::contains)
              .map(p -> new Point(p.getX() - 8, p.getY() - 8))
              .collect(Collectors.toSet());
      back =
          LongStream.range(4, 8)
              .mapToObj(y -> LongStream.range(0, 4).mapToObj(x -> new Point(x, y)))
              .flatMap(s -> s)
              .filter(walls::contains)
              .map(p -> new Point(p.getX(), p.getY() - 4))
              .collect(Collectors.toSet());
      left =
          LongStream.range(4, 8)
              .mapToObj(y -> LongStream.range(4, 8).mapToObj(x -> new Point(x, y)))
              .flatMap(s -> s)
              .filter(walls::contains)
              .map(p -> new Point(p.getX() - 4, p.getY() - 4))
              .collect(Collectors.toSet());
      right =
          rotateCounterClockwise(
              LongStream.range(8, 12)
                  .mapToObj(y -> LongStream.range(12, 16).mapToObj(x -> new Point(x, y)))
                  .flatMap(s -> s)
                  .filter(walls::contains)
                  .map(p -> new Point(p.getX() - 12, p.getY() - 8))
                  .collect(Collectors.toSet()),
              size);
    } else {
      //      pointTransform.put(1, p -> new Point(p.getX() + 50, p.getY()));
      pointTransform.put(2, p -> new Point(p.getY(), 100 + 50 - p.getX() - 1));
      //      pointTransform.put(3, p -> new Point(p.getX() + 50, p.getY() + 50));
      //      pointTransform.put(4, p -> new Point(p.getY() + 100, 50 - p.getX() - 1));
      //      pointTransform.put(5, p -> new Point(p.getX() + 50, p.getY() + 100));
      //      pointTransform.put(6, p -> new Point(p.getY(), 150 + 50 - p.getX() - 1));
      directionTransform.put(1, 0);
      directionTransform.put(2, -1);
      directionTransform.put(3, 0);
      directionTransform.put(4, -1);
      directionTransform.put(5, 0);
      directionTransform.put(6, -1);
      top =
          LongStream.range(0, 50)
              .mapToObj(y -> LongStream.range(50, 100).mapToObj(x -> new Point(x, y)))
              .flatMap(s -> s)
              .filter(walls::contains)
              .map(p -> new Point(p.getX() - 50, p.getY()))
              .collect(Collectors.toSet());
      front =
          LongStream.range(50, 100)
              .mapToObj(y -> LongStream.range(50, 100).mapToObj(x -> new Point(x, y)))
              .flatMap(s -> s)
              .filter(walls::contains)
              .map(p -> new Point(p.getX() - 50, p.getY() - 50))
              .collect(Collectors.toSet());
      bottom =
          LongStream.range(100, 150)
              .mapToObj(y -> LongStream.range(50, 100).mapToObj(x -> new Point(x, y)))
              .flatMap(s -> s)
              .filter(walls::contains)
              .map(p -> new Point(p.getX() - 50, p.getY() - 100))
              .collect(Collectors.toSet());
      back =
          rotateClockwise(
              LongStream.range(150, 200)
                  .mapToObj(y -> LongStream.range(0, 50).mapToObj(x -> new Point(x, y)))
                  .flatMap(s -> s)
                  .filter(walls::contains)
                  .map(p -> new Point(p.getX(), p.getY() - 150))
                  .collect(Collectors.toSet()),
              size);
      left =
          rotateClockwise(
              LongStream.range(100, 150)
                  .mapToObj(y -> LongStream.range(0, 50).mapToObj(x -> new Point(x, y)))
                  .flatMap(s -> s)
                  .filter(walls::contains)
                  .map(p -> new Point(p.getX(), p.getY() - 100))
                  .collect(Collectors.toSet()),
              size);
      right =
          rotateClockwise(
              LongStream.range(0, 50)
                  .mapToObj(y -> LongStream.range(100, 150).mapToObj(x -> new Point(x, y)))
                  .flatMap(s -> s)
                  .filter(walls::contains)
                  .map(p -> new Point(p.getX() - 100, p.getY()))
                  .collect(Collectors.toSet()),
              size);
    }
    Point currentPoint = new Point(0, 0);
    Set<Point> currentFace = top;
    Map<Integer, Point> pointMap = new HashMap<>();
    Map<Integer, Integer> directionMap = new HashMap<>();
    Map<Integer, Integer> faceMap = new HashMap<>();
    int faceIdx = 1;
    int direction = 0;
    while (moves.length() > 0) {
      int idx =
          !moves.contains("R")
              ? moves.indexOf("L")
              : (!moves.contains("L")
                  ? moves.indexOf("R")
                  : Math.min(moves.indexOf("R"), moves.indexOf("L")));
      long dist = Long.parseLong(moves.substring(0, idx == -1 ? moves.length() : idx));
      for (long m = 0; m < dist; m++) {
        int newFaceIdx = faceIdx;
        int newDirection = direction;
        Set<Point> newFace = currentFace;
        Point newPoint;
        if (currentPoint.getX() == 0 && direction == 2) {
          faceMap.put(1, 2);
          directionMap.put(1, 1);
          pointMap.put(1, new Point(currentPoint.getY(), 0));
          faceMap.put(2, 6);
          directionMap.put(2, 2);
          pointMap.put(2, new Point(size - 1, currentPoint.getY()));
          faceMap.put(3, 2);
          directionMap.put(3, 2);
          pointMap.put(3, new Point(size - 1, currentPoint.getY()));
          faceMap.put(4, 3);
          directionMap.put(4, 2);
          pointMap.put(4, new Point(size - 1, currentPoint.getY()));
          faceMap.put(5, 2);
          directionMap.put(5, 3);
          pointMap.put(5, new Point(size - currentPoint.getY() - 1, size - 1));
          faceMap.put(6, 4);
          directionMap.put(6, 2);
          pointMap.put(6, new Point(size - 1, currentPoint.getY()));
          newPoint = pointMap.get(faceIdx);
          newDirection = directionMap.get(faceIdx);
          newFaceIdx = faceMap.get(faceIdx);
        } else if (currentPoint.getX() == (size - 1) && direction == 0) {
          faceMap.put(1, 4);
          directionMap.put(1, 1);
          pointMap.put(1, new Point(size - currentPoint.getY() - 1, 0));
          faceMap.put(2, 3);
          directionMap.put(2, 0);
          pointMap.put(2, new Point(0, currentPoint.getY()));
          faceMap.put(3, 4);
          directionMap.put(3, 0);
          pointMap.put(3, new Point(0, currentPoint.getY()));
          faceMap.put(4, 6);
          directionMap.put(4, 0);
          pointMap.put(4, new Point(0, currentPoint.getY()));
          faceMap.put(5, 4);
          directionMap.put(5, 3);
          pointMap.put(5, new Point(currentPoint.getY(), size - 1));
          faceMap.put(6, 2);
          directionMap.put(6, 0);
          pointMap.put(6, new Point(0, currentPoint.getY()));
          newPoint = pointMap.get(faceIdx);
          newDirection = directionMap.get(faceIdx);
          newFaceIdx = faceMap.get(faceIdx);
        } else if (currentPoint.getY() == 0 && direction == 3) {
          faceMap.put(1, 6);
          directionMap.put(1, 1);
          pointMap.put(1, new Point(size - currentPoint.getX() - 1, 0));
          faceMap.put(2, 1);
          directionMap.put(2, 0);
          pointMap.put(2, new Point(0, currentPoint.getX()));
          faceMap.put(3, 1);
          directionMap.put(3, 3);
          pointMap.put(3, new Point(currentPoint.getX(), size - 1));
          faceMap.put(4, 1);
          directionMap.put(4, 2);
          pointMap.put(4, new Point(size - 1, size - currentPoint.getX() - 1));
          faceMap.put(5, 3);
          directionMap.put(5, 3);
          pointMap.put(5, new Point(currentPoint.getX(), size - 1));
          faceMap.put(6, 1);
          directionMap.put(6, 1);
          pointMap.put(6, new Point(size - currentPoint.getX() - 1, 0));
          newPoint = pointMap.get(faceIdx);
          newDirection = directionMap.get(faceIdx);
          newFaceIdx = faceMap.get(faceIdx);
        } else if (currentPoint.getY() == (size - 1) && direction == 1) {
          faceMap.put(1, 3);
          directionMap.put(1, 1);
          pointMap.put(1, new Point(currentPoint.getX(), 0));
          faceMap.put(2, 5);
          directionMap.put(2, 0);
          pointMap.put(2, new Point(0, size - currentPoint.getX() - 1));
          faceMap.put(3, 5);
          directionMap.put(3, 1);
          pointMap.put(3, new Point(currentPoint.getX(), 0));
          faceMap.put(4, 5);
          directionMap.put(4, 2);
          pointMap.put(4, new Point(size - 1, currentPoint.getX()));
          faceMap.put(5, 6);
          directionMap.put(5, 3);
          pointMap.put(5, new Point(size - currentPoint.getX() - 1, size - 1));
          faceMap.put(6, 5);
          directionMap.put(6, 3);
          pointMap.put(6, new Point(size - currentPoint.getX() - 1, size - 1));
          newPoint = pointMap.get(faceIdx);
          newDirection = directionMap.get(faceIdx);
          newFaceIdx = faceMap.get(faceIdx);
        } else {
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
          long newX = currentPoint.getX() + xMove;
          long newY = currentPoint.getY() + yMove;
          newPoint = new Point(newX, newY);
        }
        newFace =
            newFaceIdx == 1
                ? top
                : (newFaceIdx == 2
                    ? left
                    : (newFaceIdx == 3
                        ? front
                        : (newFaceIdx == 4 ? right : (newFaceIdx == 5 ? bottom : back))));
        if (newFace.contains(newPoint)) {
          break;
        }
        currentPoint = newPoint;
        direction = newDirection;
        faceIdx = newFaceIdx;
        currentFace = newFace;
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
    direction += directionTransform.get(faceIdx);
    currentPoint = pointTransform.get(faceIdx).apply(currentPoint);
    direction = Math.floorMod(direction, 4);
    //    printPuzzle(points, walls, maxX, maxY);
    return (currentPoint.getY() + 1) * 1000 + (currentPoint.getX() + 1) * 4 + direction;
  }
}
