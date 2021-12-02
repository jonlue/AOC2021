package ch.adventofcode.days;

import ch.adventofcode.AOCRiddle;

public class Day02 extends AOCRiddle {
    public Day02(String in, int part) {
        super(in, part);
    }

    @Override
    protected String solve1() {
        int depth = 0;
        int x = 0;
        for(String s : getInput().split("\n")){
            int value = Integer.parseInt(s.split(" ")[1]);
            if(s.startsWith("f")){
                x += value;
            }else if(s.startsWith("d")){
                depth += value;
            }else {
                depth -= value;
            }
        }
        return String.valueOf(x * depth);
    }

    @Override
    protected String solve2() {
        int aim = 0;
        int depth = 0;
        int x = 0;
        for(String s : getInput().split("\n")){
            int value = Integer.parseInt(s.split(" ")[1]);
            if(s.startsWith("f")){
                x += value;
                depth += value * aim;
            }else if(s.startsWith("d")){
                aim += value;
            }else {
                aim -= value;
            }
        }
        return String.valueOf(x * depth);
    }
}
