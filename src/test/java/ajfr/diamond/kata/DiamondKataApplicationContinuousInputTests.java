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

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.function.Supplier;

import static ajfr.diamond.kata.helpers.DiamondKataConstants.CONTINUOUS_INPUT_MESSAGE;
import static ajfr.diamond.kata.helpers.DiamondKataConstants.ERROR_INPUT_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DiamondKataApplicationContinuousInputTests {

    private static LogCaptor logCaptor;
    private final DiamondKataService diamondKataService = mock(DiamondKataService.class);
    private final Supplier<InputStream> systemInSupplier = Mockito.mock();
    private final SystemExit systemExit = mock(SystemExit.class);
    private final DiamondKataApplication diamondKataApplication =
            new DiamondKataApplication(diamondKataService, systemExit, systemInSupplier, false);

    private final List<String> expectedInfoLogsFor3Inputs = List.of(
            CONTINUOUS_INPUT_MESSAGE, CONTINUOUS_INPUT_MESSAGE, CONTINUOUS_INPUT_MESSAGE
    );
    private final String scannerInputs = "B" + System.lineSeparator() + "C" + System.lineSeparator() + "exit";


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
    void whenRunningInContinuousModeShouldIgnoreArgsAndProcessAllLinesUntilExit() throws Exception {
        int status = 0;
        mockExit(status);
        mockIn(scannerInputs);
        String[] input = {"A"};

        assertThrows(RuntimeException.class, () -> diamondKataApplication.run(input));

        assertEquals(expectedInfoLogsFor3Inputs, logCaptor.getInfoLogs());
        verify(diamondKataService, never()).printDiamondKata(input);
        verify(diamondKataService, times(2)).printDiamondKata(anyString());
        verify(systemExit, times(1)).exit(status);
    }

    @Test
    void processLinesUntilExit() throws Exception {
        int status = 0;
        mockExit(status);
        mockIn(scannerInputs);

        assertThrows(RuntimeException.class, diamondKataApplication::run);

        assertEquals(expectedInfoLogsFor3Inputs, logCaptor.getInfoLogs());
        verify(diamondKataService, times(2)).printDiamondKata(anyString());
        verify(systemExit, times(1)).exit(status);
    }

    @Test
    void whenInThrowsShouldStillProcessInUntilExit() throws Exception {
        int status = 0;
        mockExit(status);
        String errorScannerInputs = "?" + System.lineSeparator() + "C" + System.lineSeparator() + "exit";
        mockIn(errorScannerInputs);
        doThrow(ValidationException.class).when(diamondKataService).printDiamondKata("?");

        assertThrows(RuntimeException.class, diamondKataApplication::run);

        assertEquals(expectedInfoLogsFor3Inputs, logCaptor.getInfoLogs());
        String processingError = "Unable to process the input. With message [null] and input [null].";
        assertEquals(List.of(processingError, ERROR_INPUT_MESSAGE), logCaptor.getErrorLogs());
        verify(diamondKataService, times(2)).printDiamondKata(anyString());
        verify(systemExit, times(1)).exit(status);
    }

    private void mockExit(int status) {
        doThrow(RuntimeException.class)
                .when(systemExit)
                .exit(status);
    }

    private void mockIn(String scannerInputs) {
        when(systemInSupplier.get()).thenReturn(new ByteArrayInputStream(scannerInputs.getBytes()));
    }

}
