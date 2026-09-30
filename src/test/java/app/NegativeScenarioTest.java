package app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NegativeScenarioTest {

    @Test
    void unknownFamilyIsRejectedWithHelpfulMessage() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> FactoryProvider.createFactory("beos"));
        assertTrue(ex.getMessage().contains("beos"));
        assertTrue(ex.getMessage().contains("Windows"));
    }

    @Test
    void nullFamilyIsRejected() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> FactoryProvider.createFactory(null));
        assertTrue(ex.getMessage().contains("null"));
    }

    @Test
    void emptyAndWhitespaceFamiliesAreRejected() {
        assertThrows(IllegalArgumentException.class, () -> FactoryProvider.createFactory(""));
        assertThrows(IllegalArgumentException.class, () -> FactoryProvider.createFactory("   "));
    }

    @Test
    void rendererWithoutFactoryFailsFastWhenUsed() {
        FormRenderer renderer = new FormRenderer(null);
        assertThrows(NullPointerException.class, renderer::displayForm);
    }

    @Test
    void familyNamesWithSurroundingSpacesAreNotSilentlyAccepted() {
        assertThrows(IllegalArgumentException.class, () -> FactoryProvider.createFactory(" windows "));
    }
}