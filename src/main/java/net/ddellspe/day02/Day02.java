package net.ddellspe.day02;

import java.util.List;
import net.ddellspe.utils.InputUtils;

public class Day02 {
  private Day02() {}

  public static long part1(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day02.class);
    long totalScore = 0L;
    for (String game : lines) {
      long score = 0L;
      String opponent = game.split(" ")[0];
      String me = game.split(" ")[1];
      switch (opponent) {
        case "A":
          switch (me) {
            case "X":
              score += 4;
              break;
            case "Y":
              score += 8;
              break;
            case "Z":
              score += 3;
              break;
            default:
              throw new IllegalStateException(String.format("Invalid choice: %s", me));
          }
          break;
        case "B":
          switch (me) {
            case "X":
              score += 1;
              break;
            case "Y":
              score += 5;
              break;
            case "Z":
              score += 9;
              break;
            default:
              throw new IllegalStateException(String.format("Invalid choice: %s", me));
          }
          break;
        case "C":
          switch (me) {
            case "X":
              score += 7;
              break;
            case "Y":
              score += 2;
              break;
            case "Z":
              score += 6;
              break;
            default:
              throw new IllegalStateException(String.format("Invalid choice: %s", me));
          }
          break;
      }
      totalScore += score;
    }
    return totalScore;
  }

  public static long part2(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day02.class);
    long totalScore = 0L;
    for (String game : lines) {
      long score = 0L;
      String opponent = game.split(" ")[0];
      String me = game.split(" ")[1];
      switch (opponent) {
        case "A":
          switch (me) {
            case "Y":
              score += 4;
              break;
            case "Z":
              score += 8;
              break;
            case "X":
              score += 3;
              break;
            default:
              throw new IllegalStateException(String.format("Invalid choice: %s", me));
          }
          break;
        case "B":
          switch (me) {
            case "X":
              score += 1;
              break;
            case "Y":
              score += 5;
              break;
            case "Z":
              score += 9;
              break;
            default:
              throw new IllegalStateException(String.format("Invalid choice: %s", me));
          }
          break;
        case "C":
          switch (me) {
            case "Z":
              score += 7;
              break;
            case "X":
              score += 2;
              break;
            case "Y":
              score += 6;
              break;
            default:
              throw new IllegalStateException(String.format("Invalid choice: %s", me));
          }
          break;
      }
      totalScore += score;
    }
    return totalScore;
  }
}
