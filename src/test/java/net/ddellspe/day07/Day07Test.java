package net.ddellspe.day07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Day07Test {
  Day07Test() {}

  @Test
  public void providedInputTestPart1() {
    assertEquals(95437L, Day07.part1("example.txt"));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 07 Part 1 Answer is: " + Day07.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertEquals(24933642L, Day07.part2("example.txt"));
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 07 Part 2 Answer is: " + Day07.part2("input.txt"));
  }
}
