package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();
    private static final Options options = new Options();
    private static final CommandLineParser parser = new DefaultParser();
    private static String filePath = null;
    private static String pathString = null;

    public static String readFile(String fileName) {
        try {
            logger.info("**** Reading the maze from file {}", fileName);
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            StringBuilder out = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                for (int idx = 0; idx < line.length(); idx++) {
                    out.append(line.charAt(idx));
                }
                out.append(System.lineSeparator());
            }
            return out.toString();

        } catch(Exception e) {
            logger.error("Bad filepath: {}. Exiting program...", fileName);
            System.exit(1);
        }

        return null;
    }

    // set options for CLI parser
    public static void initCLI(String[] args) {
        options.addOption("i", true, "name of input file containing maze");
        options.addOption("p", true, "takes a path as input to verify its a legit one.");

        try {
            CommandLine cmd = parser.parse(options, args);
            filePath = cmd.getOptionValue("i");
            pathString = cmd.getOptionValue("p");
        } catch (ParseException e) {
            // This means a flag was added but no argument passed
            logger.error(e.getMessage());
            System.exit(2);
        }
    }


    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");


        initCLI(args);
        String fileInput = readFile(filePath);
        Maze maze = new Maze(fileInput);
        System.out.println(maze);

        if (pathString == null) {
            // no -p flag
            logger.info("**** Computing path\n");
            MazeRunner mr = new MazeRunner(maze);
            Path path = mr.runPathfinder();
            System.out.printf("Canonical: %s\n", path.Canonical());
            System.out.printf("Factorized: %s\n\n", path.Factorized());
        } else {
            // check path given a -p flag
            Path path = new Path();
            path.addInstruction("1F");
            path.addInstruction("1L");
            path.addInstruction("1F");
            path.addInstruction("1R");
            path.addInstruction("2F");
            path.addInstruction("1L");
            path.addInstruction("6F");
            path.addInstruction("1R");
            path.addInstruction("4F");
            path.addInstruction("1R");
            path.addInstruction("2F");
            path.addInstruction("1L");
            path.addInstruction("2F");
            path.addInstruction("1R");
            path.addInstruction("2F");
            path.addInstruction("1L");
            path.addInstruction("1F");

            String factorized = "F L F R 2F L 6F R 4F R 2F L 2F R 2F R";
            String canonical = "F L F R FF L FFFFFF R FFFF R FF L FF R FF R FF L F";

            String simple = "FFFF";

            Path f = Path.pathFromString(canonical);

            System.out.println(f);

        }

        logger.info("** End of MazeRunner");
    }
}
