package net.ddellspe.day14;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class Day14Test {
  Day14Test() {}

  @Test
  public void providedInputTestPart1() {
    assertEquals(24L, Day14.part1("example.txt"));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 14 Part 1 Answer is: " + Day14.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertEquals(93L, Day14.part2("example.txt"));
  }

  @Test
  @Disabled("Test runs for too long")
  public void solutionPart2() {
    System.out.println("Day 14 Part 2 Answer is: " + Day14.part2("input.txt"));
  }
}
