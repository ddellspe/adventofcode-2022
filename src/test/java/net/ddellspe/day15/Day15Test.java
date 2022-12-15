package net.ddellspe.day15;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Day15Test {
  Day15Test() {}

  @Test
  public void providedInputTestPart1() {
    assertEquals(26L, Day15.part1("example.txt", 10));
  }

  @Test
  public void providedInputTestPart1BadCount() {
    assertEquals(0L, Day15.part1("example.txt", 1000));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 15 Part 1 Answer is: " + Day15.part1("input.txt", 2000000));
  }

  @Test
  public void providedInputTestPart2() {
    assertEquals(56000011L, Day15.part2("example.txt", 20));
  }

  @Test
  public void generatedInputFailsTestPart2() {
    assertEquals(0L, Day15.part2("example2.txt", 3));
  }

  @Test
  public void generatedInputFailsTestPart2Set2() {
    assertEquals(0L, Day15.part2("example2.txt", 1));
  }

  @Test
  public void generatedInputFailsTestPart2Set3() {
    assertEquals(4000000L, Day15.part2("example3.txt", 2));
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 15 Part 2 Answer is: " + Day15.part2("input.txt", 4000000));
  }
}
