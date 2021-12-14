package ch.adventofcode.days;

import ch.adventofcode.AOCRiddle;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day13 extends AOCRiddle {
    public Day13(String in, int part) {
        super(in, part);
        parseInput();
    }

    private Set<Point> points;
    private List<String> folds;

    @Override
    protected String solve1() {
        fold(folds.get(0));
        return String.valueOf(points.size());
    }

    @Override
    protected String solve2() {
        for (String s : folds) {
            fold(s);
        }

        return getPicture();
    }

    private String getPicture() {
        boolean[][] pic = new boolean[6][39];
        for (Point p : points) {
            pic[p.y][p.x] = true;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 39; j++) {
                sb.append(pic[i][j] ? "#" : ".");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private void fold(String fold) {
        String[] temp = fold.split("=");
        int line = Integer.parseInt(temp[1]);
        final boolean isX = temp[0].equals("x");

        Set<Point> toRemove = new HashSet<>();
        Set<Point> toAdd = new HashSet<>();

        for (Point p : points) {
            if (isX) {
                if (p.x > line) {
                    int diff = p.x - line;
                    int newX = line - diff;
                    toRemove.add(p);
                    toAdd.add(new Point(newX, p.y));
                }
            } else {
                if (p.y > line) {
                    int diff = p.y - line;
                    int newY = line - diff;
                    toRemove.add(p);
                    toAdd.add(new Point(p.x, newY));
                }
            }
        }
        points.removeAll(toRemove);
        points.addAll(toAdd);
    }

    private void parseInput() {
        points = new HashSet<>();
        folds = new ArrayList<>();
        String[] temp = getInput().split("\n\n");
        for (String s : temp[0].split("\n")) {
            String[] n = s.split(",");
            points.add(new Point(Integer.parseInt(n[0]), Integer.parseInt(n[1])));
        }

        for (String s : temp[1].split("\n")) {
            folds.add(s.replaceAll("fold along ", ""));
        }
    }

}
