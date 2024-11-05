package ajfr.diamond.kata.helpers;

import ajfr.diamond.kata.abstracts.AbstractLineGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "white.space.char.visible", havingValue = "false", matchIfMissing = true)
public class WhitespaceLineGenerator extends AbstractLineGenerator {
    
    public WhitespaceLineGenerator() {
        super(" ");
    }
}
