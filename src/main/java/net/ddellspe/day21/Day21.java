package net.ddellspe.day21;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.ddellspe.utils.InputUtils;

public class Day21 {
  private Day21() {}

  public static long part1(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day21.class);
    Map<String, Monkey> monkeys = new HashMap<>();
    for (String monkey : lines) {
      String[] data = monkey.split(": ");
      monkeys.put(data[0], new Monkey(data[1]));
    }
    return monkeys.get("root").getValue(monkeys);
  }

  public static long part2(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day21.class);
    Map<String, Monkey> baseMonkeys = new HashMap<>();
    for (String monkey : lines) {
      String[] data;
      if (monkey.startsWith("humn")) {
        continue;
      }
      data = monkey.split(": ");
      baseMonkeys.put(data[0], new Monkey(data[1]));
    }
    Monkey rootMonkey = baseMonkeys.get("root");
    long rightValue = baseMonkeys.get(rootMonkey.rightSide).getValue(baseMonkeys);
    Monkey currMonkey = baseMonkeys.get(rootMonkey.leftSide);
    while (true) {
      try {
        rightValue = currMonkey.getInverseValue(rightValue, baseMonkeys, true);
        currMonkey = baseMonkeys.get(currMonkey.rightSide);
      } catch (NullPointerException e) {
        try {
          rightValue = currMonkey.getInverseValue(rightValue, baseMonkeys, false);
          currMonkey = baseMonkeys.get(currMonkey.leftSide);
        } catch (NullPointerException e2) {
          break;
        }
      }
    }
    return rightValue;
  }

  public static class Monkey {
    public String leftSide;
    public String rightSide;
    public int operator;
    public Long value;

    public Monkey(String input) {
      if (input.split(" ").length > 1) {
        String[] splits = input.strip().split(" ");
        leftSide = splits[0];
        rightSide = splits[2];
        switch (splits[1]) {
          case "+":
            operator = 1;
            break;
          case "-":
            operator = 2;
            break;
          case "*":
            operator = 3;
            break;
          case "/":
            operator = 4;
            break;
        }
        value = null;
      } else {
        leftSide = null;
        rightSide = null;
        operator = -1;
        value = Long.parseLong(input.strip());
      }
    }

    public long getValue(Map<String, Monkey> monkeys) {
      if (leftSide != null) {
        long value = monkeys.get(leftSide).getValue(monkeys);
        switch (operator) {
          case 1:
            value += monkeys.get(rightSide).getValue(monkeys);
            break;
          case 2:
            value -= monkeys.get(rightSide).getValue(monkeys);
            break;
          case 3:
            value *= monkeys.get(rightSide).getValue(monkeys);
            break;
          case 4:
            value /= monkeys.get(rightSide).getValue(monkeys);
            break;
        }
        return value;
      } else {
        return this.value;
      }
    }

    public long getInverseValue(
        long originalValue, Map<String, Monkey> monkeys, boolean leftClear) {
      if (leftClear) {
        long change = monkeys.get(leftSide).getValue(monkeys);
        switch (operator) {
          case 1:
            originalValue -= change;
            break;
          case 2:
            originalValue -= change;
            originalValue *= -1;
            break;
          case 3:
            originalValue /= change;
            break;
          case 4:
            originalValue = change / originalValue;
            break;
        }
      } else {
        long change = monkeys.get(rightSide).getValue(monkeys);
        switch (operator) {
          case 1:
            originalValue -= change;
            break;
          case 2:
            originalValue += change;
            break;
          case 3:
            originalValue /= change;
            break;
          case 4:
            originalValue *= change;
            break;
        }
      }
      return originalValue;
    }
  }
}
