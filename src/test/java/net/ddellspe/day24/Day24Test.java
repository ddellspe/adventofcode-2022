package net.ddellspe.day24;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import net.ddellspe.day24.Day24.State;
import net.ddellspe.utils.Point;
import org.junit.jupiter.api.Test;

public class Day24Test {
  Day24Test() {}

  @Test
  public void providedInputTestPart1() {
    assertEquals(18L, Day24.part1("example.txt"));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 24 Part 1 Answer is: " + Day24.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertEquals(54L, Day24.part2("example.txt"));
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 24 Part 2 Answer is: " + Day24.part2("input.txt"));
  }

  @Test
  public void testState() {
    State state = new State(new Point(0, 0), 0L);
    assertEquals(state, state);
    assertFalse(state.equals(null));
    assertFalse(state.equals(""));
  }
}
