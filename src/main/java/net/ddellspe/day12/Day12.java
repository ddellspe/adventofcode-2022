package net.ddellspe.day12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import net.ddellspe.utils.InputUtils;
import net.ddellspe.utils.Point;

public class Day12 {
  private Day12() {}

  public static long part1(String filename) {
    Map<Point, String> points = InputUtils.pointStringMap(filename, Day12.class);
    Point startingPoint =
        points.entrySet().stream()
            .filter(v -> v.getValue().equals("S"))
            .map(Entry::getKey)
            .findFirst()
            .get();
    Point endingPoint =
        points.entrySet().stream()
            .filter(v -> v.getValue().equals("E"))
            .map(Entry::getKey)
            .findFirst()
            .get();
    Map<Point, Integer> pointsInt =
        points.entrySet().stream()
            .collect(
                Collectors.toMap(
                    Entry::getKey,
                    e ->
                        e.getValue().equals("S")
                            ? 1
                            : (e.getValue().equals("E")
                                ? 26
                                : (e.getValue().charAt(0) - 'a' + 1))));
    return calculateShortestPath(startingPoint, endingPoint, pointsInt);
  }

  public static long part2(String filename) {
    Map<Point, String> points = InputUtils.pointStringMap(filename, Day12.class);
    Point endingPoint =
        points.entrySet().stream()
            .filter(v -> v.getValue().equals("E"))
            .map(Entry::getKey)
            .findFirst()
            .get();
    Map<Point, Integer> pointsInt =
        points.entrySet().stream()
            .collect(
                Collectors.toMap(
                    Entry::getKey,
                    e ->
                        e.getValue().equals("S")
                            ? 1
                            : (e.getValue().equals("E")
                                ? 26
                                : (e.getValue().charAt(0) - 'a' + 1))));
    long shortest = Long.MAX_VALUE;
    for (Point pt :
        pointsInt.entrySet().stream()
            .filter(e -> e.getValue() == 1)
            .map(Entry::getKey)
            .collect(Collectors.toList())) {
      shortest = Math.min(shortest, calculateShortestPath(pt, endingPoint, pointsInt));
    }
    return shortest;
  }

  public static long calculateShortestPath(Point start, Point end, Map<Point, Integer> pointsInt) {
    Map<Point, Integer> costs = new HashMap<>();
    Map<Point, Point> parent = new HashMap<>();
    LinkedList<Point> queue = new LinkedList<>();
    costs.put(start, 0);
    queue.add(start);
    while (queue.size() > 0) {
      Point currentPoint = queue.poll();
      if (currentPoint.equals(end)) {
        List<Point> path = new ArrayList<>();
        while (parent.containsKey(currentPoint)) {
          path.add(currentPoint);
          currentPoint = parent.get(currentPoint);
        }
        return path.size();
      }
      for (Point candidateMove : currentPoint.getDirectNeighbors()) {
        if (!pointsInt.containsKey(candidateMove)
            || pointsInt.get(candidateMove) > pointsInt.get(currentPoint) + 1) {
          continue;
        }
        int tentativeCost = costs.get(currentPoint) + 1;
        if (tentativeCost < costs.getOrDefault(candidateMove, Integer.MAX_VALUE)) {
          costs.put(candidateMove, tentativeCost);
          parent.put(candidateMove, currentPoint);
          queue.add(candidateMove);
        }
      }
    }
    return Long.MAX_VALUE;
  }
}
