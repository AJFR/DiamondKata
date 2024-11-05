package ajfr.diamond.kata.impls;

import ajfr.diamond.kata.algos.DiamondKataAlgorithmLinear;
import ajfr.diamond.kata.interfaces.LineGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static ajfr.diamond.kata.test.TestDataConstants.A_TO_Z;
import static ajfr.diamond.kata.test.TestDataConstants.CHARACTER_WIDTH_MAP;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DiamondKataAlgorithmLinearTest {

    private static final LineGenerator lineGenerator = mock(LineGenerator.class);
    private final DiamondKataAlgorithmLinear diamondKataLinear = new DiamondKataAlgorithmLinear(lineGenerator, CHARACTER_WIDTH_MAP);

    @BeforeAll
    public static void before() {
        when(lineGenerator.generateLine(any(Character.class), anyInt(), anyInt())).thenAnswer(
                invocationOnMock -> invocationOnMock.getArguments()[0].toString()
        );
    }

    @ParameterizedTest
    @ValueSource(
            //unfortunately since arrays (i.e. char[]) are mutable even if static and final we have to provide full list like this.
            chars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                    'U', 'V', 'W', 'X', 'Y', 'Z'}
    )
    void shouldReturnCorrectlyForAllPossibleInputs(char c) {
        assertEquals(createExpectedOutput(c), diamondKataLinear.diamondKata(c));
    }

    private List<String> createExpectedOutput(char input) {
        List<String> expectedOutput = new ArrayList<>();
        for(Character c : A_TO_Z) {
            if (c == input) {
                expectedOutput.add(c.toString());
                break;
            }
            expectedOutput.add(c.toString());
        }
        return expectedOutput;
    }

}