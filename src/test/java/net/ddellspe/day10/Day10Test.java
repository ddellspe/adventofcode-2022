package net.ddellspe.day10;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class Day10Test {
  Day10Test() {}

  @Test
  public void providedInputTestPart1() {
    assertEquals(13140L, Day10.part1("example.txt"));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 10 Part 1 Answer is: " + Day10.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    String expected =
        "##..##..##..##..##..##..##..##..##..##.."
            + "###...###...###...###...###...###...###."
            + "####....####....####....####....####...."
            + "#####.....#####.....#####.....#####....."
            + "######......######......######......####"
            + "#######.......#######.......#######.....";
    List<String> expectedList =
        expected.chars().mapToObj(c -> String.valueOf((char) c)).collect(Collectors.toList());
    assertEquals(expectedList, Day10.part2("example.txt"));
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 10 Part 2 Answer is: ");
    List<String> pixels = Day10.part2("input.txt");
    for (int y = 0; y < 6; y++) {
      for (int x = 0; x < 40; x++) {
        System.out.print(pixels.get(y * 40 + x));
      }
      System.out.print("\n");
    }
  }
}
