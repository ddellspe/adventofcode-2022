package net.ddellspe.day18;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import net.ddellspe.utils.InputUtils;
import net.ddellspe.utils.Point3D;

public class Day18 {
  private Day18() {}

  public static long part1(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day18.class);
    Set<Point3D> lavaDroplets = lines.stream().map(Point3D::new).collect(Collectors.toSet());
    return lavaDroplets.stream()
        .mapToLong(
            pt -> (6L - pt.getDirectNeighbors().stream().filter(lavaDroplets::contains).count()))
        .sum();
  }

  public static long part2(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day18.class);
    Set<Point3D> lavaDroplets = lines.stream().map(Point3D::new).collect(Collectors.toSet());
    Set<Point3D> spotsOfInterest = new HashSet<>();
    for (long x = lavaDroplets.stream().mapToLong(Point3D::getX).min().getAsLong() - 1;
        x < lavaDroplets.stream().mapToLong(Point3D::getX).max().getAsLong() + 2;
        x++) {
      for (long y = lavaDroplets.stream().mapToLong(Point3D::getY).min().getAsLong() - 1;
          y < lavaDroplets.stream().mapToLong(Point3D::getY).max().getAsLong() + 2;
          y++) {
        for (long z = lavaDroplets.stream().mapToLong(Point3D::getZ).min().getAsLong() - 1;
            z < lavaDroplets.stream().mapToLong(Point3D::getZ).max().getAsLong() + 2;
            z++) {
          spotsOfInterest.add(new Point3D(x, y, z));
        }
      }
    }
    Set<Point3D> airPockets =
        spotsOfInterest.stream()
            .filter(pt -> !lavaDroplets.contains(pt))
            .collect(Collectors.toSet());
    Point3D start = airPockets.stream().min(Point3D::compareTo).get();
    LinkedList<Point3D> queue = new LinkedList<>();
    queue.add(start);
    while (queue.size() > 0) {
      Point3D pt = queue.poll();
      if (airPockets.contains(pt)) {
        airPockets.remove(pt);
        queue.addAll(pt.getDirectNeighbors());
      }
    }
    return lavaDroplets.stream()
            .mapToLong(
                pt ->
                    (6L - pt.getDirectNeighbors().stream().filter(lavaDroplets::contains).count()))
            .sum()
        - airPockets.stream()
            .mapToLong(
                pt -> (6L - pt.getDirectNeighbors().stream().filter(airPockets::contains).count()))
            .sum();
  }
}
