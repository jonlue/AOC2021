package ch.adventofcode.days;

import ch.adventofcode.AOCRiddle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day08 extends AOCRiddle {
    public Day08(String in, int part) {
        super(in, part);
        parseInput();
    }

    private List<String[]> allDigits;
    private List<String[]> goalDigits;

    @Override
    protected String solve1() {
        int uniqueNums = 0;
        for (String[] s : goalDigits) {
            uniqueNums += Arrays.stream(s).filter(str -> str.length() == 2 || str.length() == 3 || str.length() == 4 || str.length() == 7).count();
        }
        return String.valueOf(uniqueNums);
    }

    @Override
    protected String solve2() {
        int sum = 0;
        for (int i = 0; i < allDigits.size(); i++) {
            sum += getCode(i);
        }
        return String.valueOf(sum);
    }

    private int getCode(int index) {
        String[] all = allDigits.get(index);
        String[] numbers = new String[10];
        numbers[1] = getNum(all, 2);
        numbers[4] = getNum(all, 4);
        numbers[7] = getNum(all, 3);
        numbers[8] = getNum(all, 7);

        numbers[3] = getThree(all, numbers[1]);
        numbers[9] = getNine(all, numbers[4]);
        numbers[5] = getFiveSix(all, numbers[1], numbers[9], 5);
        numbers[6] = getFiveSix(all, numbers[1], numbers[9], 6);
        numbers[0] = getZero(all, numbers[6], numbers[9]);
        numbers[2] = getTwo(all, numbers[3], numbers[5]);

        return decipherCode(numbers, goalDigits.get(index));
    }

    private int decipherCode(String[] numbers, String[] goal) {
        int multiplier = 1000;
        int result = 0;
        for (String s : goal) {
            for (int i = 0; i < numbers.length; i++) {
                if (s.length() == numbers[i].length() && containsAll(s, numbers[i])) {
                    result += i * multiplier;
                    break;
                }
            }
            multiplier /= 10;
        }
        return result;
    }

    private String getTwo(String[] all, String three, String five) {
        for (String s : all) {
            if (s.length() == 5) {
                if (s.equals(three)) continue;
                if (s.equals(five)) continue;
                return s;
            }
        }
        return "";
    }

    private String getThree(String[] all, String one) {
        for (String s : all) {
            if (s.length() == 5) {
                if (containsAll(s, one)) return s;
            }
        }
        return "";
    }


    private String getZero(String[] all, String six, String nine) {
        for (String s : all) {
            if (s.length() == 6) {
                if (s.equals(six)) continue;
                if (s.equals(nine)) continue;
                return s;
            }
        }
        return "";
    }

    private String getFiveSix(String[] all, String one, String nine, int segments) {
        String diff = replaceAll(nine, one);
        for (String s : all) {
            if (s.length() == segments) {
                if (s.equals(nine)) continue;
                if (containsAll(s, diff)) return s;
            }
        }
        return "";
    }

    private String getNine(String[] all, String four) {
        for (String s : all) {
            if (s.length() == 6 && containsAll(s, four)) {
                return s;
            }
        }
        return "";
    }

    private String getNum(String[] all, int numSegments) {
        return Arrays.stream(all).filter(s -> s.length() == numSegments).findFirst().orElse("");
    }

    private boolean containsAll(String str, String charsToContain) {
        for (char c : charsToContain.toCharArray()) {
            if (!str.contains("" + c)) {
                return false;
            }
        }
        return true;
    }

    private String replaceAll(String str, String toReplace) {
        String res = str;
        for (char c : toReplace.toCharArray()) {
            res = res.replaceAll(String.valueOf(c), "");
        }
        return res;
    }

    private void parseInput() {
        allDigits = new ArrayList<>();
        goalDigits = new ArrayList<>();
        for (String s : getInput().split("\n")) {
            String[] temp = s.split(" \\| ");
            allDigits.add(temp[0].split(" "));
            goalDigits.add(temp[1].split(" "));
        }
    }

}
