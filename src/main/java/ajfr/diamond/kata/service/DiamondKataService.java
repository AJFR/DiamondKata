package ajfr.diamond.kata.service;

import ajfr.diamond.kata.exception.ValidationException;
import ajfr.diamond.kata.interfaces.DiamondKataAlgorithm;
import ajfr.diamond.kata.interfaces.PrintingHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiamondKataService {

    private final DiamondKataAlgorithm diamondKataAlgorithm;
    private final ValidationService validationService;
    private final PrintingHelper printingHelper;

    public DiamondKataService(
            DiamondKataAlgorithm diamondKataAlgorithm, ValidationService validationService, PrintingHelper printingHelper
    ) {
        this.diamondKataAlgorithm = diamondKataAlgorithm;
        this.validationService = validationService;
        this.printingHelper = printingHelper;
    }

    public void printDiamondKata(String[] rawInput) throws ValidationException {
        Character inputCharacter = validationService.validateSingleInput(rawInput);
        printDiamondKata(inputCharacter);
    }

    public void printDiamondKata(String input) throws ValidationException {
        Character inputCharacter = validationService.validateContinuousInput(input);
        printDiamondKata(inputCharacter);
    }

    private void printDiamondKata(Character input) {
        List<String> lines = diamondKataAlgorithm.diamondKata(input);
        printingHelper.printLines(lines);
    }

}
