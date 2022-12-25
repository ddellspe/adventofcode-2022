package net.ddellspe.day25;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Day25Test {
  Day25Test() {}

  @Test
  public void providedInputTestPart1() {
    assertEquals("2=-1=0", Day25.part1("example.txt"));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 25 Part 1 Answer is: " + Day25.part1("input.txt"));
  }
}
