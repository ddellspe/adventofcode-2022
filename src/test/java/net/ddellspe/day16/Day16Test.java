package net.ddellspe.day16;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class Day16Test {
  Day16Test() {}

  @Test
  public void providedInputTestPart1() {
    assertEquals(1651L, Day16.part1("example.txt"));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 16 Part 1 Answer is: " + Day16.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertEquals(1707L, Day16.part2("example.txt"));
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 16 Part 2 Answer is: " + Day16.part2("input.txt"));
  }

  @Test
  public void testInvalidPaths() {
    Map<String, Set<String>> connections = new HashMap<>();
    connections.put("AA", Set.of("BB", "CC"));
    connections.put("BB", Set.of("AA"));
    connections.put("CC", Set.of("AA"));
    assertEquals(-1, Day16.calculateShortestDistance("AA", "DD", connections));
  }
}
