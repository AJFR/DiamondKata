package ajfr.diamond.kata.helpers;

import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrintingHelperLinearTest {

    private static LogCaptor logCaptor;
    private PrintingHelperLinear printingHelper;
    private final List<String> inputRecursive = List.of("A", "B", "C", "B", "A");
    private final List<String> singleLineInput = List.of("A");
    private final List<String> emptyInput = new ArrayList<>();

    @BeforeAll
    public static void setupLogCaptor() {
        logCaptor = LogCaptor.forClass(PrintingHelperLinear.class);
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
    void shouldPrintLines() {
        List<String> inputLinear = List.of("A", "B", "C");
        printingHelper = new PrintingHelperLinear();

        printingHelper.printLines(inputLinear);

        assertEquals(inputRecursive, logCaptor.getInfoLogs());
    }

    @Test
    void shouldPrintOneLineWhenOnlyOneElement() {
        printingHelper = new PrintingHelperLinear();

        printingHelper.printLines(singleLineInput);

        assertEquals(singleLineInput, logCaptor.getInfoLogs());
    }

    @Test
    void shouldPrintNothingWhenEmptyListInput() {
        printingHelper = new PrintingHelperLinear();

        printingHelper.printLines(emptyInput);

        assertEquals(emptyInput, logCaptor.getInfoLogs());
    }

}