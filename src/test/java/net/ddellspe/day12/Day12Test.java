package net.ddellspe.day12;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Day12Test {
  Day12Test() {}

  @Test
  public void providedInputTestPart1() {
    assertEquals(31L, Day12.part1("example.txt"));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 12 Part 1 Answer is: " + Day12.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertEquals(29L, Day12.part2("example.txt"));
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 12 Part 2 Answer is: " + Day12.part2("input.txt"));
  }
}
