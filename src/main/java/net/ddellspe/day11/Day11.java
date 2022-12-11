package net.ddellspe.day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import net.ddellspe.utils.InputUtils;
import net.ddellspe.utils.MathUtils;

public class Day11 {
  private Day11() {}

  public static long part1(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day11.class);
    List<Monkey> monkeys = new ArrayList<>();
    int monkeyNum = -1;
    Monkey monkey = new Monkey();
    for (String line : lines) {
      if (line.startsWith("Monkey")) {
        if (monkeyNum > -1) {
          monkeys.add(monkey);
          monkey = new Monkey();
        }
        monkeyNum = Integer.parseInt(line.substring(line.length() - 2, line.length() - 1));
      } else if (line.strip().startsWith("Starting")) {
        monkey.setItems(
            Arrays.stream(line.substring(line.indexOf(":") + 2).split(","))
                .map(String::strip)
                .map(Long::parseLong)
                .collect(Collectors.toList()));
      } else if (line.strip().startsWith("Operation")) {
        monkey.setOperation(line.indexOf('+') == -1 ? "*" : "+");
        monkey.setValue(line.substring(line.indexOf(monkey.getOperation()) + 2).strip());
      } else if (line.strip().startsWith("Test")) {
        monkey.setDivisor(Integer.parseInt(line.substring(line.indexOf("by ") + 3)));
      } else if (line.strip().startsWith("If true")) {
        monkey.setTrueMonkey(Integer.parseInt(line.substring(line.length() - 1)));
      } else if (line.strip().startsWith("If false")) {
        monkey.setFalseMonkey(Integer.parseInt(line.substring(line.length() - 1)));
      }
    }
    monkeys.add(monkey);
    List<Monkey> nextMonkeys =
        monkeys.stream().map(Monkey::copyMonkey).collect(Collectors.toList());
    List<Integer> inspections = new ArrayList<>();
    for (int i = 0; i < monkeys.size(); i++) {
      inspections.add(0);
    }
    for (int i = 0; i < 20; i++) {
      int monkeyNumber = 0;
      for (Monkey monk : monkeys) {
        for (Long item : monk.getItems()) {
          inspections.set(monkeyNumber, inspections.get(monkeyNumber) + 1);
          long nextValue = monk.getValue(item) / 3L;
          monkeyNum = monk.getNextMonkey(nextValue);
          if (monkeyNum <= monkeyNumber) {
            nextMonkeys.get(monkeyNum).addItem(nextValue);
          } else {
            monkeys.get(monkeyNum).addItem(nextValue);
          }
        }
        monkeyNumber++;
      }
      monkeys = nextMonkeys.stream().map(Monkey::cloneMonkey).collect(Collectors.toList());
      nextMonkeys = monkeys.stream().map(Monkey::copyMonkey).collect(Collectors.toList());
    }
    return inspections.stream()
        .sorted(Comparator.reverseOrder())
        .collect(Collectors.toList())
        .subList(0, 2)
        .stream()
        .reduce(1, (a, b) -> a * b);
  }

  public static long part2(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day11.class);
    List<Monkey> monkeys = new ArrayList<>();
    int monkeyNum = -1;
    Monkey monkey = new Monkey();
    for (String line : lines) {
      if (line.startsWith("Monkey")) {
        if (monkeyNum > -1) {
          monkeys.add(monkey);
          monkey = new Monkey();
        }
        monkeyNum = Integer.parseInt(line.substring(line.length() - 2, line.length() - 1));
      } else if (line.strip().startsWith("Starting")) {
        monkey.setItems(
            Arrays.stream(line.substring(line.indexOf(":") + 2).split(","))
                .map(String::strip)
                .map(Long::parseLong)
                .collect(Collectors.toList()));
      } else if (line.strip().startsWith("Operation")) {
        monkey.setOperation(line.indexOf('+') == -1 ? "*" : "+");
        monkey.setValue(line.substring(line.indexOf(monkey.getOperation()) + 2).strip());
      } else if (line.strip().startsWith("Test")) {
        monkey.setDivisor(Integer.parseInt(line.substring(line.indexOf("by ") + 3)));
      } else if (line.strip().startsWith("If true")) {
        monkey.setTrueMonkey(Integer.parseInt(line.substring(line.length() - 1)));
      } else if (line.strip().startsWith("If false")) {
        monkey.setFalseMonkey(Integer.parseInt(line.substring(line.length() - 1)));
      }
    }
    monkeys.add(monkey);
    List<Monkey> nextMonkeys =
        monkeys.stream().map(Monkey::copyMonkey).collect(Collectors.toList());
    List<Long> inspections = new ArrayList<>();
    for (int i = 0; i < monkeys.size(); i++) {
      inspections.add(0L);
    }
    // get the least common multiple of all divisors
    long lcm =
        MathUtils.lcm(
            monkeys.stream()
                .map(Monkey::getDivisor)
                .map(v -> (long) v)
                .collect(Collectors.toList()));
    for (int i = 0; i < 10000; i++) {
      int monkeyNumber = 0;
      for (Monkey monk : monkeys) {
        for (Long item : monk.getItems()) {
          inspections.set(monkeyNumber, inspections.get(monkeyNumber) + 1);
          long nextValue = monk.getValue(item);
          monkeyNum = monk.getNextMonkey(nextValue);
          if (monkeyNum <= monkeyNumber) {
            nextMonkeys.get(monkeyNum).addItem(nextValue % lcm);
          } else {
            monkeys.get(monkeyNum).addItem(nextValue % lcm);
          }
        }
        monkeyNumber++;
      }
      monkeys = nextMonkeys.stream().map(Monkey::cloneMonkey).collect(Collectors.toList());
      nextMonkeys = monkeys.stream().map(Monkey::copyMonkey).collect(Collectors.toList());
    }
    return inspections.stream()
        .sorted(Comparator.reverseOrder())
        .collect(Collectors.toList())
        .subList(0, 2)
        .stream()
        .reduce(1L, (a, b) -> a * b);
  }

  public static class Monkey {
    private List<Long> items;
    private String operation;
    private String value;
    private int divisor;
    private int trueMonkey;
    private int falseMonkey;

    public Monkey() {
      items = new ArrayList<>();
      operation = "*";
      value = "old";
      divisor = 23;
      trueMonkey = 0;
      falseMonkey = 0;
    }

    public Monkey(
        List<Long> items,
        String operation,
        String value,
        int divisor,
        int trueMonkey,
        int falseMonkey) {
      this.items = items;
      this.operation = operation;
      this.value = value;
      this.divisor = divisor;
      this.trueMonkey = trueMonkey;
      this.falseMonkey = falseMonkey;
    }

    public Monkey copyMonkey() {
      return new Monkey(new ArrayList<>(), operation, value, divisor, trueMonkey, falseMonkey);
    }

    public Monkey cloneMonkey() {
      return new Monkey(
          items.stream().collect(Collectors.toList()),
          operation,
          value,
          divisor,
          trueMonkey,
          falseMonkey);
    }

    public long getValue(long input) {
      long ans = input;
      if (operation.equals("*")) {
        if (value.equals("old")) {
          ans = input * input;
        } else {
          long val = Long.parseLong(value);
          ans = input * val;
        }
      } else {
        if (value.equals("old")) {
          ans = input + input;
        } else {
          long val = Long.parseLong(value);
          ans = input + val;
        }
      }
      return ans;
    }

    public int getNextMonkey(long input) {
      if (input % divisor == 0) {
        return trueMonkey;
      } else {
        return falseMonkey;
      }
    }

    public List<Long> getItems() {
      return items;
    }

    public void setItems(List<Long> items) {
      this.items = items;
    }

    public void addItem(Long item) {
      this.items.add(item);
    }

    public String getOperation() {
      return operation;
    }

    public void setOperation(String operation) {
      this.operation = operation;
    }

    public void setValue(String value) {
      this.value = value;
    }

    public int getDivisor() {
      return divisor;
    }

    public void setDivisor(int divisor) {
      this.divisor = divisor;
    }

    public void setTrueMonkey(int trueMonkey) {
      this.trueMonkey = trueMonkey;
    }

    public void setFalseMonkey(int falseMonkey) {
      this.falseMonkey = falseMonkey;
    }
  }
}
