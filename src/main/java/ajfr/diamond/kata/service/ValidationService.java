package ajfr.diamond.kata.service;

import ajfr.diamond.kata.exception.ValidationException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ValidationService {

    private final List<Character> aToZ;

    public ValidationService(List<Character> aToZ) {
        this.aToZ = aToZ;
    }

    public Character validateSingleInput(String[] rawInput) throws ValidationException {
        String stringInput = Arrays.stream(rawInput)
                .findFirst()
                .orElseThrow(
                        () -> new ValidationException("Input must be provided.")
                );

        return commonValidations(stringInput);
    }

    public Character validateContinuousInput(String input) throws ValidationException {
        return commonValidations(input);
    }

    private Character commonValidations(String input) throws ValidationException {
        if (input.isBlank() || input.length() != 1) {
            throw new ValidationException("Input is invalid.", input);
        }
        Character inputAsCharacter = input.charAt(0);
        if (!aToZ.contains(inputAsCharacter)) {
            throw new ValidationException("Input character is invalid.", input);
        }

        return inputAsCharacter;
    }
}
