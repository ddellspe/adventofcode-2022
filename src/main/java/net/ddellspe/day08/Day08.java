package net.ddellspe.day08;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.ddellspe.utils.InputUtils;
import net.ddellspe.utils.Point;

public class Day08 {
  private Day08() {}

  public static long part1(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day08.class);
    Map<Point, Integer> trees = new HashMap<>();
    for (int y = 0; y < lines.size(); y++) {
      for (int x = 0; x < lines.get(y).length(); x++) {
        trees.put(new Point(x, y), Integer.parseInt(String.valueOf(lines.get(y).charAt(x))));
      }
    }
    Set<Point> visible = new HashSet<>();
    for (int y = 0; y < lines.size(); y++) {
      for (int x = 0; x < lines.get(y).length(); x++) {
        if (x == 0 || y == 0 || x == lines.get(y).length() - 1 || y == lines.size() - 1) {
          visible.add(new Point(x, y));
          continue;
        }
        int value = trees.get(new Point(x, y));
        boolean highest = true;
        for (int y2 = 0; y2 < y; y2++) {
          if (trees.get(new Point(x, y2)) >= value) {
            highest = false;
            break;
          }
        }
        if (highest) {
          visible.add(new Point(x, y));
        }
        highest = true;
        for (int y2 = y + 1; y2 < lines.size(); y2++) {
          if (trees.get(new Point(x, y2)) >= value) {
            highest = false;
            break;
          }
        }
        if (highest) {
          visible.add(new Point(x, y));
        }
        highest = true;
        for (int x2 = 0; x2 < x; x2++) {
          if (trees.get(new Point(x2, y)) >= value) {
            highest = false;
            break;
          }
        }
        if (highest) {
          visible.add(new Point(x, y));
        }
        highest = true;
        for (int x2 = x + 1; x2 < lines.get(y).length(); x2++) {
          if (trees.get(new Point(x2, y)) >= value) {
            highest = false;
            break;
          }
        }
        if (highest) {
          visible.add(new Point(x, y));
        }
      }
    }
    return visible.size();
  }

  public static long part2(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day08.class);
    Map<Point, Integer> trees = new HashMap<>();
    for (int y = 0; y < lines.size(); y++) {
      for (int x = 0; x < lines.get(y).length(); x++) {
        trees.put(new Point(x, y), Integer.parseInt(String.valueOf(lines.get(y).charAt(x))));
      }
    }
    Map<Point, Long> scenicScores = new LinkedHashMap<>();
    for (int y = 0; y < lines.size(); y++) {
      for (int x = 0; x < lines.get(y).length(); x++) {
        Point pt = new Point(x, y);
        if (x == 0 || y == 0 || x == lines.get(y).length() - 1 || y == lines.size() - 1) {
          scenicScores.put(pt, 0L);
          continue;
        }
        int value = trees.get(pt);
        long score = 1L;
        boolean found = false;
        for (int y2 = y - 1; y2 >= 0; y2--) {
          if (trees.get(new Point(x, y2)) >= value) {
            score *= (y - y2);
            found = true;
            break;
          }
        }
        if (!found) {
          score *= y;
        }
        found = false;
        for (int y2 = y + 1; y2 < lines.size(); y2++) {
          if (trees.get(new Point(x, y2)) >= value) {
            score *= (y2 - y);
            found = true;
            break;
          }
        }
        if (!found) {
          score *= (lines.size() - y - 1);
        }
        found = false;
        for (int x2 = x - 1; x2 >= 0; x2--) {
          if (trees.get(new Point(x2, y)) >= value) {
            score *= (x - x2);
            found = true;
            break;
          }
        }
        if (!found) {
          score *= x;
        }
        found = false;
        for (int x2 = x + 1; x2 < lines.get(y).length(); x2++) {
          if (trees.get(new Point(x2, y)) >= value) {
            score *= (x2 - x);
            found = true;
            break;
          }
        }
        if (!found) {
          score *= (lines.get(y).length() - x - 1);
        }
        scenicScores.put(pt, score);
      }
    }
    return scenicScores.values().stream().max(Comparator.naturalOrder()).get();
  }
}
