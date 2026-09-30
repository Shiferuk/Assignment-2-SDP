package app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FormRendererBehaviorTest {

    private String run(String family) {
        return ConsoleCapture.capture(
                () -> new FormRenderer(FactoryProvider.createFactory(family)).displayForm());
    }

    @Test
    void formRendersAllThreeWidgetsBeforeInteraction() {
        String out = run("windows");
        int field = out.indexOf("Rendering square-edged text input");
        int box = out.indexOf("Rendering square check box");
        int button = out.indexOf("Rendering sharp rectangular button");
        int interaction = out.indexOf("[User interaction simulated]");
        assertTrue(field >= 0 && box >= 0 && button >= 0, "All widgets must render");
        assertTrue(field < box && box < button, "Render order: field, checkbox, button");
        assertTrue(button < interaction, "Rendering precedes user interaction");
    }

    @Test
    void userInteractionTypesTogglesThenClicksInOrder() {
        String out = run("mac");
        int typed = out.indexOf("Typed \"Jane Doe\" into field.");
        int toggled = out.indexOf("Mac checkbox toggled.");
        int clicked = out.indexOf("Mac button clicked!");
        assertTrue(typed >= 0, "Name must be typed into the field");
        assertTrue(typed < toggled && toggled < clicked, "Order: type, toggle, click");
    }

    @Test
    void formLifecycleStartsAndEndsWithAppMessages() {
        String out = run("gtk");
        int start = out.indexOf("App: Initializing OS-native form window...");
        int rendered = out.indexOf("App: Form successfully rendered on screen.");
        int done = out.indexOf("App: Form submission complete. Saved to database.");
        assertTrue(start >= 0 && start < rendered && rendered < done);
    }

    @Test
    void formProducesNoWidgetOutputFromOtherFamilies() {
        String out = run("gtk");
        assertFalse(out.contains("[Windows]"));
        assertFalse(out.contains("[Mac]"));
        assertFalse(out.contains("[Android]"));
    }

    @Test
    void rendererCanBeReusedAndIsRepeatable() {
        FormRenderer renderer = new FormRenderer(new WindowsFactory());
        String first = ConsoleCapture.capture(renderer::displayForm);
        String second = ConsoleCapture.capture(renderer::displayForm);
        assertEquals(first, second);
    }
}