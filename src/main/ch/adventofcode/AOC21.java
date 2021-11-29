package ch.adventofcode;


import ch.adventofcode.days.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

public class AOC21 {

    public static void main(String[] args) {
        int exercise = 0;
        int part = 0;
        boolean testing = false;
        if (args.length > 3 || args.length < 2) {
            usage();
        } else {
            try {
                exercise = Integer.parseInt(args[0]);
                part = Integer.parseInt(args[1]);
                if (args.length == 3) {
                    testing = Boolean.parseBoolean(args[2]);
                }
                if (exercise > 25 || exercise < 1 || part < 1 || part > 2) {
                    usage();
                }
            } catch (Exception e) {
                e.printStackTrace();
                usage();
            }
        }
        doExercise(exercise, part, testing);
    }

    private static void usage() {
        System.out.println("usage: exercise part testing");
        System.out.println("usage:  [0-25]  [1,2]  [1,0]?");
        System.exit(-1);
    }

    private static void doExercise(int exercise, int part, boolean test) {
        String in = "";
        try {
            in = readFile("input" + exercise + ".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        AOCRiddle riddle = getRiddle(exercise, part, in);
        System.out.println(riddle.solve());
    }

    private static AOCRiddle getRiddle(int exercise, int part, String in) {
        AOCRiddle riddle = null;
        switch (exercise) {
            case (1) -> riddle = new Day01(in, part);
            case (2) -> riddle = new Day02(in, part);
            case (3) -> riddle = new Day03(in, part);
            case (4) -> riddle = new Day04(in, part);
            case (5) -> riddle = new Day05(in, part);
            case (6) -> riddle = new Day06(in, part);
            case (7) -> riddle = new Day07(in, part);
            case (8) -> riddle = new Day08(in, part);
            case (9) -> riddle = new Day09(in, part);
            case (10) -> riddle = new Day10(in, part);
            case (11) -> riddle = new Day11(in, part);
            case (12) -> riddle = new Day12(in, part);
            case (13) -> riddle = new Day13(in, part);
            case (14) -> riddle = new Day14(in, part);
            case (15) -> riddle = new Day15(in, part);
            case (16) -> riddle = new Day16(in, part);
            case (17) -> riddle = new Day17(in, part);
            case (18) -> riddle = new Day18(in, part);
            case (19) -> riddle = new Day19(in, part);
            case (20) -> riddle = new Day20(in, part);
            case (21) -> riddle = new Day21(in, part);
            case (22) -> riddle = new Day22(in, part);
            case (23) -> riddle = new Day23(in, part);
            case (24) -> riddle = new Day24(in, part);
            case (25) -> riddle = new Day25(in, part);
            default -> {
            }
        }
        return riddle;
    }

    private static String readFile(String fileName) throws IOException {
        File file = new File(
                Objects.requireNonNull(AOC21.class.getClassLoader().getResource(fileName)).getFile()
        );
        return Files.readString(file.toPath());
    }

}
