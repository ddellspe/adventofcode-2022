package net.ddellspe.day23;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import net.ddellspe.utils.InputUtils;
import net.ddellspe.utils.Point;

public class Day23 {
  private Day23() {}

  public static long part1(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day23.class);
    Set<Point> currentPositions = new HashSet<>();
    for (int y = 0; y < lines.size(); y++) {
      String line = lines.get(y);
      for (int x = 0; x < line.length(); x++) {
        if (line.charAt(x) == '#') {
          currentPositions.add(new Point(x, y));
        }
      }
    }
    List<Elf> elves = currentPositions.stream().map(Elf::new).collect(Collectors.toList());
    for (int i = 0; i < 10; i++) {
      //      printPuzzle(currentPositions);
      for (Elf elf : elves) {
        elf.determineMove(currentPositions, i);
      }
      List<Point> projectedPositions =
          elves.stream().map(e -> e.nextPoint).collect(Collectors.toList());
      for (Elf elf : elves) {
        elf.completeMove(projectedPositions);
      }
      currentPositions = elves.stream().map(e -> e.currentPoint).collect(Collectors.toSet());
    }
    //    printPuzzle(currentPositions);
    long xMin = currentPositions.stream().mapToLong(Point::getX).min().getAsLong();
    long xMax = currentPositions.stream().mapToLong(Point::getX).max().getAsLong();
    long yMin = currentPositions.stream().mapToLong(Point::getY).min().getAsLong();
    long yMax = currentPositions.stream().mapToLong(Point::getY).max().getAsLong();
    return (yMax - yMin + 1) * (xMax - xMin + 1) - currentPositions.size();
  }

  //  public static void printPuzzle(Set<Point> currentPositions) {
  //    long xMin = currentPositions.stream().mapToLong(Point::getX).min().getAsLong();
  //    long xMax = currentPositions.stream().mapToLong(Point::getX).max().getAsLong();
  //    long yMin = currentPositions.stream().mapToLong(Point::getY).min().getAsLong();
  //    long yMax = currentPositions.stream().mapToLong(Point::getY).max().getAsLong();
  //    System.out.println("puzzle");
  //    for (long y = yMin; y <= yMax; y++) {
  //      for (long x = xMin; x <= xMax; x++) {
  //        if (currentPositions.contains(new Point(x, y))) {
  //          System.out.print("#");
  //        } else {
  //          System.out.print(".");
  //        }
  //      }
  //      System.out.print("\n");
  //    }
  //  }

  public static long part2(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day23.class);
    Set<Point> currentPositions = new HashSet<>();
    for (int y = 0; y < lines.size(); y++) {
      String line = lines.get(y);
      for (int x = 0; x < line.length(); x++) {
        if (line.charAt(x) == '#') {
          currentPositions.add(new Point(x, y));
        }
      }
    }
    List<Elf> elves = currentPositions.stream().map(Elf::new).collect(Collectors.toList());
    int round = 0;
    while (true) {
      for (Elf elf : elves) {
        elf.determineMove(currentPositions, round);
      }
      List<Point> projectedPositions =
          elves.stream().map(e -> e.nextPoint).collect(Collectors.toList());
      for (Elf elf : elves) {
        elf.completeMove(projectedPositions);
      }
      if (elves.stream()
          .map(e -> e.currentPoint)
          .collect(Collectors.toSet())
          .equals(currentPositions)) {
        break;
      }
      currentPositions = elves.stream().map(e -> e.currentPoint).collect(Collectors.toSet());
      round++;
    }
    return round + 1;
  }

  public static class Elf {
    Point currentPoint;
    Point nextPoint;

    public Elf(Point currentPoint) {
      this.currentPoint = currentPoint;
      this.nextPoint = currentPoint;
    }

    public void determineMove(Set<Point> points, int round) {
      if (currentPoint.getAllNeighbors().stream().noneMatch(points::contains)) {
        nextPoint = currentPoint;
      } else {
        if (round % 4 == 0) {
          if (currentPoint.getNorthNeighbors().stream().noneMatch(points::contains)) {
            nextPoint = new Point(currentPoint.getX(), currentPoint.getY() - 1);
          } else if (currentPoint.getSouthNeighbors().stream().noneMatch(points::contains)) {
            nextPoint = new Point(currentPoint.getX(), currentPoint.getY() + 1);
          } else if (currentPoint.getWestNeighbors().stream().noneMatch(points::contains)) {
            nextPoint = new Point(currentPoint.getX() - 1, currentPoint.getY());
          } else if (currentPoint.getEastNeighbors().stream().noneMatch(points::contains)) {
            nextPoint = new Point(currentPoint.getX() + 1, currentPoint.getY());
          }
        } else if (round % 4 == 1) {
          if (currentPoint.getSouthNeighbors().stream().noneMatch(points::contains)) {
            nextPoint = new Point(currentPoint.getX(), currentPoint.getY() + 1);
          } else if (currentPoint.getWestNeighbors().stream().noneMatch(points::contains)) {
            nextPoint = new Point(currentPoint.getX() - 1, currentPoint.getY());
          } else if (currentPoint.getEastNeighbors().stream().noneMatch(points::contains)) {
            nextPoint = new Point(currentPoint.getX() + 1, currentPoint.getY());
          } else if (currentPoint.getNorthNeighbors().stream().noneMatch(points::contains)) {
            nextPoint = new Point(currentPoint.getX(), currentPoint.getY() - 1);
          }
        } else if (round % 4 == 2) {
          if (currentPoint.getWestNeighbors().stream().noneMatch(points::contains)) {
            nextPoint = new Point(currentPoint.getX() - 1, currentPoint.getY());
          } else if (currentPoint.getEastNeighbors().stream().noneMatch(points::contains)) {
            nextPoint = new Point(currentPoint.getX() + 1, currentPoint.getY());
          } else if (currentPoint.getNorthNeighbors().stream().noneMatch(points::contains)) {
            nextPoint = new Point(currentPoint.getX(), currentPoint.getY() - 1);
          } else if (currentPoint.getSouthNeighbors().stream().noneMatch(points::contains)) {
            nextPoint = new Point(currentPoint.getX(), currentPoint.getY() + 1);
          }
        } else {
          if (currentPoint.getEastNeighbors().stream().noneMatch(points::contains)) {
            nextPoint = new Point(currentPoint.getX() + 1, currentPoint.getY());
          } else if (currentPoint.getNorthNeighbors().stream().noneMatch(points::contains)) {
            nextPoint = new Point(currentPoint.getX(), currentPoint.getY() - 1);
          } else if (currentPoint.getSouthNeighbors().stream().noneMatch(points::contains)) {
            nextPoint = new Point(currentPoint.getX(), currentPoint.getY() + 1);
          } else if (currentPoint.getWestNeighbors().stream().noneMatch(points::contains)) {
            nextPoint = new Point(currentPoint.getX() - 1, currentPoint.getY());
          }
        }
      }
    }

    public void completeMove(List<Point> points) {
      if (points.stream().filter(nextPoint::equals).count() > 1) {
        nextPoint = currentPoint;
      } else {
        currentPoint = nextPoint;
      }
    }
  }
}
