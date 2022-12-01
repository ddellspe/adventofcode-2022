package net.ddellspe.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

public class InputUtilsTest {
  @Test
  public void testNumbersInLine() {
    List<Integer> values = IntStream.range(1, 11).boxed().collect(Collectors.toList());
    assertEquals(values, InputUtils.numbersInOneLine("numbers_on_one_line.txt", InputUtils.class));
  }

  @Test
  public void testNumberPerLine() {
    List<Integer> values = IntStream.range(1, 11).boxed().collect(Collectors.toList());
    assertEquals(values, InputUtils.numberPerLine("number_per_line.txt", InputUtils.class));
  }

  @Test
  public void testStringPerLine() {
    List<String> values =
        IntStream.range(1, 6).boxed().map(String::valueOf).collect(Collectors.toList());
    assertEquals(values, InputUtils.stringPerLine("string_per_line.txt", InputUtils.class));
  }

  @Test
  public void testFileNotFound() {
    assertThrows(
        NullPointerException.class,
        () -> {
          InputUtils.numbersInOneLine("file_not_found.txt", InputUtils.class);
        });
    assertThrows(
        NullPointerException.class,
        () -> {
          InputUtils.numberPerLine("file_not_found.txt", InputUtils.class);
        });
    assertThrows(
        NullPointerException.class,
        () -> {
          InputUtils.stringPerLine("file_not_found.txt", InputUtils.class);
        });
  }
}
