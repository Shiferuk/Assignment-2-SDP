package app;

import model.Linux.*;
import model.Mac.*;
import model.Windows.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class ConcreteProductTest {

    @Test
    void windowsFactoryCreatesWindowsProducts() {
        SystemFactory f = new WindowsFactory();
        assertInstanceOf(WindowsButton.class, f.createButton());
        assertInstanceOf(WindowsCheckbox.class, f.createCheckbox());
        assertInstanceOf(WindowsTextField.class, f.createTextField());
    }

    @Test
    void macFactoryCreatesMacProducts() {
        SystemFactory f = new MacFactory();
        assertInstanceOf(MacButton.class, f.createButton());
        assertInstanceOf(MacCheckbox.class, f.createCheckbox());
        assertInstanceOf(MacTextField.class, f.createTextField());
    }

    @Test
    void gtkFactoryCreatesGtkProducts() {
        SystemFactory f = new GtkFactory();
        assertInstanceOf(GtkButton.class, f.createButton());
        assertInstanceOf(GtkCheckbox.class, f.createCheckbox());
        assertInstanceOf(GtkTextField.class, f.createTextField());
    }
}