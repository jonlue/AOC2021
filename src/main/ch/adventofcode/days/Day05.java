package ch.adventofcode.days;

import ch.adventofcode.AOCRiddle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day05 extends AOCRiddle {
    public Day05(String in, int part) {
        super(in,part);
        parseInput();
        location = new int[1000][1000];
    }

    private List<int[]> ventLines;
    private final int[][] location;

    @Override
    protected String solve1() {
        drawLines(false);
        return String.valueOf(countOverlaps());
    }

    @Override
    protected String solve2() {
        drawLines(true);
        return String.valueOf(countOverlaps());
    }

    private void drawLines(boolean drawDiagonal) {
        for(int[] ins : ventLines){
            drawLine(ins, drawDiagonal);
        }
    }

    private void drawLine(int[] line, boolean drawDiagonal) {
        if(!drawDiagonal){
            if(line[0] != line[2] && line[1] != line[3]){
                return;
            }
        }


        if(line[1] == line[3]) {
            sortLine(line);
            for (int x = line[0]; x <= line[2]; x++) {
                location[line[1]][x] += 1;
            }
        }else if(line[0] == line[2]){
            sortLine(line);
            for (int y = line[1]; y <= line[3]; y++) {
                location[y][line[0]] += 1;
            }
        }else{
            drawDiagonalLine(line);
        }
    }

    private void drawDiagonalLine(int[] line) {
        int y = line[1];
        int incrementor = 1;
        if(line[1] > line[3]){
            incrementor = -1;
        }

        if(line[0] < line[2]) {
            for (int x = line[0]; x <= line[2]; x++) {
                location[y][x] += 1;
                y+= incrementor;
            }
        }else {
            for (int x = line[0]; x >= line[2]; x--) {
                location[y][x] += 1;
                y+= incrementor;
            }
        }
    }

    private void sortLine(int[] line) {
        if(line[0] > line[2]) {
            int t = line[0];
            line[0] = line[2];
            line[2] = t;
        }

        if(line[1] > line[3]){
            int t = line[1];
            line[1] = line[3];
            line[3] = t;
        }
    }

    private int countOverlaps() {
        return Arrays.stream(location).mapToInt(line -> (int) Arrays.stream(line).filter(n -> n >= 2).count()).sum();
    }

    private void parseInput() {
        ventLines = new ArrayList<>();
        String in = getInput().replaceAll(" -> ", ",");
        for(String s : in.split("\n")){
            ventLines.add(Arrays.stream(s.split(",")).mapToInt(Integer::parseInt).toArray());
        }
    }

}
