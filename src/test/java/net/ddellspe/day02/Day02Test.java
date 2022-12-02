package net.ddellspe.day02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class Day02Test {
  @Test
  public void providedInputTestPart1() {
    assertEquals(15L, Day02.part1("example.txt"));
  }

  @Test
  public void providedInputTestPart1ExceptionA() {
    IllegalStateException exception =
        assertThrows(
            IllegalStateException.class,
            () -> {
              Day02.part1("example2.txt");
            });
    assertEquals("Invalid choice: A", exception.getMessage());
  }

  @Test
  public void providedInputTestPart1ExceptionB() {
    IllegalStateException exception =
        assertThrows(
            IllegalStateException.class,
            () -> {
              Day02.part1("example3.txt");
            });
    assertEquals("Invalid choice: B", exception.getMessage());
  }

  @Test
  public void providedInputTestPart1ExceptionC() {
    IllegalStateException exception =
        assertThrows(
            IllegalStateException.class,
            () -> {
              Day02.part1("example4.txt");
            });
    assertEquals("Invalid choice: C", exception.getMessage());
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 02 Part 1 Answer is: " + Day02.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertEquals(12L, Day02.part2("example.txt"));
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 02 Part 2 Answer is: " + Day02.part2("input.txt"));
  }

  @Test
  public void providedInputTestPart2ExceptionA() {
    IllegalStateException exception =
        assertThrows(
            IllegalStateException.class,
            () -> {
              Day02.part2("example2.txt");
            });
    assertEquals("Invalid choice: A", exception.getMessage());
  }

  @Test
  public void providedInputTestPart2ExceptionB() {
    IllegalStateException exception =
        assertThrows(
            IllegalStateException.class,
            () -> {
              Day02.part2("example3.txt");
            });
    assertEquals("Invalid choice: B", exception.getMessage());
  }

  @Test
  public void providedInputTestPart2ExceptionC() {
    IllegalStateException exception =
        assertThrows(
            IllegalStateException.class,
            () -> {
              Day02.part2("example4.txt");
            });
    assertEquals("Invalid choice: C", exception.getMessage());
  }
}
