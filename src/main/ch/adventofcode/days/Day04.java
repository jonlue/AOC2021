package ch.adventofcode.days;

import ch.adventofcode.AOCRiddle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day04 extends AOCRiddle {
    public Day04(String in, int part) {
        super(in, part);
        parseInput();
    }

    private List<Integer> numbers;
    private List<Integer[][]> boards;

    @Override
    protected String solve1() {
        for (int i = 4; i < numbers.size(); i++) {
            Set<Integer> set = new HashSet<>(numbers.subList(0, i+1));
            for (Integer[][] b : boards) {
                if (hasBoardWon(b, set)) {
                    return String.valueOf(getWinningScore(b, set, i));
                }
            }
        }
        return String.valueOf(-1);
    }

    @Override
    protected String solve2() {
        for (int i = 4; i < numbers.size(); i++) {
            Set<Integer> set = new HashSet<>(numbers.subList(0, i+1));
            if(boards.size() == 1){
                if(hasBoardWon(boards.get(0),set)){
                    return String.valueOf(getWinningScore(boards.get(0), set, i));
                }
                continue;
            }
            boards = boards.stream().filter(b -> !hasBoardWon(b,set)).collect(Collectors.toList());
        }
        return String.valueOf(-1);
    }

    private boolean hasBoardWon(Integer[][] board, Set<Integer> numberSet) {

        //row
        boolean found;
        for (Integer[] i : board) {
            found = true;
            for (int j : i) {
                if (!numberSet.contains(j)) {
                    found = false;
                    break;
                }
            }
            if (found) return true;
        }


        //col
        for (int j = 0; j < board[0].length; j++) {
            found = true;
            for (Integer[] integers : board) {
                if (!numberSet.contains(integers[j])) {
                    found = false;
                    break;
                }
            }
            if (found) return true;
        }
        return false;
    }

    private int getWinningScore(Integer[][] board, Set<Integer> numberSet, int callNumber) {
        int res = 0;
        for (Integer[] i : board) {
            for (int j : i) {
                if (!numberSet.contains(j)) {
                    res += j;
                }
            }
        }
        return res * numbers.get(callNumber);
    }

    private void parseInput() {
        numbers = new ArrayList<>();
        boards = new ArrayList<>();
        String[] in = getInput().split("\n");
        for (String s : in[0].split(",")) {
            numbers.add(Integer.parseInt(s));
        }
        int count = 0;
        Integer[][] board = new Integer[5][5];
        int y = 0;
        for (String s : in) {
            if (count < 2) {
                count++;
                continue;
            }
            if (s.isEmpty()) {
                boards.add(board);
                board = new Integer[5][5];
                y = 0;
                continue;
            }

            int x = 0;
            for (String n : s.split(" ")) {
                if (n.isEmpty()) continue;
                board[y][x] = Integer.parseInt(n);
                x++;
            }
            y++;
        }
        boards.add(board);
    }
}
