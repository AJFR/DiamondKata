package ajfr.diamond.kata.helpers;

import ajfr.diamond.kata.abstracts.AbstractLineGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "white.space.char.visible", havingValue = "true")
public class VisibleWhitespaceLineGenerator extends AbstractLineGenerator {
    
    public VisibleWhitespaceLineGenerator() {
        super("_");
    }
    
}
