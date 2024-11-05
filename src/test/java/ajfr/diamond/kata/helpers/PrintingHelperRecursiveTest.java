package ajfr.diamond.kata.helpers;

import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrintingHelperRecursiveTest {

    private static LogCaptor logCaptor;
    private PrintingHelperRecursive printingHelper;
    private final List<String> inputRecursive = List.of("A", "B", "C", "B", "A");
    private final List<String> singleLineInput = List.of("A");
    private final List<String> emptyInput = new ArrayList<>();

    @BeforeAll
    public static void setupLogCaptor() {
        logCaptor = LogCaptor.forClass(PrintingHelperRecursive.class);
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
    void shouldPrintLinesForRecursive() {
        printingHelper = new PrintingHelperRecursive();

        printingHelper.printLines(inputRecursive);

        assertEquals(inputRecursive, logCaptor.getInfoLogs());
    }

    @Test
    void shouldPrintOneLineWhenOnlyOneElementRecursive() {
        printingHelper = new PrintingHelperRecursive();

        printingHelper.printLines(singleLineInput);

        assertEquals(singleLineInput, logCaptor.getInfoLogs());
    }

    @Test
    void shouldPrintNothingWhenEmptyListInputRecursive() {
        printingHelper = new PrintingHelperRecursive();

        printingHelper.printLines(emptyInput);

        assertEquals(emptyInput, logCaptor.getInfoLogs());
    }

}