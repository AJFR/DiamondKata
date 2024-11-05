package ajfr.diamond.kata.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Supplier;

@Configuration
@Profile("default")
public class DiamondKataConfig {

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
    
    @Bean
    public Supplier<InputStream> systemInSupplier() {
        return () -> System.in;
    }

}
