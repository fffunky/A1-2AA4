package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Path {

    private ArrayList<Instruction> instructions;

    public Path() {
        this.instructions = new ArrayList<>();
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
