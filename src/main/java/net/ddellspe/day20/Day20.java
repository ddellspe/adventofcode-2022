package net.ddellspe.day20;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import net.ddellspe.utils.InputUtils;

public class Day20 {
  private Day20() {}

  public static long part1(String filename) {
    List<Long> numbers =
        InputUtils.numberPerLine(filename, Day20.class).stream()
            .map(Long::valueOf)
            .collect(Collectors.toList());
    List<Node> file =
        IntStream.range(0, numbers.size())
            .mapToObj(idx -> new Node(idx, numbers.get(idx)))
            .collect(Collectors.toList());
    int fileLength = file.size();
    for (int i = 0; i < numbers.size(); i++) {
      final Node node = new Node(i, numbers.get(i));
      long currentIndex = file.indexOf(node);
      file.remove(node);
      currentIndex += node.value;
      currentIndex %= (numbers.size() - 1);
      if (currentIndex < 0) {
        currentIndex += (numbers.size() - 1);
      }
      file.add((int) currentIndex, node);
    }
    int zeroIndex = file.indexOf(new Node(numbers.indexOf(0L), 0L));
    long sum = 0L;
    for (int i = zeroIndex + 1000; i <= zeroIndex + 3000; i += 1000) {
      sum += file.get(i % fileLength).value;
    }
    return sum;
  }

  public static long part2(String filename) {
    List<Long> numbers =
        InputUtils.numberPerLine(filename, Day20.class).stream()
            .map(Long::valueOf)
            .map(v -> v * 811589153L)
            .collect(Collectors.toList());
    List<Node> file =
        IntStream.range(0, numbers.size())
            .mapToObj(idx -> new Node(idx, numbers.get(idx)))
            .collect(Collectors.toList());
    int fileLength = file.size();
    for (int rounds = 0; rounds < 10; rounds++) {
      for (int i = 0; i < numbers.size(); i++) {
        final Node node = new Node(i, numbers.get(i));
        long currentIndex = file.indexOf(node);
        file.remove(node);
        currentIndex += node.value;
        currentIndex %= (numbers.size() - 1);
        if (currentIndex < 0) {
          currentIndex += (numbers.size() - 1);
        }
        file.add((int) currentIndex, node);
      }
    }
    int zeroIndex = file.indexOf(new Node(numbers.indexOf(0L), 0L));
    long sum = 0L;
    for (int i = zeroIndex + 1000; i <= zeroIndex + 3000; i += 1000) {
      sum += file.get(i % fileLength).value;
    }
    return sum;
  }

  public static class Node {
    int index;
    long value;

    public Node(int index, long value) {
      this.index = index;
      this.value = value;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      Node node = (Node) o;
      return index == node.index && value == node.value;
    }
  }
}
