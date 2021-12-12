package ch.adventofcode.days;

import ch.adventofcode.AOCRiddle;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Day11 extends AOCRiddle {
    public Day11(String in, int part) {
        super(in, part);
        parseInput();
    }

    private static final int SIZE = 10;
    private int[][] dumboOctopuses;

    @Override
    protected String solve1() {
        return String.valueOf(blinkOctopuses());
    }

    @Override
    protected String solve2() {
        int step = 0;
        while(true){
            Set<Point> flashed = new HashSet<>();
            // energy level up
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    dumboOctopuses[i][j]++;
                    if (dumboOctopuses[i][j] > 9) {
                        dumboOctopuses[i][j] = 0;
                        flashed.add(new Point(j, i));
                    }
                }
            }

            getFurtherBlinks(flashed, flashed);
            step++;
            if(flashed.size() == SIZE*SIZE){
                return String.valueOf(step);
            }
        }
    }

    private int blinkOctopuses() {
        int blinks = 0;
        for (int step = 0; step < 100; step++) {

            Set<Point> flashed = new HashSet<>();
            // energy level up
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    dumboOctopuses[i][j]++;
                    if (dumboOctopuses[i][j] > 9) {
                        dumboOctopuses[i][j] = 0;
                        flashed.add(new Point(j, i));
                    }
                }
            }

            blinks += getFurtherBlinks(flashed, flashed);
        }

        return blinks;
    }

    private int getFurtherBlinks(Set<Point> total, Set<Point> current) {
        Set<Point> nextFlashed = new HashSet<>();
        for (Point p : current) {
            for (int y = Math.max(0, p.y - 1); y <= Math.min(p.y + 1, SIZE - 1); y++) {
                for (int x = Math.max(0, p.x - 1); x <= Math.min(p.x + 1, SIZE - 1); x++) {
                    if (!total.contains(new Point(x, y)) && !nextFlashed.contains(new Point(x, y))) {
                        dumboOctopuses[y][x]++;
                        if (dumboOctopuses[y][x] > 9) {
                            dumboOctopuses[y][x] = 0;
                            nextFlashed.add(new Point(x, y));
                        }
                    }
                }
            }
        }
        total.addAll(nextFlashed);
        if (!nextFlashed.isEmpty()) {
            return getFurtherBlinks(total, nextFlashed);
        }
        return total.size();
    }

    private void parseInput() {
        dumboOctopuses = new int[SIZE][SIZE];
        int i = 0;
        for (String s : getInput().split("\n")) {
            int j = 0;
            for (char c : s.toCharArray()) {
                dumboOctopuses[i][j] = Character.getNumericValue(c);
                j++;
            }
            i++;
        }
    }

}
