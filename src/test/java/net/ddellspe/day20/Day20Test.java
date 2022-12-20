package net.ddellspe.day20;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import net.ddellspe.day20.Day20.Node;
import org.junit.jupiter.api.Test;

public class Day20Test {
  Day20Test() {}

  @Test
  public void providedInputTestPart1() {
    assertEquals(3L, Day20.part1("example.txt"));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 20 Part 1 Answer is: " + Day20.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertEquals(1623178306L, Day20.part2("example.txt"));
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 20 Part 2 Answer is: " + Day20.part2("input.txt"));
  }

  @Test
  public void testNode() {
    Node node = new Node(0, 10L);
    assertEquals(node, node);
    assertNotEquals(node, new Node(1, 10L));
    assertNotEquals(node, new Node(0, 11L));
  }
}
