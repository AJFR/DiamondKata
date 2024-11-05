package ajfr.diamond.kata.helpers;

import ajfr.diamond.kata.interfaces.PrintingHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnProperty(value = "use.recursive.algo", havingValue = "false")
public class PrintingHelperLinear implements PrintingHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrintingHelperLinear.class);

    public void printLines(List<String> lines) {
        //print full array forwards
        for (String s : lines) {
            LOGGER.info(s);
        }
        //print array backwards skipping last element
        for (int i = lines.size() - 2; i >= 0; i--) {
            LOGGER.info(lines.get(i));
        }
    }

}
