package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();
    private static final Options options = new Options();

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
            logger.error("/!\\ An error has occured /!\\");
            return null;
        }
    }


    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");


        Maze m = new Maze(readFile(args[0]));

        Matrix matrix = m.getMatrix();

        System.out.println(m.toString());

        logger.info("**** Computing path");
        logger.info("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}
