package net.ddellspe.day19;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.ddellspe.utils.InputUtils;

public class Day19 {
  public static Pattern LINE_PARSER =
      Pattern.compile(
          "Blueprint (\\d+): "
              + "Each ore robot costs (\\d+) ore\\. "
              + "Each clay robot costs (\\d+) ore\\. "
              + "Each obsidian robot costs (\\d+) ore and (\\d+) clay\\. "
              + "Each geode robot costs (\\d+) ore and (\\d+) obsidian\\.");

  private Day19() {}

  public static long part1(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day19.class);
    List<Blueprint> blueprints = new ArrayList<>();
    for (String line : lines) {
      Matcher matcher = LINE_PARSER.matcher(line);
      if (matcher.matches()) {
        Cost oreCost = new Cost(Long.parseLong(matcher.group(2)), null, null, null);
        Cost clayCost = new Cost(Long.parseLong(matcher.group(3)), null, null, null);
        Cost obsidianCost =
            new Cost(
                Long.parseLong(matcher.group(4)), Long.parseLong(matcher.group(5)), null, null);
        Cost geodeCost =
            new Cost(
                Long.parseLong(matcher.group(6)), null, Long.parseLong(matcher.group(7)), null);
        blueprints.add(new Blueprint(oreCost, clayCost, obsidianCost, geodeCost));
      }
    }
    long total = 0L;
    for (int i = 0; i < blueprints.size(); i++) {
      long max = simulateBlueprint(blueprints.get(i), 24L);
      total += (max * (i + 1));
    }
    return total;
  }

  public static long part2(String filename) {
    List<String> lines = InputUtils.stringPerLine(filename, Day19.class);
    List<Blueprint> blueprints = new ArrayList<>();
    for (String line : lines) {
      Matcher matcher = LINE_PARSER.matcher(line);
      if (matcher.matches()) {
        Cost oreCost = new Cost(Long.parseLong(matcher.group(2)), null, null, null);
        Cost clayCost = new Cost(Long.parseLong(matcher.group(3)), null, null, null);
        Cost obsidianCost =
            new Cost(
                Long.parseLong(matcher.group(4)), Long.parseLong(matcher.group(5)), null, null);
        Cost geodeCost =
            new Cost(
                Long.parseLong(matcher.group(6)), null, Long.parseLong(matcher.group(7)), null);
        blueprints.add(new Blueprint(oreCost, clayCost, obsidianCost, geodeCost));
      }
    }
    long total = 1L;
    for (int i = 0; i < Math.min(3, blueprints.size()); i++) {
      long max = simulateBlueprint(blueprints.get(i), 32L);
      total *= max;
    }
    return total;
  }

  public static long simulateBlueprint(Blueprint blueprint, long minutes) {
    LinkedList<State> queue = new LinkedList<>();
    Set<State> seen = new HashSet<>();
    Map<Long, Long> best = new HashMap<>();
    queue.add(new State());
    long maxOreCost =
        Math.max(
            blueprint.oreCost.ore,
            Math.max(
                blueprint.clayCost.ore,
                Math.max(blueprint.obsidianCost.ore, blueprint.geodeCost.ore)));
    while (queue.size() > 0) {
      State currentState = queue.poll();
      State updatedState =
          new State(
              Math.min(maxOreCost * (minutes - currentState.time), currentState.ore),
              Math.min(
                  blueprint.obsidianCost.clay * (minutes - currentState.time), currentState.clay),
              Math.min(
                  blueprint.geodeCost.obsidian * (minutes - currentState.time),
                  currentState.obsidian),
              currentState.geodes,
              Math.min(currentState.oreRobots, maxOreCost),
              Math.min(currentState.clayRobots, blueprint.obsidianCost.clay),
              Math.min(currentState.obsidianRobots, blueprint.geodeCost.obsidian),
              currentState.geodeRobots,
              currentState.time);
      if (seen.contains(updatedState)) {
        continue;
      } else {
        seen.add(updatedState);
      }

      best.put(
          updatedState.time,
          Math.max(best.getOrDefault(updatedState.time, 0L), updatedState.geodes));

      if (updatedState.time == minutes) {
        continue;
      }
      if (blueprint.canPurchaseGeodeRobot(updatedState)) {
        queue.add(blueprint.purchaseGeodeRobot(blueprint.simulateMinute(updatedState)));
        continue;
      }
      if (blueprint.canPurchaseObsidianRobot(updatedState)) {
        queue.add(blueprint.purchaseObsidianRobot(blueprint.simulateMinute(updatedState)));
      }
      if (blueprint.canPurchaseClayRobot(updatedState)) {
        queue.add(blueprint.purchaseClayRobot(blueprint.simulateMinute(updatedState)));
      }
      if (blueprint.canPurchaseOreRobot(updatedState)) {
        queue.add(blueprint.purchaseOreRobot(blueprint.simulateMinute(updatedState)));
      }
      queue.add(blueprint.simulateMinute(updatedState));
    }
    return best.get(minutes);
  }

  public static class Cost {
    Long ore;
    Long clay;
    Long obsidian;
    Long geode;

    public Cost(Long ore, Long clay, Long obsidian, Long geode) {
      this.ore = ore;
      this.clay = clay;
      this.obsidian = obsidian;
      this.geode = geode;
    }

    //    @Override
    //    public String toString() {
    //      StringBuilder builder = new StringBuilder();
    //      if (ore != null) {
    //        builder.append(ore).append(" ore");
    //      }
    //      if (clay != null) {
    //        if (ore != null) {
    //          builder.append(" and ");
    //        }
    //        builder.append(clay).append(" clay");
    //      }
    //      if (obsidian != null) {
    //        if (ore != null || clay != null) {
    //          builder.append(" and ");
    //        }
    //        builder.append(obsidian).append(" obsidian");
    //      }
    //      if (geode != null) {
    //        if (ore != null || clay != null || obsidian != null) {
    //          builder.append(" and ");
    //        }
    //        builder.append(geode).append(" geode");
    //      }
    //      return builder.toString();
    //    }
  }

  public static class State {
    long ore;
    long clay;
    long obsidian;
    long geodes;
    long oreRobots;
    long clayRobots;
    long obsidianRobots;
    long geodeRobots;
    long time;

    public State() {
      this.ore = 0;
      this.oreRobots = 1;
      this.clay = 0;
      this.clayRobots = 0;
      this.obsidian = 0;
      this.obsidianRobots = 0;
      this.geodes = 0;
      this.geodeRobots = 0;
      this.time = 0;
    }

    public State(
        long ore,
        long clay,
        long obsidian,
        long geodes,
        long oreRobots,
        long clayRobots,
        long obsidianRobots,
        long geodeRobots,
        long time) {
      this.ore = ore;
      this.clay = clay;
      this.obsidian = obsidian;
      this.geodes = geodes;
      this.oreRobots = oreRobots;
      this.clayRobots = clayRobots;
      this.obsidianRobots = obsidianRobots;
      this.geodeRobots = geodeRobots;
      this.time = time;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      State state = (State) o;
      return ore == state.ore
          && clay == state.clay
          && obsidian == state.obsidian
          && geodes == state.geodes
          && oreRobots == state.oreRobots
          && clayRobots == state.clayRobots
          && obsidianRobots == state.obsidianRobots
          && geodeRobots == state.geodeRobots
          && time == state.time;
    }

    @Override
    public int hashCode() {
      return Objects.hash(
          ore, clay, obsidian, geodes, oreRobots, clayRobots, obsidianRobots, geodeRobots, time);
    }
  }

  public static class Blueprint {
    Cost oreCost;
    Cost clayCost;
    Cost obsidianCost;
    Cost geodeCost;

    public Blueprint(Cost oreCost, Cost clayCost, Cost obsidianCost, Cost geodeCost) {
      this.oreCost = oreCost;
      this.clayCost = clayCost;
      this.obsidianCost = obsidianCost;
      this.geodeCost = geodeCost;
    }

    public boolean canPurchaseOreRobot(State currentState) {
      return currentState.ore >= oreCost.ore;
    }

    public State purchaseOreRobot(State currentState) {
      return new State(
          currentState.ore - oreCost.ore,
          currentState.clay,
          currentState.obsidian,
          currentState.geodes,
          currentState.oreRobots + 1,
          currentState.clayRobots,
          currentState.obsidianRobots,
          currentState.geodeRobots,
          currentState.time);
    }

    public boolean canPurchaseClayRobot(State currentState) {
      return currentState.ore >= clayCost.ore;
    }

    public State purchaseClayRobot(State currentState) {
      return new State(
          currentState.ore - clayCost.ore,
          currentState.clay,
          currentState.obsidian,
          currentState.geodes,
          currentState.oreRobots,
          currentState.clayRobots + 1,
          currentState.obsidianRobots,
          currentState.geodeRobots,
          currentState.time);
    }

    public boolean canPurchaseObsidianRobot(State currentState) {
      return currentState.ore >= obsidianCost.ore && currentState.clay >= obsidianCost.clay;
    }

    public State purchaseObsidianRobot(State currentState) {
      return new State(
          currentState.ore - obsidianCost.ore,
          currentState.clay - obsidianCost.clay,
          currentState.obsidian,
          currentState.geodes,
          currentState.oreRobots,
          currentState.clayRobots,
          currentState.obsidianRobots + 1,
          currentState.geodeRobots,
          currentState.time);
    }

    public boolean canPurchaseGeodeRobot(State currentState) {
      return currentState.ore >= geodeCost.ore && currentState.obsidian >= geodeCost.obsidian;
    }

    public State purchaseGeodeRobot(State currentState) {
      return new State(
          currentState.ore - geodeCost.ore,
          currentState.clay,
          currentState.obsidian - geodeCost.obsidian,
          currentState.geodes,
          currentState.oreRobots,
          currentState.clayRobots,
          currentState.obsidianRobots,
          currentState.geodeRobots + 1,
          currentState.time);
    }

    public State simulateMinute(State currentState) {
      return new State(
          currentState.ore + currentState.oreRobots,
          currentState.clay + currentState.clayRobots,
          currentState.obsidian + currentState.obsidianRobots,
          currentState.geodes + currentState.geodeRobots,
          currentState.oreRobots,
          currentState.clayRobots,
          currentState.obsidianRobots,
          currentState.geodeRobots,
          currentState.time + 1);
    }

    //    @Override
    //    public String toString() {
    //      return "Blueprint{"
    //          + "oreCost="
    //          + oreCost
    //          + ", clayCost="
    //          + clayCost
    //          + ", obsidianCost="
    //          + obsidianCost
    //          + ", geodeCost="
    //          + geodeCost
    //          + '}';
    //    }
  }
}
