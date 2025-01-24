package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;


public class Path {

    private static final Logger logger = LogManager.getLogger();
    private ArrayList<Instruction> instructions;

    public Path() {
        this.instructions = new ArrayList<>();
    }

    public ArrayList<Instruction> getInstructions() {
        return instructions;
    }

    public static Path pathFromString(String s) {
        s = String.join("", s.split(" "));
        Path p = new Path();

        if (s.length() == 0) {
            return new Path();
        }

        int i=0, j=1;
        while (i < j) {
            char left = s.charAt(i);
            char right;
            try {
                right = s.charAt(j);
            } catch (Exception e) {
                right = 'x';
            }
            // cases
            // i = digit, j = char -> take new instruction from substring s[i:j+1]; i++; j=i+1;
            // i = char, j = char -> new instruction = "1" + s[i]; i++; j=i+1;
            // i = char, j = digit -> new instruction = "1" + s[i]; i++; j=i+1;
            // i = digit, j = digit -> j++;
            System.out.println(left + " " + right);

            if (Character.isAlphabetic(left) && Character.isAlphabetic(right)) {
                if (right == 'x') {
                    p.addInstruction("1" + left);
                    i = j + 1; // end loop
                } else {
                    logger.info("New Instruction: {}", "1" + s.substring(i, j));
                    p.addInstruction("1" + s.substring(i, j));
                    i++;
                    j++;
                }
            } else if (Character.isAlphabetic(left) && Character.isDigit(right)) {
                logger.info("New instruction: {}", "1" + s.substring(i, j));
                p.addInstruction("1" + s.substring(i, j));
                i++;
                j++;

            } else if (Character.isDigit(left) && Character.isAlphabetic(right)) {
                logger.info("New Instruction: {}", s.substring(i, j+1));
                p.addInstruction(s.substring(i, j+1));
                i = j + 1;
                j = i + 1;
                if (i > s.length() - 1) {
                    return p;
                }
            } else {
                j++;
            }

        }



        return p;
    }

    public void addInstruction(String instruction) {
        instructions.add(new Instruction(instruction));
    }

    public void addInstruction(Instruction instruction) {
        this.instructions.add(instruction);
    }

    // returns a string of the path in the factorized form
    public String Factorized() {
        StringBuilder factorized = new StringBuilder();

        String canonical = this.Canonical();
        String[] instructions = canonical.split(" ");

        for (String instruction : instructions) {
            factorized.append(instruction.length());
            factorized.append(instruction.charAt(0));
            factorized.append(" ");
        }
        
        return factorized.toString();
    }

    public String Canonical() {
        StringBuilder canonical = new StringBuilder();
        Instruction cur = null;
        Instruction next = null;

        for (int i = 0; i < this.instructions.size() - 1; i++) {
            cur = this.instructions.get(i);
            next = this.instructions.get(i + 1);

            for (int j = 0; j < cur.getFrequency(); j++) {
                canonical.append(cur.getDirection());
            }

            if (!Objects.equals(next.getDirection(), cur.getDirection())) {
                canonical.append(" ");
            }
        }

        canonical.append(next.getDirection());

        return canonical.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Instruction instruction : instructions) {
            sb.append(instruction.toString()).append(" ");
        }
        return sb.toString();
    }
}
