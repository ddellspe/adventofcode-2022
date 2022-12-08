package net.ddellspe.day07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.ddellspe.utils.InputUtils;

public class Day07 {
  static Pattern FILE_PATTERN = Pattern.compile("(\\d+) (.+)");

  private Day07() {}

  public static long part1(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day07.class);
    Directory directory = new Directory(null);
    List<Directory> directories = new ArrayList<>();
    directories.add(directory);
    for (String line : lines) {
      if ("$ cd /".equals(line)) {
        continue;
      }
      Matcher matcher = FILE_PATTERN.matcher(line);
      if (line.startsWith("$ cd ..")) {
        directory = directory.getParent();
      } else if (line.startsWith("$ cd")) {
        directory = directory.getDirectories().get(line.split(" ")[2]);
        directories.add(directory);
      } else if (line.startsWith("dir")) {
        directory.addDirectory(line.split(" ")[1]);
      } else if (matcher.matches()) {
        directory.addFile(matcher.group(2), Long.parseLong(matcher.group(1)));
      }
    }
    return directories.stream()
        .map(Directory::getSize)
        .filter(v -> v <= 100000L)
        .reduce(0L, Long::sum);
  }

  public static long part2(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day07.class);
    Directory directory = new Directory(null);
    List<Directory> directories = new ArrayList<>();
    directories.add(directory);
    for (String line : lines) {
      if ("$ cd /".equals(line)) {
        continue;
      }
      Matcher matcher = FILE_PATTERN.matcher(line);
      if (line.startsWith("$ cd ..")) {
        directory = directory.getParent();
      } else if (line.startsWith("$ cd")) {
        directory = directory.getDirectories().get(line.split(" ")[2]);
        directories.add(directory);
      } else if (line.startsWith("dir")) {
        directory.addDirectory(line.split(" ")[1]);
      } else if (matcher.matches()) {
        directory.addFile(matcher.group(2), Long.parseLong(matcher.group(1)));
      }
    }
    long totalSpace = directories.get(0).getSize();
    final long spaceNecessary = 30_000_000L - (70_000_000L - totalSpace);
    return directories.stream()
        .map(Directory::getSize)
        .filter(v -> v >= spaceNecessary)
        .sorted()
        .findFirst()
        .get();
  }

  public static class Directory {
    private final Map<String, Long> files;
    private final Map<String, Directory> directories;
    private final Directory parent;

    public Directory(Directory parent) {
      this.files = new HashMap<>();
      this.directories = new HashMap<>();
      this.parent = parent;
    }

    public void addDirectory(String directoryName) {
      directories.put(directoryName, new Directory(this));
    }

    public void addFile(String filename, long fileSize) {
      files.put(filename, fileSize);
    }

    public Directory getParent() {
      return parent;
    }

    public Map<String, Directory> getDirectories() {
      return directories;
    }

    public long getSize() {
      return files.values().stream().reduce(0L, Long::sum)
          + directories.values().stream().map(Directory::getSize).reduce(0L, Long::sum);
    }
  }
}
