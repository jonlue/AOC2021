package ch.adventofcode.days;

import ch.adventofcode.AOCRiddle;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Day09 extends AOCRiddle {
    public Day09(String in, int part) {
        super(in, part);
        parseInput();
    }

    int[][] heightMap;
    private static final int SIZE = 100;

    @Override
    protected String solve1() {
        List<Point> lowest = findLowestPoints();

        int sum = 0;
        for (Point p : lowest) {
            sum += heightMap[p.x][p.y] + 1;
        }

        return String.valueOf(sum);
    }

    @Override
    protected String solve2() {
        List<Point> lowest = findLowestPoints();
        List<Integer> basinSizes = new ArrayList<>();
        for (Point p : lowest) {
            basinSizes.add(getBasin(p));
        }
        Collections.sort(basinSizes);
        int res = basinSizes.get(basinSizes.size()-1);
        res *= basinSizes.get(basinSizes.size()-2);
        res *= basinSizes.get(basinSizes.size()-3);
        return String.valueOf(res);
    }

    private Integer getBasin(Point start) {
        Queue<Point> toVisit = new ArrayDeque<>();
        Set<Point> visited = new HashSet<>();

        toVisit.add(start);
        visited.add(start);

        while (!toVisit.isEmpty()) {
            Point pos = toVisit.poll();

            for (Point p : getNeighbors(pos)) {
                if (!visited.contains(p)) {
                    visited.add(p);
                    toVisit.add(p);
                }
            }
        }
        return visited.size();
    }

    private List<Point> getNeighbors(Point pos) {
        List<Point> neighbors = new ArrayList<>();
        if (pos.x < SIZE - 1 && heightMap[pos.y][pos.x + 1] < 9 && heightMap[pos.y][pos.x + 1] > heightMap[pos.y][pos.x]) {
            neighbors.add(new Point(pos.x + 1, pos.y));
        }
        if (pos.x > 0 && heightMap[pos.y][pos.x - 1] < 9 && heightMap[pos.y][pos.x - 1] > heightMap[pos.y][pos.x]) {
            neighbors.add(new Point(pos.x - 1, pos.y));
        }
        if (pos.y < SIZE-1 && heightMap[pos.y + 1][pos.x] < 9 && heightMap[pos.y + 1][pos.x] > heightMap[pos.y][pos.x]) {
            neighbors.add(new Point(pos.x, pos.y + 1));
        }
        if (pos.y > 0 && heightMap[pos.y - 1][pos.x] < 9 && heightMap[pos.y - 1][pos.x] > heightMap[pos.y][pos.x]) {
            neighbors.add(new Point(pos.x, pos.y - 1));
        }
        return neighbors;
    }

    private List<Point> findLowestPoints() {
        List<Point> res = new ArrayList<>();
        for (int i = 0; i < heightMap.length; i++) {
            for (int j = 0; j < heightMap[0].length; j++) {
                if (lowestOfAdjacent(i, j)) {
                    res.add(new Point(j, i));
                }
            }
        }
        return res;
    }

    private boolean lowestOfAdjacent(int y, int x) {
        int num = heightMap[y][x];
        boolean same = false;

        if (y != 0) {
            same = heightMap[y - 1][x] == num;
            if (heightMap[y - 1][x] < num) return false;
        }
        if (y != SIZE - 1) {
            if (!same) same = heightMap[y + 1][x] == num;
            if (heightMap[y + 1][x] < num) return false;
        }
        if (x != 0) {
            if (!same) same = heightMap[y][x - 1] == num;
            if (heightMap[y][x - 1] < num) return false;
        }
        if (x != SIZE - 1) {
            if (!same) same = heightMap[y][x + 1] == num;
            if (heightMap[y][x + 1] < num) return false;
        }
        return !same;
    }

    private void parseInput() {
        heightMap = new int[SIZE][SIZE];
        int i = 0;
        for (String s : getInput().split("\n")) {
            int j = 0;
            for (char c : s.toCharArray()) {
                heightMap[i][j] = Character.getNumericValue(c);
                j++;
            }
            i++;
        }
    }

}
