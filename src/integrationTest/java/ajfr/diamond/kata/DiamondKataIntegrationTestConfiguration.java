package ajfr.diamond.kata;

import ajfr.diamond.kata.helpers.SystemExit;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.TestPropertySource;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Supplier;

import static org.mockito.Mockito.mock;

@Configuration
@Profile("integrationTest")
@TestPropertySource(locations = "classpath:application-integration-test.properties")
@ComponentScan(basePackages = {"ajfr.diamond.kata.algos", "ajfr.diamond.kata.helpers", "ajfr.diamond.kata.service"})
public class DiamondKataIntegrationTestConfiguration {

    @Bean
    public SystemExit systemExit() {
        return mock(SystemExit.class);
    }

    @Bean
    public Supplier<InputStream> systemInSupplier() {
        return Mockito.mock();
    }

    @Bean
    public List<Character> aToZ() {
        return List.of(
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
                'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        );
    }

    @Bean
    public LinkedHashMap<Character, Integer> characterToWidthMap(List<Character> aToZ) {
        int widthCount = 1;
        LinkedHashMap<Character, Integer> characterToWidthMap = new LinkedHashMap<>();
        for (Character c : aToZ) {
            characterToWidthMap.put(c, widthCount);
            widthCount = widthCount + 2;
        }
        return characterToWidthMap;
    }
}
