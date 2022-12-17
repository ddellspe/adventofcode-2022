package net.ddellspe.day15;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import net.ddellspe.utils.InputUtils;
import net.ddellspe.utils.Point;

public class Day15 {
  private Day15() {}

  public static <Mao> long part1(String filename, int yLevel) {
    List<String> lines = InputUtils.stringPerLine(filename, Day15.class);
    Map<Point, Point> sensors = new LinkedHashMap<>();
    for (String line : lines) {
      int sensorX = Integer.parseInt(line.substring(line.indexOf("x=") + 2, line.indexOf(", ")));
      int sensorY = Integer.parseInt(line.substring(line.indexOf("y=") + 2, line.indexOf(":")));
      int beaconX =
          Integer.parseInt(
              line.substring(
                  line.indexOf("x=", line.indexOf(":")) + 2,
                  line.indexOf(", ", line.indexOf(":"))));
      int beaconY = Integer.parseInt(line.substring(line.indexOf("y=", line.indexOf(":")) + 2));
      sensors.put(new Point(sensorX, sensorY), new Point(beaconX, beaconY));
    }
    Set<Point> nonBeacons = new HashSet<>();
    for (Entry<Point, Point> entry : sensors.entrySet()) {
      Point sensor = entry.getKey();
      Point beacon = entry.getValue();
      long distance = sensor.getManhattanDistance(beacon);
      if ((sensor.getY() - distance) <= yLevel && (sensor.getY() + distance) >= yLevel) {
        long yDiff = distance - Math.abs(yLevel - sensor.getY());
        for (long x = -yDiff; x <= yDiff; x++) {
          nonBeacons.add(new Point(sensor.getX() + x, yLevel));
        }
      }
    }
    nonBeacons.removeAll(sensors.keySet());
    nonBeacons.removeAll(sensors.values());
    return nonBeacons.stream().count();
  }

  public static long part2(String filename, int maxCoord) {
    List<String> lines = InputUtils.stringPerLine(filename, Day15.class);
    Map<Point, Long> sensors = new LinkedHashMap<>();
    for (String line : lines) {
      long sensorX = Integer.parseInt(line.substring(line.indexOf("x=") + 2, line.indexOf(", ")));
      long sensorY = Integer.parseInt(line.substring(line.indexOf("y=") + 2, line.indexOf(":")));
      long beaconX =
          Integer.parseInt(
              line.substring(
                  line.indexOf("x=", line.indexOf(":")) + 2,
                  line.indexOf(", ", line.indexOf(":"))));
      long beaconY = Integer.parseInt(line.substring(line.indexOf("y=", line.indexOf(":")) + 2));
      Point sensor = new Point(sensorX, sensorY);
      sensors.put(sensor, sensor.getManhattanDistance(new Point(beaconX, beaconY)));
    }
    Point testPoint = null;
    for (Entry<Point, Long> pointDistances : sensors.entrySet()) {
      for (long yOffset = -pointDistances.getValue() - 1;
          yOffset < pointDistances.getValue() + 1;
          yOffset++) {
        long xOffset = pointDistances.getValue() - Math.abs(yOffset) + 1;
        long minX = pointDistances.getKey().getX() - xOffset;
        long maxX = pointDistances.getKey().getX() + xOffset;
        long y = pointDistances.getKey().getY() + yOffset;
        if (minX >= 0
            && minX <= maxCoord
            && y >= 0
            && y <= maxCoord
            && sensors.entrySet().stream()
                .allMatch(
                    e -> e.getKey().getManhattanDistance(new Point(minX, y)) > e.getValue())) {
          testPoint = new Point(minX, y);
        } else if (maxX >= 0
            && maxX <= maxCoord
            && y >= 0
            && y <= maxCoord
            && sensors.entrySet().stream()
                .allMatch(
                    e -> e.getKey().getManhattanDistance(new Point(maxX, y)) > e.getValue())) {
          testPoint = new Point(maxX, y);
        }
        if (testPoint != null) {
          break;
        }
      }
      if (testPoint != null) {
        break;
      }
    }
    if (testPoint != null) {
      return 4_000_000L * testPoint.getX() + testPoint.getY();
    } else {
      return 0L;
    }
  }
}
