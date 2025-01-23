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
            logger.error("Bad filepath: {}", fileName);
            System.exit(1);
        }
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



        logger.info("**** Computing path");
        logger.info("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}
