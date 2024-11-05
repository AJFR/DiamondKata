package ajfr.diamond.kata.algos;

import ajfr.diamond.kata.interfaces.LineGenerator;
import ajfr.diamond.kata.interfaces.DiamondKataAlgorithm;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@ConditionalOnProperty(value = "use.recursive.algo", havingValue = "false")
public class DiamondKataAlgorithmLinear implements DiamondKataAlgorithm {

    private final LineGenerator lineGenerator;
    private final LinkedHashMap<Character, Integer> characterToWidthMap;

    public DiamondKataAlgorithmLinear(LineGenerator lineGenerator, LinkedHashMap<Character, Integer> characterToWidthMap) {
        this.lineGenerator = lineGenerator;
        this.characterToWidthMap = characterToWidthMap;
    }

    public List<String> diamondKata(Character c) {
        int maxWidth = characterToWidthMap.get(c);
        ArrayList<String> lines = new ArrayList<>();
        for (Map.Entry<Character, Integer> kv : characterToWidthMap.entrySet()) {
            if (kv.getValue() <= maxWidth) {
                lines.add(lineGenerator.generateLine(kv.getKey(), kv.getValue(), maxWidth));
            } else {
                break;
            }
        }
        return lines;
    }

}
