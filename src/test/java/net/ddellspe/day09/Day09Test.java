package net.ddellspe.day09;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Day09Test {
  Day09Test() {}

  @Test
  public void providedInputTestPart1() {
    assertEquals(13L, Day09.part1("example.txt"));
  }

  @Test
  public void providedInputTestPart1Coverage() {
    assertEquals(88L, Day09.part1("example3.txt"));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 09 Part 1 Answer is: " + Day09.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertEquals(36L, Day09.part2("example2.txt"));
  }

  @Test
  public void providedInputTestPart2Coverage() {
    assertEquals(36L, Day09.part2("example3.txt"));
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 09 Part 2 Answer is: " + Day09.part2("input.txt"));
  }
}
