package net.ddellspe.day06;

import java.util.List;
import net.ddellspe.utils.InputUtils;

public class Day06 {
  private Day06() {}

  public static long part1(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day06.class);
    String data = lines.get(0);
    long firstValid = 4L;
    for (int i = 4; i < data.length(); i++) {
      String subst = data.substring(i - 4, i);
      if (subst.chars().distinct().count() == 4) {
        firstValid = i;
        break;
      }
    }
    return firstValid;
  }

  public static long part2(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day06.class);
    String data = lines.get(0);
    long firstValid = 14L;
    for (int i = 14; i < data.length(); i++) {
      String subst = data.substring(i - 14, i);
      if (subst.chars().distinct().count() == 14) {
        firstValid = i;
        break;
      }
    }
    return firstValid;
  }
}
