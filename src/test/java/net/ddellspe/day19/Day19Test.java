package net.ddellspe.day19;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import net.ddellspe.day19.Day19.State;
import org.junit.jupiter.api.Test;

public class Day19Test {
  Day19Test() {}

  @Test
  public void providedInputTestPart1() {
    assertEquals(33L, Day19.part1("example.txt"));
  }

  @Test
  public void brokenInputTestPart1() {
    assertEquals(0L, Day19.part1("example2.txt"));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 19 Part 1 Answer is: " + Day19.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertEquals(3472L, Day19.part2("example.txt"));
  }

  @Test
  public void brokenInputTestPart2() {
    assertEquals(1L, Day19.part2("example2.txt"));
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 19 Part 2 Answer is: " + Day19.part2("input.txt"));
  }

  @Test
  public void testState() {
    State original = new State(1, 2, 3, 4, 5, 6, 7, 8, 9);
    assertEquals(original, original);
    assertNotEquals(original, new State(0, 2, 3, 4, 5, 6, 7, 8, 9));
    assertNotEquals(original, new State(1, 1, 3, 4, 5, 6, 7, 8, 9));
    assertNotEquals(original, new State(1, 2, 1, 4, 5, 6, 7, 8, 9));
    assertNotEquals(original, new State(1, 2, 3, 1, 5, 6, 7, 8, 9));
    assertNotEquals(original, new State(1, 2, 3, 4, 1, 6, 7, 8, 9));
    assertNotEquals(original, new State(1, 2, 3, 4, 5, 1, 7, 8, 9));
    assertNotEquals(original, new State(1, 2, 3, 4, 5, 6, 1, 8, 9));
    assertNotEquals(original, new State(1, 2, 3, 4, 5, 6, 7, 1, 9));
    assertNotEquals(original, new State(1, 2, 3, 4, 5, 6, 7, 8, 1));
  }
}
