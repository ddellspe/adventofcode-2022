package net.ddellspe.day25;

import java.util.List;
import net.ddellspe.utils.InputUtils;

public class Day25 {
  private Day25() {}

  public static String part1(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day25.class);
    long sum = 0L;
    for (String line : lines) {
      long value = 0L;
      long mult = 1L;
      for (int i = 0; i < line.length(); i++) {
        char digit = line.charAt(line.length() - i - 1);
        switch (digit) {
          case '2':
            value += (2L * mult);
            break;
          case '1':
            value += mult;
            break;
          case '-':
            value -= mult;
            break;
          case '=':
            value -= (2L * mult);
            break;
        }
        mult *= 5;
      }
      sum += value;
    }
    System.out.println(sum);
    StringBuilder number = new StringBuilder();
    while (sum > 0) {
      long remainder = sum % 5;
      if (remainder <= 2) {
        number.insert(0, remainder);
      } else {
        number.insert(0, remainder == 3 ? "=" : "-");
      }
      sum /= 5;
      sum += (remainder / 3);
    }
    return number.toString();
  }
}
