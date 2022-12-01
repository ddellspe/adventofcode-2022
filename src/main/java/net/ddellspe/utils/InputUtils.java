package net.ddellspe.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class InputUtils {
  public static List<Integer> numberPerLine(String filename, Class klass) {
    BufferedReader reader =
        new BufferedReader(
            new InputStreamReader(Objects.requireNonNull(klass.getResourceAsStream(filename))));
    return reader.lines().map(Integer::parseInt).collect(Collectors.toList());
  }

  public static List<Integer> numbersInOneLine(String filename, Class klass) {
    BufferedReader reader =
        new BufferedReader(
            new InputStreamReader(Objects.requireNonNull(klass.getResourceAsStream(filename))));
    return Arrays.stream(reader.lines().findFirst().get().split(","))
        .map(Integer::parseInt)
        .collect(Collectors.toList());
  }

  public static List<String> stringPerLine(String filename, Class klass) {
    BufferedReader reader =
        new BufferedReader(
            new InputStreamReader(Objects.requireNonNull(klass.getResourceAsStream(filename))));
    return reader.lines().collect(Collectors.toList());
  }
}
