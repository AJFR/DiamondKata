package ajfr.diamond.kata.service;

import ajfr.diamond.kata.exception.ValidationException;
import ajfr.diamond.kata.interfaces.DiamondKataAlgorithm;
import ajfr.diamond.kata.interfaces.PrintingHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DiamondKataServiceTest {

    private final DiamondKataAlgorithm diamondKataAlgorithm = mock(DiamondKataAlgorithm.class);
    private final ValidationService validationService = mock(ValidationService.class);
    private final PrintingHelper printingHelper = mock(PrintingHelper.class);
    private final DiamondKataService diamondKataService = new DiamondKataService(diamondKataAlgorithm, validationService, printingHelper);

    @BeforeEach
    void beforeEach() {
        when(diamondKataAlgorithm.diamondKata(any(Character.class))).thenAnswer(
                invocationOnMock -> List.of(invocationOnMock.getArguments()[0].toString())
        );
    }

    @Test
    void shouldThrowForSingleInputWhenValidationServiceThrows() throws ValidationException {
        String[] input = new String[0];
        when(validationService.validateSingleInput(input)).thenThrow(ValidationException.class);

        assertThrows(ValidationException.class, () -> diamondKataService.printDiamondKata(input));
    }

    @Test
    void shouldThrowForContinuousInputWhenValidationServiceThrows() throws ValidationException {
        String input = "";
        when(validationService.validateContinuousInput(input)).thenThrow(ValidationException.class);

        assertThrows(ValidationException.class, () -> diamondKataService.printDiamondKata(input));
    }

    @Test
    void shouldCallPrintingHelperForSingleInput() throws ValidationException {
        Character c = 'A';
        String[] input = {c.toString()};
        when(validationService.validateSingleInput(input)).thenReturn(c);

        diamondKataService.printDiamondKata(input);

        verify(diamondKataAlgorithm, times(1)).diamondKata(c);
        verify(printingHelper, times(1)).printLines(List.of(c.toString()));
    }

    @Test
    void shouldCallPrintingHelperForContinuousInput() throws ValidationException {
        Character c = 'A';
        when(validationService.validateContinuousInput(c.toString())).thenReturn(c);

        diamondKataService.printDiamondKata(c.toString());

        verify(diamondKataAlgorithm, times(1)).diamondKata(c);
        verify(printingHelper, times(1)).printLines(List.of(c.toString()));
    }

}