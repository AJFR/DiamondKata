package ajfr.diamond.kata.service;

import ajfr.diamond.kata.exception.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static ajfr.diamond.kata.test.TestDataConstants.A_TO_Z;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidationServiceTest {

    private final ValidationService validationService = new ValidationService(A_TO_Z);

    @Test
    void shouldThrowOnEmptyInputForSingleInput() {
        assertThrows(ValidationException.class, () -> validationService.validateSingleInput(new String[0]));
    }

    @ParameterizedTest
    @ValueSource(
            //unfortunately since arrays (i.e. char[]) are mutable even if static and final we have to provide full list like this.
            chars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                    'U', 'V', 'W', 'X', 'Y', 'Z'}
    )
    void shouldReturnCharacterForSingleInputWhenValid(char c) throws ValidationException {
        String[] input = {String.valueOf(c)};

        assertEquals(c, validationService.validateSingleInput(input));
    }

    @Test
    void shouldThrowOnBlankInput() {
        assertThrows(ValidationException.class, () -> validationService.validateContinuousInput(""));
    }

    @Test
    void shouldThrowOnIncorrectInput() {
        assertThrows(ValidationException.class, () -> validationService.validateContinuousInput("AA"));
    }

    @Test
    void shouldThrowOnWhenIncorrectCharacter() {
        assertThrows(ValidationException.class, () -> validationService.validateContinuousInput("?"));
    }

}