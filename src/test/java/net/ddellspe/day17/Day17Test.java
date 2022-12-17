package net.ddellspe.day17;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Set;
import net.ddellspe.day17.Day17.State;
import net.ddellspe.utils.Point;
import org.junit.jupiter.api.Test;

public class Day17Test {
  Day17Test() {}

  @Test
  public void providedInputTestPart1() {
    assertEquals(3068L, Day17.part1("example.txt"));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 17 Part 1 Answer is: " + Day17.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertEquals(1514285714288L, Day17.part2("example.txt"));
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 17 Part 2 Answer is: " + Day17.part2("input.txt"));
  }

  @Test
  public void testState() {
    Set<Point> points = Set.of(new Point(0L, 100L), new Point(3L, 140L));
    Set<Point> points2 = Set.of(new Point(0L, 10L), new Point(0, 100L), new Point(3L, 140L));
    Set<Point> points3 = Set.of(new Point(0L, 99L), new Point(3L, 140L));
    State state = new State(0, 0, points);
    State state2 = new State(0, 0, points2);
    State state3 = new State(1, 0, points2);
    State state4 = new State(0, 1, points2);
    State state5 = new State(0, 0, points3);
    assertEquals(state, state);
    assertEquals(state, state2);
    assertNotEquals(state, state3);
    assertNotEquals(state, state4);
    assertNotEquals(state, state5);
    assertFalse(state.equals(null));
    assertFalse(state.equals(""));
  }
}
