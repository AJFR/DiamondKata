package ajfr.diamond.kata;

import ajfr.diamond.kata.helpers.SystemExit;
import ajfr.diamond.kata.service.DiamondKataService;
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

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.function.Supplier;

import static ajfr.diamond.kata.test.TestData.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith({SpringExtension.class, OutputCaptureExtension.class})
@SpringBootTest(classes = DiamondKataIntegrationTestConfiguration.class)
@ActiveProfiles("integrationTest")
public class DiamondKataContinuousRunIntegrationTest {

    private final DiamondKataApplication diamondKataApplication;
    private final SystemExit systemExit;
    private final Supplier<InputStream> systemInSupplier;

    private final String scannerInputs = "Z" + System.lineSeparator() + "exit";

    @Autowired
    public DiamondKataContinuousRunIntegrationTest(
            DiamondKataService diamondKataService, SystemExit systemExit, Supplier<InputStream> systemInSupplier
    ) {
        this.systemExit = systemExit;
        this.systemInSupplier = systemInSupplier;
        this.diamondKataApplication = new DiamondKataApplication(
                diamondKataService, systemExit, systemInSupplier, false
        );
    }

    @Test
    void whenValidInPassedShouldProcessAll(CapturedOutput output) {
        mockExit(systemExit, 0);
        mockIn("C" + System.lineSeparator() + scannerInputs);

        assertThrows(RuntimeException.class, diamondKataApplication::run);

        MatcherAssert.assertThat(output.getOut(), Matchers.containsString(C_DIAMOND_KATA_OUTPUT));
        MatcherAssert.assertThat(output.getOut(), Matchers.containsString(Z_DIAMOND_KATA_OUTPUT));
    }

    @Test
    void whenArgsPassedInShouldIgnoreAndProcessIn(CapturedOutput output) {
        mockExit(systemExit, 0);
        mockIn(scannerInputs);

        assertThrows(RuntimeException.class, () -> diamondKataApplication.run("C"));

        MatcherAssert.assertThat(output.getOut(), Matchers.not(Matchers.containsString(C_DIAMOND_KATA_OUTPUT)));
        MatcherAssert.assertThat(output.getOut(), Matchers.containsString(Z_DIAMOND_KATA_OUTPUT));
    }

    @Test
    void whenInvalidInPassedShouldProcessUntilExit(CapturedOutput output) {
        mockExit(systemExit, 0);
        mockIn("CC" + System.lineSeparator() + "Z" + System.lineSeparator() + "exit");

        assertThrows(RuntimeException.class, diamondKataApplication::run);

        MatcherAssert.assertThat(output.getOut(), Matchers.not(Matchers.containsString(C_DIAMOND_KATA_OUTPUT)));
        MatcherAssert.assertThat(output.getOut(), Matchers.containsString(Z_DIAMOND_KATA_OUTPUT));
    }

    private void mockIn(String scannerInputs) {
        when(systemInSupplier.get()).thenReturn(new ByteArrayInputStream(scannerInputs.getBytes()));
    }

}
