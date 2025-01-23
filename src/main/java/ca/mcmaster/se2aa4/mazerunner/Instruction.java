package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Instruction {
    private static final Logger logger = LogManager.getLogger(Instruction.class);
    private List<String> instruction = new ArrayList<>();

        public Instruction(String input) {
            try {
                validateString(input);
                instruction.add(input.substring(0, input.length() - 1)); // add integer value that comes before direction.
                instruction.add(String.valueOf(input.charAt(input.length() - 1))); // add direction stored in final input char.
            } catch (Exception e) {
                logger.error("- Bad Instruction \"{}\": {}", input, e);
            }
        }

        // returns the integer value that comes before the final instruction
        public Integer getFrequency() {
            return Integer.parseInt(instruction.get(0));
        }

        public String getDirection() {
            return instruction.get(1);
        }

        private void validateString(String input) throws Exception {
            if (input == null || input.isEmpty()) {
                throw new Exception("Instruction is null or empty");
            }

            for (int i = 0; i < input.length() - 1; i++) {
                if (!Character.isDigit(input.charAt(i))) {
                    throw new Exception("Expected digit, found " + input.charAt(i));
                }

            }
            if (input.charAt(input.length() - 1) != 'F' && input.charAt(input.length() - 1) != 'L' && input.charAt(input.length() - 1) != 'R') {
                throw new Exception("Last instruction character should be 'F', 'L', or 'R'");
            }
        }

        @Override
        public String toString() {
            return String.join("", instruction);
        }

}
