package net.ddellspe.day03;

import java.util.List;
import net.ddellspe.utils.InputUtils;

public class Day03 {
  private Day03() {}

  public static long part1(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day03.class);
    long total = 0L;
    for (String sack : lines) {
      String comp1 = sack.substring(0, sack.length() / 2);
      String comp2 = sack.substring(sack.length() / 2);
      char item =
          (char)
              comp1
                  .chars()
                  .filter(c -> comp2.contains(String.valueOf((char) c)))
                  .findFirst()
                  .getAsInt();
      if (item <= 'Z' && item >= 'A') {
        total += (item - 'A' + 27);
      } else {
        total += (item - 'a' + 1);
      }
    }
    return total;
  }

  public static long part2(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day03.class);
    long total = 0L;
    for (int i = 0; i < lines.size(); i += 3) {
      String comp1 = lines.get(i);
      String comp2 = lines.get(i + 1);
      String comp3 = lines.get(i + 2);
      char item =
          (char)
              comp1
                  .chars()
                  .filter(c -> comp2.contains(String.valueOf((char) c)))
                  .filter(c -> comp3.contains(String.valueOf((char) c)))
                  .findFirst()
                  .getAsInt();
      if (item <= 'Z' && item >= 'A') {
        total += (item - 'A' + 27);
      } else {
        total += (item - 'a' + 1);
      }
    }
    return total;
  }
}
