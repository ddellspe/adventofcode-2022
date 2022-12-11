package net.ddellspe.day11;

import static org.junit.jupiter.api.Assertions.assertEquals;

import net.ddellspe.day11.Day11.Monkey;
import org.junit.jupiter.api.Test;

public class Day11Test {
  Day11Test() {}

  @Test
  public void providedInputTestPart1() {
    assertEquals(10605L, Day11.part1("example.txt"));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 11 Part 1 Answer is: " + Day11.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertEquals(2713310158L, Day11.part2("example.txt"));
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 11 Part 2 Answer is: " + Day11.part2("input.txt"));
  }

  @Test
  public void MonkeyTests() {
    Monkey monkey = new Monkey();
    monkey.setOperation("+");
    monkey.setValue("old");
    assertEquals(22L, monkey.getValue(11L));
  }
}
