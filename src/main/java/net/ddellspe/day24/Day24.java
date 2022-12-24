package net.ddellspe.day24;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import net.ddellspe.utils.InputUtils;
import net.ddellspe.utils.Point;

public class Day24 {
  private Day24() {}

  @FunctionalInterface
  public interface BlizzardMovementInterface<T, U, R> {
    public R apply(T t, U u);
  }

  public static long part1(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day24.class);
    Point startPoint =
        new Point(
            LongStream.range(0, lines.get(0).length())
                    .filter(idx -> lines.get(0).charAt((int) idx) == '.')
                    .findFirst()
                    .getAsLong()
                - 1,
            -1);
    Point endPoint =
        new Point(
            LongStream.range(0, lines.get(lines.size() - 1).length())
                    .filter(idx -> lines.get(lines.size() - 1).charAt((int) idx) == '.')
                    .findFirst()
                    .getAsLong()
                - 1,
            lines.size() - 2);
    Set<Point> upBlizzards = new HashSet<>();
    Set<Point> downBlizzards = new HashSet<>();
    Set<Point> leftBlizzards = new HashSet<>();
    Set<Point> rightBlizzards = new HashSet<>();
    Set<Point> validPositions = new HashSet<>();
    BlizzardMovementInterface<Point, Long, Point> moveUpBlizzard =
        (Point p, Long step) ->
            new Point(p.getX(), Math.floorMod(p.getY() - step, endPoint.getY()));
    BlizzardMovementInterface<Point, Long, Point> moveDownBlizzard =
        (Point p, Long step) ->
            new Point(p.getX(), Math.floorMod(p.getY() + step, endPoint.getY()));
    BlizzardMovementInterface<Point, Long, Point> moveLeftBlizzard =
        (Point p, Long step) ->
            new Point(Math.floorMod(p.getX() - step, endPoint.getX() + 1), p.getY());
    BlizzardMovementInterface<Point, Long, Point> moveRightBlizzard =
        (Point p, Long step) ->
            new Point(Math.floorMod(p.getX() + step, endPoint.getX() + 1), p.getY());
    for (long y = 1; y < lines.size() - 1; y++) {
      String line = lines.get((int) y);
      for (long x = 1; x < line.length() - 1; x++) {
        if (line.charAt((int) x) == '^') {
          upBlizzards.add(new Point(x - 1, y - 1));
        } else if (line.charAt((int) x) == 'v') {
          downBlizzards.add(new Point(x - 1, y - 1));
        } else if (line.charAt((int) x) == '<') {
          leftBlizzards.add(new Point(x - 1, y - 1));
        } else if (line.charAt((int) x) == '>') {
          rightBlizzards.add(new Point(x - 1, y - 1));
        }
        validPositions.add(new Point(x - 1, y - 1));
      }
    }
    validPositions.add(endPoint);
    return completeMove(
        startPoint,
        endPoint,
        0,
        validPositions,
        upBlizzards,
        downBlizzards,
        leftBlizzards,
        rightBlizzards,
        moveUpBlizzard,
        moveDownBlizzard,
        moveLeftBlizzard,
        moveRightBlizzard);
  }

  public static long part2(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day24.class);
    Point startPoint =
        new Point(
            LongStream.range(0, lines.get(0).length())
                    .filter(idx -> lines.get(0).charAt((int) idx) == '.')
                    .findFirst()
                    .getAsLong()
                - 1,
            -1);
    Point endPoint =
        new Point(
            LongStream.range(0, lines.get(lines.size() - 1).length())
                    .filter(idx -> lines.get(lines.size() - 1).charAt((int) idx) == '.')
                    .findFirst()
                    .getAsLong()
                - 1,
            lines.size() - 2);
    Set<Point> upBlizzards = new HashSet<>();
    Set<Point> downBlizzards = new HashSet<>();
    Set<Point> leftBlizzards = new HashSet<>();
    Set<Point> rightBlizzards = new HashSet<>();
    Set<Point> validPositions = new HashSet<>();
    BlizzardMovementInterface<Point, Long, Point> moveUpBlizzard =
        (Point p, Long step) ->
            new Point(p.getX(), Math.floorMod(p.getY() - step, endPoint.getY()));
    BlizzardMovementInterface<Point, Long, Point> moveDownBlizzard =
        (Point p, Long step) ->
            new Point(p.getX(), Math.floorMod(p.getY() + step, endPoint.getY()));
    BlizzardMovementInterface<Point, Long, Point> moveLeftBlizzard =
        (Point p, Long step) ->
            new Point(Math.floorMod(p.getX() - step, endPoint.getX() + 1), p.getY());
    BlizzardMovementInterface<Point, Long, Point> moveRightBlizzard =
        (Point p, Long step) ->
            new Point(Math.floorMod(p.getX() + step, endPoint.getX() + 1), p.getY());
    for (long y = 1; y < lines.size() - 1; y++) {
      String line = lines.get((int) y);
      for (long x = 1; x < line.length() - 1; x++) {
        if (line.charAt((int) x) == '^') {
          upBlizzards.add(new Point(x - 1, y - 1));
        } else if (line.charAt((int) x) == 'v') {
          downBlizzards.add(new Point(x - 1, y - 1));
        } else if (line.charAt((int) x) == '<') {
          leftBlizzards.add(new Point(x - 1, y - 1));
        } else if (line.charAt((int) x) == '>') {
          rightBlizzards.add(new Point(x - 1, y - 1));
        }
        validPositions.add(new Point(x - 1, y - 1));
      }
    }
    validPositions.add(endPoint);
    validPositions.add(startPoint);
    return completeMove(
        startPoint,
        endPoint,
        completeMove(
            endPoint,
            startPoint,
            completeMove(
                startPoint,
                endPoint,
                0,
                validPositions,
                upBlizzards,
                downBlizzards,
                leftBlizzards,
                rightBlizzards,
                moveUpBlizzard,
                moveDownBlizzard,
                moveLeftBlizzard,
                moveRightBlizzard),
            validPositions,
            upBlizzards,
            downBlizzards,
            leftBlizzards,
            rightBlizzards,
            moveUpBlizzard,
            moveDownBlizzard,
            moveLeftBlizzard,
            moveRightBlizzard),
        validPositions,
        upBlizzards,
        downBlizzards,
        leftBlizzards,
        rightBlizzards,
        moveUpBlizzard,
        moveDownBlizzard,
        moveLeftBlizzard,
        moveRightBlizzard);
  }

  public static long completeMove(
      Point startPoint,
      Point endPoint,
      long initialStep,
      Set<Point> validPositions,
      Set<Point> upBlizzards,
      Set<Point> downBlizzards,
      Set<Point> leftBlizzards,
      Set<Point> rightBlizzards,
      BlizzardMovementInterface<Point, Long, Point> moveUpBlizzard,
      BlizzardMovementInterface<Point, Long, Point> moveDownBlizzard,
      BlizzardMovementInterface<Point, Long, Point> moveLeftBlizzard,
      BlizzardMovementInterface<Point, Long, Point> moveRightBlizzard) {
    LinkedList<State> queue = new LinkedList<>();
    queue.add(new State(startPoint, initialStep));
    Set<State> seen = new HashSet<>();
    long best = Long.MAX_VALUE;
    while (queue.size() > 0) {
      State currentState = queue.poll();
      if (!seen.add(currentState)) {
        continue;
      }
      if (currentState.point.equals(endPoint) && currentState.stepNumber < best) {
        best = currentState.stepNumber;
        for (int i = queue.size() - 1; i >= 0; i--) {
          if (queue.get(i).stepNumber > best) {
            queue.remove(i);
          }
        }
        continue;
      }
      if (currentState.stepNumber > best) {
        continue;
      }
      Set<Point> pointsToCheck =
          currentState.point.getDirectNeighbors().stream()
              .filter(validPositions::contains)
              .collect(Collectors.toSet());
      pointsToCheck.add(currentState.point);
      for (Point pt : pointsToCheck) {
        if (upBlizzards.stream()
                .map(p -> moveUpBlizzard.apply(p, currentState.stepNumber + 1))
                .noneMatch(p -> p.equals(pt))
            && downBlizzards.stream()
                .map(p -> moveDownBlizzard.apply(p, currentState.stepNumber + 1))
                .noneMatch(p -> p.equals(pt))
            && leftBlizzards.stream()
                .map(p -> moveLeftBlizzard.apply(p, currentState.stepNumber + 1))
                .noneMatch(p -> p.equals(pt))
            && rightBlizzards.stream()
                .map(p -> moveRightBlizzard.apply(p, currentState.stepNumber + 1))
                .noneMatch(p -> p.equals(pt))) {
          queue.add(new State(pt, currentState.stepNumber + 1));
        }
      }
    }
    return best;
  }

  public static class State {
    Point point;
    long stepNumber;

    public State(Point point, long stepNumber) {
      this.point = point;
      this.stepNumber = stepNumber;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      State state = (State) o;
      return stepNumber == state.stepNumber && Objects.equals(point, state.point);
    }

    @Override
    public int hashCode() {
      return Objects.hash(point, stepNumber);
    }
  }
}
