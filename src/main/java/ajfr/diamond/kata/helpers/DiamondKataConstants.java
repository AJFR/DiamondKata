package ajfr.diamond.kata.helpers;

import java.util.List;

public class DiamondKataConstants {
    
    private DiamondKataConstants() {}

    public static final List<String> EXIT_STRINGS = List.of("exit", "q", "quit");
    public static final String CONTINUOUS_INPUT_MESSAGE =
            "Please input a single uppercase character from A - Z. Type exit to exit.";
    public static final String ERROR_MESSAGE =
            "Unable to process the input. With message [{}] and input [{}].";
    public static final String ERROR_INPUT_MESSAGE =
            "Please try again with correct input. Any uppercase character from A - Z is acceptable.";
}
