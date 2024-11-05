package ajfr.diamond.kata;

import ajfr.diamond.kata.helpers.SystemExit;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static ajfr.diamond.kata.test.TestData.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith({SpringExtension.class, OutputCaptureExtension.class})
@SpringBootTest(properties = "shoud.run.once=true")
@ActiveProfiles("integrationTest")
public class DiamondKataSingleRunIntegrationTest {

    private final DiamondKataApplication diamondKataApplication;
    private final SystemExit systemExit;

    @Autowired
    public DiamondKataSingleRunIntegrationTest(DiamondKataApplication diamondKataApplication, SystemExit systemExit) {
        this.diamondKataApplication = diamondKataApplication;
        this.systemExit = systemExit;
    }

    @Test
    void whenValidInputProvidedShouldOutputTheCorrectDiamondKata(CapturedOutput output) {
        mockExit(systemExit, 0);
        
        assertThrows(RuntimeException.class, () -> diamondKataApplication.run("C"));

        MatcherAssert.assertThat(output.getOut(), Matchers.containsString(C_DIAMOND_KATA_OUTPUT));
    }

    @Test
    void shouldOutputTheCorrectDiamondKataForZInput(CapturedOutput output) {
        mockExit(systemExit, 0);
        
        assertThrows(RuntimeException.class, () -> diamondKataApplication.run("Z"));

        MatcherAssert.assertThat(output.getOut(), Matchers.containsString(Z_DIAMOND_KATA_OUTPUT));
    }

    @Test
    void whenInputIsEmptyShouldThrow() {
        mockExit(systemExit, 1);
        
        assertThrows(RuntimeException.class, () -> diamondKataApplication.run(""));
    }

    @Test
    void whenInputIsGreaterThanOneCharacterShouldThrow() {
        mockExit(systemExit, 1);
        
        assertThrows(RuntimeException.class, () -> diamondKataApplication.run("AA"));
    }

}
