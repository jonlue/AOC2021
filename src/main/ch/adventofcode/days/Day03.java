package ch.adventofcode.days;

import ch.adventofcode.AOCRiddle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day03 extends AOCRiddle {
    public Day03(String in, int part) {
        super(in, part);
        parseInput();
        createBitmasks();
    }

    private List<Integer> binaries;
    private List<Integer> bitmasks;

    private int size;

    @Override
    protected String solve1() {
        int gamma = 0;
        int epsilon = 0;

        for (int mask : bitmasks) {
            if (mostCommonOne(binaries, mask)) {
                gamma += mask;
            } else {
                epsilon += mask;
            }
        }
        return String.valueOf(gamma * epsilon);
    }

    private boolean mostCommonOne(List<Integer> binaries, int mask) {
        return binaries.stream().filter(n -> (n & mask) == mask).count() >= Math.ceil((double) binaries.size() / 2);
    }

    @Override
    protected String solve2() {
        Collections.reverse(bitmasks);
        List<Integer> oxygen = new ArrayList<>(binaries);
        List<Integer> co2 = new ArrayList<>(binaries);

        for (int mask : bitmasks) {
            if (oxygen.isEmpty() || co2.isEmpty()) {
                return String.valueOf(-1);
            }

            if (oxygen.size() != 1) {
                oxygen = filter(oxygen.stream(), mask, mostCommonOne(oxygen, mask));
            }
            if (co2.size() != 1) {
                co2 = filter(co2.stream(), mask, !mostCommonOne(co2, mask));
            }
        }
        return String.valueOf(oxygen.get(0) * co2.get(0));
    }

    private List<Integer> filter(Stream<Integer> stream, int mask, boolean mostCommon) {
        if (mostCommon) {
            return stream.filter(n -> (n & mask) == mask).collect(Collectors.toList());
        } else {
            return stream.filter(n -> (~n & mask) == mask).collect(Collectors.toList());
        }
    }

    private void parseInput() {
        binaries = new ArrayList<>();
        String[] ins = getInput().split("\n");
        size = ins[0].length();
        for (String s : ins) {
            binaries.add(Integer.parseInt(s, 2));
        }
    }

    private void createBitmasks() {
        bitmasks = new ArrayList<>();
        int mask = 1;
        for (int i = 0; i < size; i++) {
            bitmasks.add(mask);
            mask <<= 1;
        }
    }
}
