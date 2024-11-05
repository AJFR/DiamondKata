package ajfr.diamond.kata.algos;

import ajfr.diamond.kata.interfaces.LineGenerator;
import ajfr.diamond.kata.interfaces.DiamondKataAlgorithm;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
@ConditionalOnProperty(value = "use.recursive.algo", havingValue = "true", matchIfMissing = true)
public class DiamondKataAlgorithmRecursive implements DiamondKataAlgorithm {

    private final LineGenerator lineGenerator;
    private final LinkedHashMap<Character, Integer> characterToWidthMap;
    private final List<Character> aToZ;

    public DiamondKataAlgorithmRecursive(
            LineGenerator lineGenerator, LinkedHashMap<Character, Integer> characterToWidthMap, List<Character> aToZ
    ) {
        this.lineGenerator = lineGenerator;
        this.characterToWidthMap = characterToWidthMap;
        this.aToZ = aToZ;
    }

    public List<String> diamondKata(Character c) {
        int maxWidth = characterToWidthMap.get(c);
        List<String> lines = new ArrayList<>();

        expandAroundTheMiddle(maxWidth, c, lines, 0);

        return lines;
    }

    private void expandAroundTheMiddle(int maxWidth, Character finalCharacter, List<String> result, int index) {
        Character current = aToZ.get(index);
        int characterWidth = characterToWidthMap.get(current);
        String line = lineGenerator.generateLine(current, characterWidth, maxWidth);
        result.add(line);
        if (current != finalCharacter) {
            expandAroundTheMiddle(maxWidth, finalCharacter, result,index + 1);
            result.add(line);
        }
    }



}
