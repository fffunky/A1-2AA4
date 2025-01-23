package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

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

        Path path = new Path();
        path.addInstruction(new Instruction("9F"));
        path.addInstruction(new Instruction("3R"));
        path.addInstruction(new Instruction("5F"));
        path.addInstruction(new Instruction("2L"));
        path.addInstruction(new Instruction("4F"));

        logger.info(path.Factorized());
        logger.info(path.Canonical());

        logger.info("**** Computing path");
        logger.info("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}
