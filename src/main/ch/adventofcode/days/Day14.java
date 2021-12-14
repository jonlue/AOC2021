package ch.adventofcode.days;

import ch.adventofcode.AOCRiddle;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Day14 extends AOCRiddle {
    public Day14(String in, int part) {
        super(in, part);
        parseInput();
    }

    private Map<String, String> rules;
    private Map<String, Long> pairs;
    private Map<String, Long> charCounter;

    @Override
    protected String solve1() {
        transform(10);
        return String.valueOf(getScore());
    }

    @Override
    protected String solve2() {
        transform(40);
        return String.valueOf(getScore());
    }

    private void transform(int times) {
        for (int i = 0; i < times; i++) {
            Map<String, Long> newMap = rules.keySet().stream().collect(Collectors.toMap(s -> s, s -> 0L, (a, b) -> b));
            pairs.forEach((key, value) -> {
                if (value != 0) {
                    // add chars
                    String newChar = rules.get(key);
                    charCounter.put(newChar, charCounter.get(newChar) + value);

                    // add first pair
                    String firstKey = key.charAt(0) + newChar;
                    newMap.put(firstKey, newMap.get(firstKey) + value);

                    String secondKey = newChar + key.charAt(1);
                    newMap.put(secondKey, newMap.get(secondKey) + value);
                }
            });
            pairs.clear();
            pairs.putAll(newMap);
        }
    }

    private long getScore() {
        return Collections.max(charCounter.values()) - Collections.min(charCounter.values());
    }

    private void parseInput() {
        rules = new HashMap<>();
        String[] t = getInput().split("\n\n");
        String polymer = t[0];
        for (String s : t[1].split("\n")) {
            String[] pair = s.split(" -> ");
            rules.put(pair[0], pair[1]);
        }

        //count chars in polymer
        charCounter = rules.values().stream().collect(Collectors.toMap(s -> s, s -> 0L, (a, b) -> b));
        for (char c : polymer.toCharArray()) {
            String str = String.valueOf(c);
            charCounter.put(str, charCounter.get(str) + 1);
        }

        // fill pairs with possible keys
        pairs = rules.keySet().stream().collect(Collectors.toMap(s -> s, s -> 0L, (a, b) -> b));
        for (int i = 1; i < polymer.length(); i++) {
            String str = polymer.substring(i - 1, i + 1);
            pairs.put(str, pairs.get(str) + 1);
        }
    }

}
