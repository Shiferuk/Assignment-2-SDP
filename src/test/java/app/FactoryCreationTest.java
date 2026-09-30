package app;

import model.Button;
import model.Checkbox;
import model.TextField;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FactoryCreationTest {

    private void assertCreatesFullFamily(SystemFactory factory) {
        Button b = factory.createButton();
        Checkbox c = factory.createCheckbox();
        TextField t = factory.createTextField();
        assertNotNull(b);
        assertNotNull(c);
        assertNotNull(t);
    }

    @Test
    void windowsFactoryCreatesCompleteFamily() {
        assertCreatesFullFamily(new WindowsFactory());
    }

    @Test
    void macFactoryCreatesCompleteFamily() {
        assertCreatesFullFamily(new MacFactory());
    }

    @Test
    void gtkFactoryCreatesCompleteFamily() {
        assertCreatesFullFamily(new GtkFactory());
    }

    @Test
    void factoryReturnsFreshInstanceOnEachCall() {
        SystemFactory factory = new WindowsFactory();
        assertNotSame(factory.createButton(), factory.createButton());
        assertNotSame(factory.createCheckbox(), factory.createCheckbox());
        assertNotSame(factory.createTextField(), factory.createTextField());
    }
}