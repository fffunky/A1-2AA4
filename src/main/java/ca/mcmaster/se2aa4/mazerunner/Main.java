package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.ldap.Control;

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
        options.addOption(Option.builder("i")
                .hasArg()
                .desc("name of input file containing maze")
                .build());
        options.addOption(Option.builder("p")
                .hasArgs()
                .desc("takes a path as input to verify its a legit one.")
                .build());

        try {
            CommandLine cmd = parser.parse(options, args);

            if (!cmd.hasOption("i")) {
                logger.error("Pass in a file using the -i option. Exiting program...");
                System.exit(1);
            }

            filePath = cmd.getOptionValue("i");

            // get remaining arguments following -p
             String[] pathValues = cmd.getOptionValues("p");
             if (pathValues == null) {
                 pathString = null;
             } else {
                 pathString = String.join(" ", pathValues);
             }
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
        MazeBuilder mb = new GraphMazeBuilder(fileInput);
        GraphMaze maze = (GraphMaze) mb.buildMaze();
        GraphControlCentre cc = new GraphControlCentre(maze);

        if (pathString == null) {
            // no -p flag
            logger.info("**** Computing path\n");
            Path path = cc.runPathfinder();

            System.out.printf("\nCanonical: %s\n\n", path.Canonical());
            System.out.printf("Factorized: %s\n\n", path.Factorized());
        } else {
            // check path given a -p flag
            Path p = null;
            try {
                p = Path.pathFromString(pathString);
            } catch (Exception e) {
                logger.error("Bad input path: {}. Exiting program...", fileInput);
                System.exit(1);
            }

            logger.info("**** Following path: {}\n", pathString);

            if ( cc.isValidSolution(p) ) {
                System.out.printf("\nPath '%s' is a valid solution to the maze\n\n", pathString);
            } else {
                System.out.printf("\nPath '%s' is not a valid solution to the maze\n\n", pathString);
            }

        }

        logger.info("** End of MazeRunner");

    }
}
