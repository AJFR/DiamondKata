package ajfr.diamond.kata.test;

import ajfr.diamond.kata.helpers.SystemExit;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.mockito.Mockito.doThrow;

public class TestData {
    
    private TestData() {}

    public static final String C_DIAMOND_KATA_OUTPUT;
    public static final String Z_DIAMOND_KATA_OUTPUT;

    static {
        try {
            C_DIAMOND_KATA_OUTPUT = Files.readString(Paths.get(TestData.class.getClassLoader().getResource("cDiamondKataOutput.txt").toURI()));
            Z_DIAMOND_KATA_OUTPUT = Files.readString(Paths.get(TestData.class.getClassLoader().getResource("zDiamondKataOutput.txt").toURI()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void mockExit(SystemExit systemExit, int status) {
        doThrow(RuntimeException.class)
                .when(systemExit)
                .exit(status);
    }


}
