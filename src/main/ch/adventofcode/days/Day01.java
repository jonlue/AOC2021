package ch.adventofcode.days;

import ch.adventofcode.AOCRiddle;

import java.util.ArrayList;
import java.util.List;


public class Day01 extends AOCRiddle {
    public Day01(String in, int part) {
        super(in, part);
        parseInput();
    }

    private List<Integer> depths;

    @Override
    protected String solve1() {
        return String.valueOf(countPreviousDeeper());
    }

    @Override
    protected String solve2() {
        createSlidingWindow();
        return solve1();
    }

    private void createSlidingWindow() {
        List<Integer> slidingWindow = new ArrayList<>();
        for(int i = 2; i<depths.size(); i++){
            slidingWindow.add(depths.get(i-2) + depths.get(i-1) + depths.get(i));
        }
        depths = slidingWindow;
    }

    private int countPreviousDeeper(){
        int deeper = 0;
        for(int i = 1; i < depths.size(); i++){
            if(depths.get(i-1) < depths.get(i)){
                deeper++;
            }
        }
        return deeper;
    }

    private void parseInput(){
        depths = new ArrayList<>();
        for(String s : getInput().split("\n")){
            depths.add(Integer.parseInt(s));
        }
    }


}
