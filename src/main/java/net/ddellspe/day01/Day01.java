package net.ddellspe.day01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import net.ddellspe.utils.InputUtils;

public class Day01 {
  private Day01() {}

  public static long part1(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day01.class);
    long max = 0L;
    long sum = 0L;
    for (String line : lines) {
      if (line.isEmpty()) {
        if (sum > max) {
          max = sum;
        }
        sum = 0L;
        continue;
      }
      sum += Long.parseLong(line);
    }
    if (sum > max) {
      max = sum;
    }
    return max;
  }

  public static long part2(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day01.class);
    List<Long> values = new ArrayList<>();
    long sum = 0L;
    for (String line : lines) {
      if (line.isEmpty()) {
        values.add(sum);
        sum = 0L;
        continue;
      }
      sum += Long.parseLong(line);
    }
    values.add(sum);
    return values.stream()
        .sorted(Comparator.reverseOrder())
        .collect(Collectors.toList())
        .subList(0, 3)
        .stream()
        .reduce(0L, (a, b) -> a + b);
  }
}
