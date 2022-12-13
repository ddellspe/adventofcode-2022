package net.ddellspe.day13;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import net.ddellspe.utils.InputUtils;

public class Day13 {
  private Day13() {}

  public static long part1(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day13.class);
    List<Packet> packets = new ArrayList<>();
    for (String line : lines) {
      if (line.isEmpty()) {
        continue;
      }
      packets.add(new Packet(line));
    }
    long sum = 0L;
    for (int index = 0; index < packets.size() / 2; index++) {
      if (packets.get(index * 2).compareTo(packets.get(index * 2 + 1)) == 1) {
        sum += index + 1L;
      }
    }
    return sum;
  }

  public static long part2(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day13.class);
    List<Packet> packets = new ArrayList<>();
    for (String line : lines) {
      if (line.isEmpty()) {
        continue;
      }
      packets.add(new Packet(line));
    }
    Packet indOne = new Packet("[[2]]");
    Packet indTwo = new Packet("[[6]]");
    packets.add(indOne);
    packets.add(indTwo);
    packets.sort(Packet::compareToReverse);
    long product = 1L;
    for (int i = 0; i < packets.size(); i++) {
      if (packets.get(i).equals(indOne) || packets.get(i).equals(indTwo)) {
        product *= (i + 1);
      }
    }
    return product;
  }

  public static class Packet implements Comparable<Packet> {
    private List<Packet> packetList = null;
    private Long value = null;

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      Packet packet = (Packet) o;
      return Objects.equals(packetList, packet.packetList) && Objects.equals(value, packet.value);
    }

    public Packet(String input) {
      if (input.startsWith("[")) {
        packetList = new ArrayList<>();
        String tmpInput = input.substring(1, input.length() - 1);
        while (tmpInput.length() > 0) {
          if (tmpInput.startsWith("[")) {
            int bracketCount = 0;
            int i = 0;
            while (true) {
              if (tmpInput.charAt(i) == '[') {
                bracketCount++;
              } else if (tmpInput.charAt(i) == ']') {
                bracketCount--;
              }
              if (bracketCount == 0) {
                packetList.add(new Packet(tmpInput.substring(0, i + 1)));
                tmpInput = tmpInput.substring(i + 1);
                break;
              }
              i++;
            }
          } else if (tmpInput.startsWith(",")) {
            tmpInput = tmpInput.substring(1);
          } else if (tmpInput.contains(",")) {
            int nextComma = tmpInput.indexOf(",");
            packetList.add(new Packet(tmpInput.substring(0, nextComma)));
            tmpInput = tmpInput.substring(nextComma + 1);
          } else {
            packetList.add(new Packet(tmpInput));
            tmpInput = "";
          }
        }
      } else {
        value = Long.parseLong(input);
      }
    }

    public Packet(List<Packet> packetList, Long value) {
      this.packetList = packetList;
      this.value = value;
    }

    public Packet(Packet packet) {
      packetList = new ArrayList<>();
      packetList.add(packet);
    }

    @Override
    public int compareTo(Packet right) {
      if (packetList != null) {
        if (right.packetList != null) {
          for (int i = 0; i < Math.max(packetList.size(), right.packetList.size()); i++) {
            if (packetList.size() < (i + 1)) {
              return 1;
            } else if (right.packetList.size() < (i + 1)) {
              return -1;
            } else {
              int comp = packetList.get(i).compareTo(right.packetList.get(i));
              if (comp == 0) {
                continue;
              }
              return comp;
            }
          }
        } else {
          return compareTo(new Packet(right));
        }
      } else {
        if (right.packetList != null) {
          return new Packet(this).compareTo(right);
        } else {
          return value.compareTo(right.value) * -1;
        }
      }
      return 0;
    }

    public int compareToReverse(Packet right) {
      return this.compareTo(right) * -1;
    }

    @Override
    public String toString() {
      if (packetList != null) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < packetList.size(); i++) {
          builder.append(packetList.get(i).toString());
          if (i < packetList.size() - 1) {
            builder.append(",");
          }
        }
        builder.append("]");
        return builder.toString();
      } else {
        return String.valueOf(value);
      }
    }
  }
}
