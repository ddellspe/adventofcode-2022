package net.ddellspe.day03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Day03Test {
  @Test
  public void providedInputTestPart1() {
    assertEquals(157L, Day03.part1("example.txt"));
  }

  @Test
  public void providedInputTestPart1Extended() {
    assertEquals(-144L, Day03.part1("exampleExtended.txt"));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 03 Part 1 Answer is: " + Day03.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertEquals(70L, Day03.part2("example.txt"));
  }

  @Test
  public void providedInputTestPart2Extended() {
    assertEquals(-48L, Day03.part2("exampleExtended.txt"));
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 03 Part 2 Answer is: " + Day03.part2("input.txt"));
  }
}
