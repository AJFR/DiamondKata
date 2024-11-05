package ajfr.diamond.kata.test;

import java.util.LinkedHashMap;
import java.util.List;

public class TestDataConstants {

    private TestDataConstants() {}

    public static final List<Character> A_TO_Z = List.of(
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
            'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
            );

    public static final LinkedHashMap<Character, Integer> CHARACTER_WIDTH_MAP = characterToWidthMap();

    private static LinkedHashMap<Character, Integer> characterToWidthMap() {
        int widthCount = 1;
        LinkedHashMap<Character, Integer> characterToWidthMap = new LinkedHashMap<>();
        for (Character c : A_TO_Z) {
            characterToWidthMap.put(c, widthCount);
            widthCount = widthCount + 2;
        }
        return characterToWidthMap;
    }

}
