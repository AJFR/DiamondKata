package ajfr.diamond.kata;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static ajfr.diamond.kata.test.TestData.C_DIAMOND_KATA_OUTPUT;

@ExtendWith({SpringExtension.class, OutputCaptureExtension.class})
@SpringBootTest(properties = "shoud.run.once=true", args = "C", useMainMethod = SpringBootTest.UseMainMethod.ALWAYS)
@ActiveProfiles("integrationTest")
public class DiamondKataSingleRunMainMethodIntegrationTest {

    @Test
    void whenMainMethodRunShouldPassArgsAndProcessCorrectly(CapturedOutput output) {
        MatcherAssert.assertThat(output.getOut(), Matchers.containsString(C_DIAMOND_KATA_OUTPUT));
    }
    
}
