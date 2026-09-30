import app.ConsoleCapture;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void commandLineArgumentSelectsTheFamily() {
        String out = ConsoleCapture.capture(() -> Main.main(new String[]{"windows"}));
        assertTrue(out.contains("[Windows]"));
        assertTrue(out.contains("Form submission complete"));
    }

    @Test
    void commandLineArgumentSelectsAndroid() {
        String out = ConsoleCapture.capture(() -> Main.main(new String[]{"android"}));
        assertTrue(out.contains("[Android]"));
    }

    @Test
    void invalidCommandLineArgumentFailsWithIllegalArgument() {
        assertThrows(IllegalArgumentException.class, () -> Main.main(new String[]{"amiga"}));
    }
}