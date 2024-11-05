package ajfr.diamond.kata;

import ajfr.diamond.kata.exception.ValidationException;
import ajfr.diamond.kata.helpers.SystemExit;
import ajfr.diamond.kata.service.DiamondKataService;
import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.InputStream;
import java.util.List;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DiamondKataApplicationSingleInputTests {

    private static LogCaptor logCaptor;
    private final DiamondKataService diamondKataService = mock(DiamondKataService.class);
    private final SystemExit systemExit = mock(SystemExit.class);
    private final Supplier<InputStream> systemInSupplier = Mockito.mock();
    private final DiamondKataApplication diamondKataApplication =
            new DiamondKataApplication(diamondKataService, systemExit, systemInSupplier, true);

    @BeforeAll
    public static void setupLogCaptor() {
        logCaptor = LogCaptor.forClass(DiamondKataApplication.class);
    }

    @AfterEach
    public void clearLogs() {
        logCaptor.clearLogs();
    }

    @AfterAll
    public static void tearDown() {
        logCaptor.close();
    }

    @Test
    void shouldExitWith1WhenExceptionThrownByDiamondKataService() throws Exception {
        String inputString = "A";
        String[] input = {inputString};
        doThrow(new ValidationException("Error.", inputString))
                .when(diamondKataService)
                .printDiamondKata(input);
        List<String> expectedErrorLogs = List.of(
                "Unable to process the input. With message [Error.] and input [A].",
                "Please try again with correct input. Any uppercase character from A - Z is acceptable."
        );

        diamondKataApplication.run(input);

        assertEquals(expectedErrorLogs, logCaptor.getErrorLogs());
        verify(systemExit, times(1)).exit(1);
    }

    @Test
    void shouldExitWith0() throws Exception {
        String[] input = { "A" };

        diamondKataApplication.run(input);

        verify(diamondKataService, times(1)).printDiamondKata(input);
        verify(systemExit, times(1)).exit(0);
    }

}
