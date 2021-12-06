package ch.adventofcode.days;

import ch.adventofcode.AOCRiddle;

import java.util.Arrays;

public class Day06 extends AOCRiddle {
    public Day06(String in, int part) {
        super(in, part);
        parseInput();
    }

    private long[] lanternFish;

    @Override
    protected String solve1() {
        simulate(80);
        return String.valueOf(Arrays.stream(lanternFish).sum());
    }

    @Override
    protected String solve2() {
        simulate(256);
        return String.valueOf(Arrays.stream(lanternFish).sum());
    }

    private void simulate(int days) {
        for (int i = 0; i < days; i++) {
            long newFish = lanternFish[0];
            System.arraycopy(lanternFish, 1, lanternFish, 0, lanternFish.length - 1);
            lanternFish[lanternFish.length - 1] = newFish;
            lanternFish[6] += newFish;
        }
    }


    private void parseInput() {
        lanternFish = new long[9];
        for (int i : Arrays.stream(getInput().split(",")).mapToInt(Integer::parseInt).toArray()) {
            lanternFish[i]++;
        }
    }
}
