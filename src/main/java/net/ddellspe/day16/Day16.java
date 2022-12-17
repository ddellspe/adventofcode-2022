package net.ddellspe.day16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import net.ddellspe.utils.InputUtils;

public class Day16 {
  private Day16() {}

  public static long part1(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day16.class);
    Map<String, Set<String>> flowConnections = new LinkedHashMap<>();
    Map<String, Long> flowRates = new LinkedHashMap<>();
    flowConnections.put("start", Collections.singleton("AA"));
    for (String line : lines) {
      String valve = line.substring(line.indexOf("Valve") + 6, line.indexOf("has") - 1);
      long rate = Long.parseLong(line.substring(line.indexOf("=") + 1, line.indexOf(";")));
      flowRates.put(valve, rate);
      flowConnections.put(
          valve,
          Arrays.stream(line.substring(line.indexOf("valve") + 6).split(", "))
              .map(String::strip)
              .collect(Collectors.toSet()));
    }
    Set<String> worthWhileValves =
        flowRates.entrySet().stream()
            .filter(e -> e.getValue() > 0)
            .map(Entry::getKey)
            .collect(Collectors.toSet());
    worthWhileValves.add("AA");
    Map<String, Integer> movementCost = new HashMap<>();
    for (String valve1 : worthWhileValves) {
      for (String valve2 : worthWhileValves) {
        if (valve1.equals(valve2)) {
          continue;
        }
        int cost = calculateShortestDistance(valve1, valve2, flowConnections);
        movementCost.put(valve1 + "->" + valve2, cost);
      }
    }
    long best = -1;
    LinkedList<ValveState> queue = new LinkedList<>();
    worthWhileValves.remove("AA");
    queue.add(new ValveState(0, 0, List.of("AA"), worthWhileValves));
    while (queue.size() > 0) {
      ValveState state = queue.poll();
      best = Math.max(best, state.score);

      String currentPosition = state.route.get(state.route.size() - 1);
      for (String nextPath : state.valves) {
        int neededTime = state.time + movementCost.get(currentPosition + "->" + nextPath);
        if (neededTime < 30) {
          List<String> route = new ArrayList<>(state.route);
          Set<String> valves = new HashSet<>(state.valves);
          valves.remove(nextPath);
          route.add(nextPath);
          queue.add(
              new ValveState(
                  state.score + (30L - neededTime) * flowRates.get(nextPath),
                  neededTime,
                  route,
                  valves));
        }
      }
    }
    return best;
  }

  public static long part2(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day16.class);
    Map<String, Set<String>> flowConnections = new LinkedHashMap<>();
    Map<String, Long> flowRates = new LinkedHashMap<>();
    flowConnections.put("start", Collections.singleton("AA"));
    for (String line : lines) {
      String valve = line.substring(line.indexOf("Valve") + 6, line.indexOf("has") - 1);
      long rate = Long.parseLong(line.substring(line.indexOf("=") + 1, line.indexOf(";")));
      flowRates.put(valve, rate);
      flowConnections.put(
          valve,
          Arrays.stream(line.substring(line.indexOf("valve") + 6).split(", "))
              .map(String::strip)
              .collect(Collectors.toSet()));
    }
    Set<String> worthWhileValves =
        flowRates.entrySet().stream()
            .filter(e -> e.getValue() > 0)
            .map(Entry::getKey)
            .collect(Collectors.toSet());
    worthWhileValves.add("AA");
    Map<String, Integer> movementCost = new HashMap<>();
    for (String valve1 : worthWhileValves) {
      for (String valve2 : worthWhileValves) {
        if (valve1.equals(valve2)) {
          continue;
        }
        int cost = calculateShortestDistance(valve1, valve2, flowConnections);
        movementCost.put(valve1 + "->" + valve2, cost);
      }
    }
    Map<Set<String>, Long> bestScores = new HashMap<>();
    LinkedList<ValveState> queue = new LinkedList<>();
    worthWhileValves.remove("AA");
    queue.add(new ValveState(0, 0, List.of("AA"), worthWhileValves));
    while (queue.size() > 0) {
      ValveState state = queue.poll();
      Set<String> visitedItems =
          state.route.stream().filter(v -> !v.equals("AA")).collect(Collectors.toSet());
      bestScores.put(
          visitedItems, Math.max(bestScores.getOrDefault(visitedItems, state.score), state.score));

      String currentPosition = state.route.get(state.route.size() - 1);
      for (String nextPath : state.valves) {
        int neededTime = state.time + movementCost.get(currentPosition + "->" + nextPath);
        if (neededTime < 26) {
          List<String> route = new ArrayList<>(state.route);
          Set<String> valves = new HashSet<>(state.valves);
          valves.remove(nextPath);
          route.add(nextPath);
          queue.add(
              new ValveState(
                  state.score + (26L - neededTime) * flowRates.get(nextPath),
                  neededTime,
                  route,
                  valves));
        }
      }
    }
    long best = -1L;
    for (Set<String> set1 : bestScores.keySet()) {
      for (Set<String> set2 : bestScores.keySet()) {
        if (set1.stream().noneMatch(set2::contains)) {
          best = Math.max(best, bestScores.get(set1) + bestScores.get(set2));
        }
      }
    }
    return best;
  }

  public static class ValveState {
    long score;
    int time;
    List<String> route;
    Set<String> valves;

    public ValveState(long score, int time, List<String> route, Set<String> remainingValves) {
      this.score = score;
      this.time = time;
      this.route = new ArrayList<>(route);
      this.valves = new HashSet<>(remainingValves);
    }
  }

  public static int calculateShortestDistance(
      String start, String end, Map<String, Set<String>> connections) {
    LinkedList<List<String>> queue = new LinkedList<>();
    List<String> path = new ArrayList<>();
    path.add(start);
    queue.add(path);
    while (queue.size() > 0) {
      path = queue.poll();
      String lastElement = path.get(path.size() - 1);
      if (lastElement.equals(end)) {
        return path.size();
      } else {
        for (String dest : connections.get(lastElement)) {
          List<String> pathCopy = new ArrayList<>(path);
          if (pathCopy.contains(dest)) {
            continue;
          }
          pathCopy.add(dest);
          queue.add(pathCopy);
        }
      }
    }
    return -1;
  }
}
