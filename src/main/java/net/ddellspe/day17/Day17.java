package net.ddellspe.day17;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import net.ddellspe.utils.InputUtils;
import net.ddellspe.utils.Point;

public class Day17 {
  private Day17() {}

  public static List<Set<Point>> PIECES =
      List.of(
          Set.of(new Point(2, 0), new Point(3, 0), new Point(4, 0), new Point(5, 0)),
          Set.of(
              new Point(2, 1), new Point(3, 2), new Point(3, 1), new Point(3, 0), new Point(4, 1)),
          Set.of(
              new Point(2, 0), new Point(3, 0), new Point(4, 0), new Point(4, 1), new Point(4, 2)),
          Set.of(new Point(2, 0), new Point(2, 1), new Point(2, 2), new Point(2, 3)),
          Set.of(new Point(2, 0), new Point(3, 0), new Point(2, 1), new Point(3, 1)));

  public static long part1(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day17.class);
    String movements = lines.get(0);
    Set<Point> rocks = new HashSet<>();
    int index = 0;
    for (int shape = 0; shape < 2022; shape++) {
      long maxY = rocks.stream().mapToLong(Point::getY).max().orElseGet(() -> 0L);
      Set<Point> piece = new HashSet<>(PIECES.get(shape % 5));
      piece = moveShape(piece, 0, maxY + 4, rocks, true);
      while (true) {
        if (movements.charAt(index) == '<') {
          piece = moveShape(piece, -1, 0, rocks);
        } else {
          piece = moveShape(piece, 1, 0, rocks);
        }
        index++;
        index %= movements.length();
        if (piece.stream().mapToLong(Point::getY).min().getAsLong() == 1L
            || piece.stream()
                .map(p -> new Point(p.getX(), p.getY() - 1))
                .anyMatch(rocks::contains)) {
          break;
        }
        piece = moveShape(piece, 0, -1, rocks);
      }
      rocks.addAll(piece);
    }

    return rocks.stream().mapToLong(Point::getY).max().getAsLong();
  }

  public static Set<Point> moveShape(Set<Point> shape, long x, long y, Set<Point> rocks) {
    return moveShape(shape, x, y, rocks, false);
  }

  public static Set<Point> moveShape(
      Set<Point> shape, long x, long y, Set<Point> rocks, boolean force) {
    long minX = shape.stream().mapToLong(Point::getX).min().getAsLong();
    long maxX = shape.stream().mapToLong(Point::getX).max().getAsLong();
    if (force) {
      return shape.stream()
          .map(v -> new Point(v.getX() + x, v.getY() + y))
          .collect(Collectors.toSet());
    }
    if (x == -1 && minX == 0 || x == 1 && maxX == 6) {
      return shape;
    } else if (shape.stream()
        .map(p -> new Point(p.getX() + x, p.getY() + y))
        .noneMatch(rocks::contains)) {
      return shape.stream()
          .map(v -> new Point(v.getX() + x, v.getY() + y))
          .collect(Collectors.toSet());
    }
    return shape;
  }

  public static long part2(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day17.class);
    String movements = lines.get(0);
    Set<Point> rocks = new HashSet<>();
    long target = 1000000000000L;
    Map<State, Point> states = new HashMap<>();
    long top = 0L;
    int index = 0;
    long shape = 0L;
    long additional = 0L;
    while (shape < target) {
      long maxY = rocks.stream().mapToLong(Point::getY).max().orElseGet(() -> 0);
      Set<Point> piece = new HashSet<>(PIECES.get((int) (shape % 5)));
      piece = moveShape(piece, 0, maxY + 4, rocks, true);
      while (true) {
        if (movements.charAt(index) == '<') {
          piece = moveShape(piece, -1, 0, rocks);
        } else {
          piece = moveShape(piece, 1, 0, rocks);
        }
        index++;
        index %= movements.length();
        if (piece.stream().mapToLong(Point::getY).min().getAsLong() == 1
            || piece.stream()
                .map(p -> new Point(p.getX(), p.getY() - 1))
                .anyMatch(rocks::contains)) {
          break;
        }
        piece = moveShape(piece, 0, -1, rocks);
      }
      rocks.addAll(piece);
      top = rocks.stream().mapToLong(Point::getY).max().getAsLong();
      State state = new State(index, (int) shape % 5, rocks);
      if (states.containsKey(state)) {
        Point previousState = states.get(state);
        long yChange = top - previousState.getY();
        long shapeChange = shape - previousState.getX();
        long scale = (target - shape) / shapeChange;
        additional += scale * yChange;
        shape += scale * shapeChange;
      }
      states.put(state, new Point(shape, top));
      shape++;
    }

    return rocks.stream().mapToLong(Point::getY).max().getAsLong() + additional;
  }

  //  public static void printPuzzle(Set<Point> rocks, Set<Point> piece) {
  //    long maxY = rocks.stream().mapToLong(Point::getY).max().orElseGet(() -> 0);
  //    if (piece != null) {
  //      maxY = Math.max(piece.stream().mapToLong(Point::getY).max().orElseGet(() -> 0), maxY);
  //    }
  //    for (long y = maxY; y >= 0; y--) {
  //      System.out.print("|");
  //      for (int x = 0; x < 7; x++) {
  //        Point pt = new Point(x, y);
  //        if (rocks.contains(pt)) {
  //          System.out.print("#");
  //        } else if (piece != null && piece.contains(pt)) {
  //          System.out.print("@");
  //        } else {
  //          System.out.print(".");
  //        }
  //      }
  //      System.out.print("|\n");
  //    }
  //    System.out.println("+-------+\n");
  //  }

  public static class State {
    int index;
    int piece;
    Set<Point> rockSignature;

    public State(int index, int piece, Set<Point> rocks) {
      this.index = index;
      this.piece = piece;
      long maxY = rocks.stream().mapToLong(Point::getY).max().getAsLong();
      this.rockSignature =
          rocks.stream()
              .filter(p -> maxY - p.getY() <= 100) // assume we need a cycle of 100 rows
              .map(p -> new Point(p.getX(), maxY - p.getY()))
              .collect(Collectors.toSet());
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
      return index == state.index
          && piece == state.piece
          && Objects.equals(rockSignature, state.rockSignature);
    }

    @Override
    public int hashCode() {
      return Objects.hash(index, piece, rockSignature);
    }
  }
}
