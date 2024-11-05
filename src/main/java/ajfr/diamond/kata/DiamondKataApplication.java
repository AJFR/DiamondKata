package ajfr.diamond.kata;

import ajfr.diamond.kata.exception.ValidationException;
import ajfr.diamond.kata.helpers.SystemExit;
import ajfr.diamond.kata.service.DiamondKataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.InputStream;
import java.util.Scanner;
import java.util.function.Supplier;

import static ajfr.diamond.kata.helpers.DiamondKataConstants.*;

@SpringBootApplication
public class DiamondKataApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiamondKataApplication.class);

    private final DiamondKataService diamondKataService;
    private final SystemExit systemExit;
    private final Supplier<InputStream> systemInSupplier;
    private final boolean shouldRunOnce;

    public DiamondKataApplication(
            DiamondKataService diamondKataService, SystemExit systemExit, Supplier<InputStream> systemInSupplier,
            @Value("${should.run.once}") boolean shouldRunOnce
    ) {
        this.diamondKataService = diamondKataService;
        this.systemExit = systemExit;
        this.systemInSupplier = systemInSupplier;
        this.shouldRunOnce = shouldRunOnce;
    }

    public static void main(String[] args) {
        SpringApplication.run(DiamondKataApplication.class, args);
    }

    @Override
    public void run(String... args) {
        if (shouldRunOnce) {
            runOnce(args);
        } else {
            runContinuously();
        }
    }

    private void runContinuously() {
        Scanner userInput = new Scanner(systemInSupplier.get());
        while (true) {
            LOGGER.info(CONTINUOUS_INPUT_MESSAGE);
            String input = userInput.nextLine();
            if (EXIT_STRINGS.contains(input)) {
                systemExit.exit(0);
            }
            try {
                diamondKataService.printDiamondKata(input);
            } catch (ValidationException e) {
                handleValidationException(e);
            }
        }
    }

    private void runOnce(String[] input) {
        try {
            diamondKataService.printDiamondKata(input);
            systemExit.exit(0);
        } catch (ValidationException e) {
            handleValidationException(e);
            systemExit.exit(1);
        }
    }

    private void handleValidationException(ValidationException e) {
        LOGGER.error(ERROR_MESSAGE, e.getMessage(), e.getRawInput());
        LOGGER.error(ERROR_INPUT_MESSAGE);
    }
}
