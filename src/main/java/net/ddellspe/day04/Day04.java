package net.ddellspe.day04;

import java.util.List;
import net.ddellspe.utils.InputUtils;

public class Day04 {
  private Day04() {}

  public static long part1(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day04.class);
    long count = 0L;
    for (String section : lines) {
      String range1 = section.split(",")[0];
      String range2 = section.split(",")[1];
      int r1low = Integer.parseInt(range1.split("-")[0]);
      int r1high = Integer.parseInt(range1.split("-")[1]);
      int r2low = Integer.parseInt(range2.split("-")[0]);
      int r2high = Integer.parseInt(range2.split("-")[1]);
      if (r1low >= r2low && r1high <= r2high || r2low >= r1low && r2high <= r1high) {
        count += 1L;
      }
    }
    return count;
  }

  public static long part2(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day04.class);
    long count = 0L;
    for (String section : lines) {
      String range1 = section.split(",")[0];
      String range2 = section.split(",")[1];
      int r1low = Integer.parseInt(range1.split("-")[0]);
      int r1high = Integer.parseInt(range1.split("-")[1]);
      int r2low = Integer.parseInt(range2.split("-")[0]);
      int r2high = Integer.parseInt(range2.split("-")[1]);
      if (r2high >= r1low && r2high <= r1high
          || r2low >= r1low && r2low <= r1high
          || r1high >= r2low && r1high <= r2high
          || r1low >= r2low && r1low <= r2high) {
        count += 1L;
      }
    }
    return count;
  }
}
