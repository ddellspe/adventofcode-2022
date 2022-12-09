package net.ddellspe.day09;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.ddellspe.utils.InputUtils;
import net.ddellspe.utils.Point;

public class Day09 {
  private Day09() {}

  public static long part1(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day09.class);
    Set<Point> pointSet = new HashSet<>();
    Point head = new Point(0, 0);
    Point tail = new Point(0, 0);
    for (String line : lines) {
      String direction = line.split(" ")[0];
      int distance = Integer.parseInt(line.split(" ")[1]);
      for (int i = 0; i < distance; i++) {
        switch (direction) {
          case "U":
            head = new Point(head.getX(), head.getY() - 1);
            break;
          case "D":
            head = new Point(head.getX(), head.getY() + 1);
            break;
          case "L":
            head = new Point(head.getX() - 1, head.getY());
            break;
          case "R":
            head = new Point(head.getX() + 1, head.getY());
            break;
        }
        if (!touching(head, tail)) {
          if (head.getX() > tail.getX()) {
            if (head.getY() > tail.getY()) {
              tail = new Point(tail.getX() + 1, tail.getY() + 1);
            } else if (head.getY() < tail.getY()) {
              tail = new Point(tail.getX() + 1, tail.getY() - 1);
            } else {
              tail = new Point(tail.getX() + 1, tail.getY());
            }
          } else if (head.getX() < tail.getX()) {
            if (head.getY() > tail.getY()) {
              tail = new Point(tail.getX() - 1, tail.getY() + 1);
            } else if (head.getY() < tail.getY()) {
              tail = new Point(tail.getX() - 1, tail.getY() - 1);
            } else {
              tail = new Point(tail.getX() - 1, tail.getY());
            }
          } else {
            if (head.getY() > tail.getY()) {
              tail = new Point(tail.getX(), tail.getY() + 1);
            } else {
              tail = new Point(tail.getX(), tail.getY() - 1);
            }
          }
        }
        pointSet.add(tail);
      }
    }
    return pointSet.size();
  }

  public static boolean touching(Point head, Point tail) {
    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        if (new Point(tail.getX() + i, tail.getY() + j).equals(head)) {
          return true;
        }
      }
    }
    return false;
  }

  public static long part2(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day09.class);
    Set<Point> pointSet = new HashSet<>();
    List<Point> points = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      points.add(new Point(0, 0));
    }
    for (String line : lines) {
      String direction = line.split(" ")[0];
      int distance = Integer.parseInt(line.split(" ")[1]);
      for (int i = 0; i < distance; i++) {
        switch (direction) {
          case "U":
            points.set(0, new Point(points.get(0).getX(), points.get(0).getY() - 1));
            break;
          case "D":
            points.set(0, new Point(points.get(0).getX(), points.get(0).getY() + 1));
            break;
          case "L":
            points.set(0, new Point(points.get(0).getX() - 1, points.get(0).getY()));
            break;
          case "R":
            points.set(0, new Point(points.get(0).getX() + 1, points.get(0).getY()));
            break;
        }
        for (int j = 1; j < 10; j++) {
          Point prev = points.get(j - 1);
          Point curr = points.get(j);
          if (!touching(prev, curr)) {
            if (prev.getX() > curr.getX()) {
              if (prev.getY() > curr.getY()) {
                curr = new Point(curr.getX() + 1, curr.getY() + 1);
              } else if (prev.getY() < curr.getY()) {
                curr = new Point(curr.getX() + 1, curr.getY() - 1);
              } else {
                curr = new Point(curr.getX() + 1, curr.getY());
              }
            } else if (prev.getX() < curr.getX()) {
              if (prev.getY() > curr.getY()) {
                curr = new Point(curr.getX() - 1, curr.getY() + 1);
              } else if (prev.getY() < curr.getY()) {
                curr = new Point(curr.getX() - 1, curr.getY() - 1);
              } else {
                curr = new Point(curr.getX() - 1, curr.getY());
              }
            } else {
              if (prev.getY() > curr.getY()) {
                curr = new Point(curr.getX(), curr.getY() + 1);
              } else {
                curr = new Point(curr.getX(), curr.getY() - 1);
              }
            }
          }
          points.set(j, curr);
        }
        pointSet.add(points.get(9));
      }
    }
    return pointSet.size();
  }
}
