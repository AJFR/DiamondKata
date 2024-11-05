package ajfr.diamond.kata.helpers;

import ajfr.diamond.kata.interfaces.PrintingHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnProperty(value = "use.recursive.algo", havingValue = "true", matchIfMissing = true)
public class PrintingHelperRecursive implements PrintingHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrintingHelperRecursive.class);

    public void printLines(List<String> lines) {
        lines.forEach(LOGGER::info);
    }

}
