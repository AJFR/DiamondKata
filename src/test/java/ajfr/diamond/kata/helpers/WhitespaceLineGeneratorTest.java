package ajfr.diamond.kata.helpers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WhitespaceLineGeneratorTest {

    private final WhitespaceLineGenerator whitespaceLineGenerator = new WhitespaceLineGenerator();

    @Test
    void shouldReturnJustCharacterWhenCharacterAndMaxWidthIsOne() {
        Character input = 'A';
        int width = 1;
        
        assertEquals(input.toString(), whitespaceLineGenerator.generateLine(input, width, width));
    }

    @Test
    void shouldReturnTwoCharactersSeperatedByPaddingWhenCharacterAndMaxWidthIsEqual() {
        Character input = 'D';
        int width = 5;
        
        assertEquals(input + "   " + input, whitespaceLineGenerator.generateLine(input, width, width));
    }

    @Test
    void shouldReturnOneCharacterWithOuterPaddingWhenCharacterWidthIsOneAndMaxWidthIsGreaterThan() {
        Character input = 'A';
        int characterWidth = 1;
        int maxWidth = 5;
        
        assertEquals("  " + input + "  ", whitespaceLineGenerator.generateLine(input, characterWidth, maxWidth));
    }

    @Test
    void shouldReturnTwoCharacterWithOuterPaddingWhenCharacterWidthIsGreaterThanOneAndMaxWidthIsGreaterThan() {
        Character input = 'C';
        int characterWidth = 3;
        int maxWidth = 5;
        
        assertEquals(
                " " + input + " " + input + " ",
                whitespaceLineGenerator.generateLine(input, characterWidth, maxWidth)
        );
    }

}