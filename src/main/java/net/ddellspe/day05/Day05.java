package net.ddellspe.day05;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.ddellspe.utils.InputUtils;

public class Day05 {
  public static final Pattern PARSER = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)");

  Day05() {}

  public static String part1(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day05.class);
    List<Move> moves = new ArrayList<>();
    List<Stack<String>> ships = new ArrayList<>(10);
    for (int i = 0; i < 10; i++) {
      ships.add(new Stack<>());
    }
    for (String line : lines) {
      if (line.startsWith("move")) {
        moves.add(new Move(line));
      } else if (line.contains("[")) {
        for (int i = 1; i < line.length(); i += 4) {
          if (line.charAt(i) != ' ') {
            ships.get(((i - 1) / 4) + 1).insertElementAt(String.valueOf(line.charAt(i)), 0);
          }
        }
      }
    }
    for (Move move : moves) {
      for (int i = 0; i < move.getCount(); i++) {
        ships.get(move.getDest()).push(ships.get(move.getSource()).pop());
      }
    }

    return ships.subList(1, 10).stream()
        .filter(stack -> !stack.isEmpty())
        .map(Stack::pop)
        .reduce("", (prev, cur) -> prev + cur);
  }

  public static String part2(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day05.class);
    List<Move> moves = new ArrayList<>();
    List<Stack<String>> ships = new ArrayList<>(10);
    for (int i = 0; i < 10; i++) {
      ships.add(new Stack<>());
    }
    for (String line : lines) {
      if (line.startsWith("move")) {
        moves.add(new Move(line));
      } else if (line.contains("[")) {
        for (int i = 1; i < line.length(); i += 4) {
          if (line.charAt(i) != ' ') {
            ships.get(((i - 1) / 4) + 1).insertElementAt(String.valueOf(line.charAt(i)), 0);
          }
        }
      }
    }
    for (Move move : moves) {
      Stack<String> temp = new Stack<>();
      for (int i = 0; i < move.getCount(); i++) {
        temp.push(ships.get(move.getSource()).pop());
      }
      for (int i = 0; i < move.getCount(); i++) {
        ships.get(move.getDest()).push(temp.pop());
      }
    }

    return ships.subList(1, 10).stream()
        .filter(stack -> !stack.isEmpty())
        .map(Stack::pop)
        .reduce("", (prev, cur) -> prev + cur);
  }

  public static class Move {
    int count;
    int source;
    int dest;

    public Move(String input) {
      Matcher match = PARSER.matcher(input);
      if (match.matches()) {
        count = Integer.parseInt(match.group(1));
        source = Integer.parseInt(match.group(2));
        dest = Integer.parseInt(match.group(3));
      } else {
        count = 0;
        source = 0;
        dest = 0;
      }
    }

    public int getCount() {
      return count;
    }

    public Move setCount(int count) {
      this.count = count;
      return this;
    }

    public int getSource() {
      return source;
    }

    public Move setSource(int source) {
      this.source = source;
      return this;
    }

    public int getDest() {
      return dest;
    }

    public Move setDest(int dest) {
      this.dest = dest;
      return this;
    }

    @Override
    public String toString() {
      return "Move{" + "count=" + count + ", source=" + source + ", dest=" + dest + '}';
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Move move = (Move) o;
      return count == move.count && source == move.source && dest == move.dest;
    }

    @Override
    public int hashCode() {
      return Objects.hash(count, source, dest);
    }
  }
}
