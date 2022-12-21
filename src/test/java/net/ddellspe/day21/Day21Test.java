package net.ddellspe.day21;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import net.ddellspe.day21.Day21.Monkey;
import org.junit.jupiter.api.Test;

public class Day21Test {
  Day21Test() {}

  @Test
  public void providedInputTestPart1() {
    assertEquals(152L, Day21.part1("example.txt"));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 21 Part 1 Answer is: " + Day21.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertEquals(301L, Day21.part2("example.txt"));
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 21 Part 2 Answer is: " + Day21.part2("input.txt"));
  }

  @Test
  public void monkeyTest() {
    Map<String, Monkey> monkeys = new HashMap<>();
    monkeys.put("left", new Monkey("20"));
    monkeys.put("rite", new Monkey("10"));
    assertEquals(30L, new Monkey("left + rite").getValue(monkeys));
    assertEquals(10L, new Monkey("left - rite").getValue(monkeys));
    assertEquals(200L, new Monkey("left * rite").getValue(monkeys));
    assertEquals(2L, new Monkey("left / rite").getValue(monkeys));
    assertEquals(80L, new Monkey("left + right").getInverseValue(100L, monkeys, true));
    assertEquals(-80L, new Monkey("left - right").getInverseValue(100L, monkeys, true));
    assertEquals(5L, new Monkey("left * right").getInverseValue(100L, monkeys, true));
    assertEquals(2L, new Monkey("left / right").getInverseValue(10L, monkeys, true));
    assertEquals(80L, new Monkey("right + left").getInverseValue(100L, monkeys, false));
    assertEquals(120L, new Monkey("right - left").getInverseValue(100L, monkeys, false));
    assertEquals(5L, new Monkey("right * left").getInverseValue(100L, monkeys, false));
    assertEquals(200L, new Monkey("right / left").getInverseValue(10L, monkeys, false));
    // switch conditions
    assertEquals(20L, new Monkey("left = rite").getValue(monkeys));
    assertEquals(0, new Monkey("left = rite").operator);
    assertEquals(100L, new Monkey("left = right").getInverseValue(100L, monkeys, true));
    assertEquals(100L, new Monkey("right = left").getInverseValue(100L, monkeys, false));
  }
}
