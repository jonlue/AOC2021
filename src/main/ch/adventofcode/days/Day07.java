package ch.adventofcode.days;

import ch.adventofcode.AOCRiddle;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day07 extends AOCRiddle {
    public Day07(String in, int part) {
        super(in, part);
        parseInput();
    }

    private List<Integer> positions;

    @Override
    protected String solve1() {
        return String.valueOf(findMinFuel(false));
    }

    @Override
    protected String solve2() {
        return String.valueOf(findMinFuel(true));
    }

    private long findMinFuel(boolean part2) {
        int min = Collections.min(positions);
        int max = Collections.max(positions);

        long minFuel = Integer.MAX_VALUE;
        for (int i = min; i < max; i++) {
            int finalI = i;
            if (part2) {
                minFuel = Math.min(minFuel, positions.stream().mapToInt(n -> sum(Math.abs(n - finalI))).sum());
            } else {
                minFuel = Math.min(minFuel, positions.stream().mapToInt(n -> Math.abs(n - finalI)).sum());
            }
        }
        return minFuel;
    }

    private int sum(int dist) {
        int sum = 0;
        for (int i = 0; i < dist; i++) {
            sum += (i + 1);
        }
        return sum;
    }

    private void parseInput() {
        positions = Arrays.stream(getInput().split(",")).mapToInt(Integer::parseInt).boxed().toList();
    }


}
