package ajfr.diamond.kata.helpers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VisibleWhitespaceLineGeneratorTest {

    private final VisibleWhitespaceLineGenerator visibleWhitespaceLineGenerator = new VisibleWhitespaceLineGenerator();

    @Test
    void shouldReturnJustCharacterWhenCharacterAndMaxWidthIsOne() {
        Character input = 'A';
        int width = 1;
        
        assertEquals(input.toString(), visibleWhitespaceLineGenerator.generateLine(input, width, width));
    }

    @Test
    void shouldReturnTwoCharactersSeperatedByPaddingWhenCharacterAndMaxWidthIsEqual() {
        Character input = 'D';
        int width = 5;
        
        assertEquals(input + "___" + input, visibleWhitespaceLineGenerator.generateLine(input, width, width));
    }

    @Test
    void shouldReturnOneCharacterWithOuterPaddingWhenCharacterWidthIsOneAndMaxWidthIsGreaterThan() {
        Character input = 'A';
        int characterWidth = 1;
        int maxWidth = 5;
        
        assertEquals("__" + input + "__", visibleWhitespaceLineGenerator.generateLine(input, characterWidth, maxWidth));
    }

    @Test
    void shouldReturnTwoCharacterWithOuterPaddingWhenCharacterWidthIsGreaterThanOneAndMaxWidthIsGreaterThan() {
        Character input = 'C';
        int characterWidth = 3;
        int maxWidth = 5;
        
        assertEquals(
                "_" + input + "_" + input + "_",
                visibleWhitespaceLineGenerator.generateLine(input, characterWidth, maxWidth)
        );
    }

}