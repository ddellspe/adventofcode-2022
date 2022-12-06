package net.ddellspe.day06;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Day06Test {
  Day06Test() {}

  @Test
  public void providedInputTestPart1Test1() {
    assertEquals(7L, Day06.part1("example.txt"));
  }

  @Test
  public void providedInputTestPart1Test2() {
    assertEquals(5L, Day06.part1("example2.txt"));
  }

  @Test
  public void providedInputTestPart1Test3() {
    assertEquals(6L, Day06.part1("example3.txt"));
  }

  @Test
  public void providedInputTestPart1Test4() {
    assertEquals(10L, Day06.part1("example4.txt"));
  }

  @Test
  public void providedInputTestPart1Test5() {
    assertEquals(11L, Day06.part1("example5.txt"));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 06 Part 1 Answer is: " + Day06.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2Test1() {
    assertEquals(19L, Day06.part2("example.txt"));
  }

  @Test
  public void providedInputTestPart2Test2() {
    assertEquals(23L, Day06.part2("example2.txt"));
  }

  @Test
  public void providedInputTestPart2Test3() {
    assertEquals(23L, Day06.part2("example3.txt"));
  }

  @Test
  public void providedInputTestPart2Test4() {
    assertEquals(29L, Day06.part2("example4.txt"));
  }

  @Test
  public void providedInputTestPart2Test5() {
    assertEquals(26L, Day06.part2("example5.txt"));
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 06 Part 2 Answer is: " + Day06.part2("input.txt"));
  }
}
