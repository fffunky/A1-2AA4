package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

public class Path {

    private ArrayList<Instruction> instructions;

    public Path() {
        this.instructions = new ArrayList<>();
    }

    public void addInstruction(Instruction instruction) {
        this.instructions.add(instruction);
    }

    // returns a string of the path in the factorized form
    public String Factorized() {
        StringBuilder factorized = new StringBuilder();
        for (Instruction instruction : this.instructions) {
            if (instruction.getFrequency() != 1) {
                factorized.append(instruction.getFrequency().toString());
            }
            factorized.append(instruction.getDirection());
            factorized.append(" ");
        }
        return factorized.toString();
    }

    public String Canonical() {
        StringBuilder canonical = new StringBuilder();
        for (Instruction instruction : this.instructions) {
            for (int i = 0; i < instruction.getFrequency(); i++) {
                canonical.append(instruction.getDirection());
            }
            canonical.append(" ");
        }
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
