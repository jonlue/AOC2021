package ch.adventofcode.days;

import ch.adventofcode.AOCRiddle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Day10 extends AOCRiddle {
    public Day10(String in, int part) {
        super(in, part);
        parseInput();
    }

    private List<char[]> in;

    @Override
    protected String solve1() {
        int score = 0;
        for (char[] line : in) {
            Stack<Character> chars = new Stack<>();
            if (isLineCorrupt(chars, line)) {
                score += getCorruptedScore(chars.pop());
            }
        }
        return String.valueOf(score);
    }

    @Override
    protected String solve2() {
        List<Long> score = new ArrayList<>();
        for (char[] line : in) {
            Stack<Character> chars = new Stack<>();
            if (!isLineCorrupt(chars, line)) {
                long lineScore = 0;
                while (!chars.isEmpty()) {
                    lineScore *= 5;
                    lineScore += getIncompleteScore(chars.pop());
                }

                score.add(lineScore);
            }
        }

        Collections.sort(score);
        return String.valueOf(score.get(score.size()/2));
    }

    private boolean isLineCorrupt(Stack<Character> chars, char[] line) {
        for (char c : line) {
            char curr = chars.isEmpty() ? '0' : chars.peek();
            switch (c) {
                case '(', '[', '{', '<' -> chars.push(c);
                default -> {
                    if (!isClosingBracket(curr, c)) {
                        chars.push(c);
                        return true;
                    }
                    chars.pop();
                }
            }
        }
        return false;
    }

    private boolean isClosingBracket(char current, char next) {
        return switch (next) {
            case ')' -> current == '(';
            case ']' -> current == '[';
            case '}' -> current == '{';
            case '>' -> current == '<';
            default -> throw new IllegalArgumentException();
        };
    }

    private int getCorruptedScore(char current) {
        return switch (current) {
            case ')' -> 3;
            case ']' -> 57;
            case '}' -> 1197;
            case '>' -> 25137;
            default -> throw new IllegalArgumentException();
        };
    }

    private int getIncompleteScore(char current) {
        return switch (current) {
            case '(' -> 1;
            case '[' -> 2;
            case '{' -> 3;
            case '<' -> 4;
            default -> throw new IllegalArgumentException();
        };
    }

    private void parseInput() {
        in = new ArrayList<>();
        for (String s : getInput().split("\n")) {
            in.add(s.toCharArray());
        }
    }

}
