package net.ddellspe.day05;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import net.ddellspe.day05.Day05.Move;
import org.junit.jupiter.api.Test;

public class Day05Test {
  Day05Test() {}

  @Test
  public void providedInputTestPart1() {
    assertEquals("CMZ", Day05.part1("example.txt"));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 05 Part 1 Answer is: " + Day05.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertEquals("MCD", Day05.part2("example.txt"));
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 05 Part 2 Answer is: " + Day05.part2("input.txt"));
  }

  @Test
  public void testMove() {
    Move move = new Move("stuff");
    assertEquals(0, move.getCount());
    assertEquals(0, move.getSource());
    assertEquals(0, move.getDest());

    move.setCount(1);
    move.setSource(2);
    move.setDest(3);

    assertEquals(1, move.getCount());
    assertEquals(2, move.getSource());
    assertEquals(3, move.getDest());

    Move move2 = new Move("move 1 from 2 to 3");
    assertEquals(move2, move);
    assertEquals(move, move);
    assertEquals(move2.hashCode(), move.hashCode());
    assertNotEquals(move, new Move("blah"));
    assertNotEquals(move, "move");
    assertEquals("Move{count=1, source=2, dest=3}", move.toString());
  }
}
