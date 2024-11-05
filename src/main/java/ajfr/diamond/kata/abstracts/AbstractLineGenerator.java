package ajfr.diamond.kata.abstracts;

import ajfr.diamond.kata.interfaces.LineGenerator;

public abstract class AbstractLineGenerator implements LineGenerator {
    
    private final String whitespace;
    
    public AbstractLineGenerator(String whitespace) {
        this.whitespace = whitespace;
    }

    public String generateLine(Character c, int characterWidth, int maxWidth) {
        return generateLineWithPadding(generateCharacterInner(c, characterWidth), characterWidth, maxWidth);
    }

    private String generateCharacterInner(Character c, int characterWidth) {
        if (characterWidth == 1) {
            return c.toString();
        }
        return c.toString() + whitespace.repeat(Math.max(0, characterWidth - 2)) + c;
    }

    private String generateLineWithPadding(String innerCharactersWithoutPadding, int characterWidth, int maxWidth) {
        if (maxWidth == characterWidth) {
            return innerCharactersWithoutPadding;
        }
        int additionalPaddingOnEachSide = (maxWidth - characterWidth) / 2;
        String padding = whitespace.repeat(additionalPaddingOnEachSide);
        return padding + innerCharactersWithoutPadding + padding;
    }
    
}
