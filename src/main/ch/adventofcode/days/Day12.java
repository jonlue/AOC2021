package ch.adventofcode.days;

import ch.adventofcode.AOCRiddle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Day12 extends AOCRiddle {
    public Day12(String in, int part) {
        super(in, part);
        parseInput();
    }

    private static final String START = "start";
    private static final String GOAL = "end";
    private Map<String, List<String>> path;

    @Override
    protected String solve1() {
        return String.valueOf(countExits(START, new HashMap<>(), 1));
    }

    @Override
    protected String solve2() {
        Map<String, Integer> map = new HashMap<>();
        return String.valueOf(countExits(START, new HashMap<>(), 2));
    }

    private int countExits(String position, Map<String, Integer> visited, int times) {
        int count = 0;
        if (position.equals(GOAL)) {
            System.out.println(visited);
            return 1;
        }

        if (Character.isLowerCase(position.charAt(0))) {
            visited.putIfAbsent(position, 0);
            visited.put(position, visited.get(position) + 1);
        }

        for (String p : path.get(position)) {
            if (p.equals(START)) continue;
            visited.putIfAbsent(p, 0);
            if (Character.isUpperCase(p.charAt(0)) || !visited.containsValue(times) || (visited.containsValue(times) && visited.get(p) == 0)) {
                count += countExits(p, new HashMap<>(visited), times);
            }
        }
        return count;
    }

    private void parseInput() {
        path = new HashMap<>();
        for (String s : getInput().split("\n")) {
            String[] t = s.split("-");
            path.putIfAbsent(t[0], new ArrayList<>());
            List<String> temp = path.get(t[0]);
            temp.add(t[1]);
            path.put(t[0], temp);

            path.putIfAbsent(t[1], new ArrayList<>());
            temp = path.get(t[1]);
            temp.add(t[0]);
            path.put(t[1], temp);
        }
    }

}
