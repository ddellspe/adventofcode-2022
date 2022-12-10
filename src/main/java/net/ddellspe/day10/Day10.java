package net.ddellspe.day10;

import java.util.ArrayList;
import java.util.List;
import net.ddellspe.utils.InputUtils;

public class Day10 {

  private Day10() {}

  public static long part1(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day10.class);
    long X = 1;
    long strength = 0L;
    int lineIndex = -1;
    int cyclesToAdd = 0;
    String line = null;
    for (int cycle = 1; cycle <= 220; cycle++) {
      if (cyclesToAdd == 0) {
        lineIndex++;
        line = lines.get(lineIndex);
      }
      if ("noop".equals(line)) {
        cyclesToAdd = 1;
      } else if (line.startsWith("addx") && cyclesToAdd == 0) {
        cyclesToAdd = 2;
      }
      if ((cycle - 20) % 40 == 0) {
        strength += (cycle * X);
      }
      cyclesToAdd--;
      if (cyclesToAdd == 0) {
        if (line.startsWith("addx")) {
          X += Long.parseLong(line.split(" ")[1]);
        }
      }
    }
    return strength;
  }

  public static List<String> part2(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day10.class);
    List<String> pixels = new ArrayList<>(240);
    for (int i = 0; i < 240; i++) {
      pixels.add(".");
    }
    long spriteStartPosition = 0;
    int lineIndex = -1;
    int cyclesToAdd = 0;
    String line = null;
    for (int cycle = 0; cycle < 240; cycle++) {
      if (cyclesToAdd == 0) {
        lineIndex++;
        line = lines.get(lineIndex);
      }
      if ("noop".equals(line)) {
        cyclesToAdd = 1;
      } else if (line.startsWith("addx") && cyclesToAdd == 0) {
        cyclesToAdd = 2;
      }
      if ((cycle % 40) < spriteStartPosition || (cycle % 40) >= (spriteStartPosition + 3)) {
        pixels.set(cycle, ".");
      } else {
        pixels.set(cycle, "#");
      }
      cyclesToAdd--;
      if (cyclesToAdd == 0) {
        if (line.startsWith("addx")) {
          spriteStartPosition += Long.parseLong(line.split(" ")[1]);
        }
      }
    }
    return pixels;
  }
}
