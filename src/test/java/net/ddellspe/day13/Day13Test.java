package net.ddellspe.day13;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import net.ddellspe.day13.Day13.Packet;
import org.junit.jupiter.api.Test;

public class Day13Test {
  Day13Test() {}

  @Test
  public void providedInputTestPart1() {
    assertEquals(13L, Day13.part1("example.txt"));
  }

  @Test
  public void solutionPart1() {
    System.out.println("Day 13 Part 1 Answer is: " + Day13.part1("input.txt"));
  }

  @Test
  public void providedInputTestPart2() {
    assertEquals(140L, Day13.part2("example.txt"));
  }

  @Test
  public void solutionPart2() {
    System.out.println("Day 13 Part 2 Answer is: " + Day13.part2("input.txt"));
  }

  @Test
  public void testPacket() {
    assertEquals("[[2],3]", new Packet("[[2],3]").toString());
    Packet packetOne = new Packet(new ArrayList<>(), 5L);
    Packet packetTwo = new Packet(new ArrayList<>(), 5L);
    assertTrue(packetOne.equals(packetTwo));
  }
}
