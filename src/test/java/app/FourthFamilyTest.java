package app;

import model.Android.*;
import model.Button;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FourthFamilyTest {

    @Test
    void androidFactoryCreatesAndroidProducts() {
        SystemFactory f = new AndroidFactory();
        assertInstanceOf(AndroidButton.class, f.createButton());
        assertInstanceOf(AndroidCheckbox.class, f.createCheckbox());
        assertInstanceOf(AndroidTextField.class, f.createTextField());
    }

    @Test
    void providerSelectsAndroidFactoryAtRuntime() {
        assertInstanceOf(AndroidFactory.class, FactoryProvider.createFactory("android"));
        assertInstanceOf(AndroidFactory.class, FactoryProvider.createFactory("ANDROID"));
    }

    @Test
    void androidProductsHaveTheirOwnLookAndBehavior() {
        SystemFactory f = new AndroidFactory();
        String out = ConsoleCapture.capture(() -> {
            f.createButton().render();
            f.createButton().click();
            f.createTextField().type("hello");
            f.createCheckbox().toggle();
        });
        assertTrue(out.contains("Material Design button"));
        assertTrue(out.contains("Android button tapped!"));
        assertTrue(out.contains("Typed \"hello\" into field."));
        assertTrue(out.contains("Android checkbox toggled."));
    }

    @Test
    void existingFormRendererWorksWithAndroidWithoutModification() {
        String out = ConsoleCapture.capture(
                () -> new FormRenderer(new AndroidFactory()).displayForm());
        assertTrue(out.contains("Rendering Material underlined text field."));
        assertTrue(out.contains("Typed \"Jane Doe\" into field."));
        assertTrue(out.contains("Android button tapped!"));
        assertFalse(out.contains("[Windows]") || out.contains("[Mac]") || out.contains("[Gtk]"));
    }

    @Test
    void androidIsInterchangeableWithTheOriginalFamiliesViaTheAbstraction() {
        Button original = new WindowsFactory().createButton();
        Button added = new AndroidFactory().createButton();
        assertNotEquals(original.getClass(), added.getClass());
        assertDoesNotThrow(() -> {
            ConsoleCapture.capture(original::render);
            ConsoleCapture.capture(added::render);
        });
    }
}