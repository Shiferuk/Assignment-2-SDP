package app;

import model.Button;
import model.Checkbox;
import model.TextField;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CompatibilityTest {

    @ParameterizedTest
    @CsvSource({"windows,model.Windows", "mac,model.Mac", "gtk,model.Linux", "android,model.Android"})
    void allProductsOfAFactoryBelongToTheSameFamilyPackage(String family, String expectedPackage) {
        SystemFactory f = FactoryProvider.createFactory(family);
        assertEquals(expectedPackage, f.createButton().getClass().getPackageName());
        assertEquals(expectedPackage, f.createCheckbox().getClass().getPackageName());
        assertEquals(expectedPackage, f.createTextField().getClass().getPackageName());
    }

    @ParameterizedTest
    @CsvSource({"windows,[Windows]", "mac,[Mac]", "gtk,[Gtk]", "android,[Android]"})
    void renderedOutputOfAllProductsCarriesOneConsistentFamilyTag(String family, String tag) {
        SystemFactory f = FactoryProvider.createFactory(family);
        String out = ConsoleCapture.capture(() -> {
            f.createButton().render();
            f.createCheckbox().render();
            f.createTextField().render();
        });
        long tagged = out.lines().filter(l -> l.startsWith(tag)).count();
        assertEquals(3, tagged, "Every rendered widget must carry the same family tag");
    }

    @Test
    void differentFactoriesProduceDistinctFamilies() {
        Set<Class<?>> buttonClasses = new HashSet<>();
        for (String family : new String[]{"windows", "mac", "gtk", "android"}) {
            buttonClasses.add(FactoryProvider.createFactory(family).createButton().getClass());
        }
        assertEquals(4, buttonClasses.size());
    }

    @Test
    void productsFromDifferentFamiliesShareOnlyTheAbstractions() {
        Button win = new WindowsFactory().createButton();
        Button mac = new MacFactory().createButton();
        assertNotEquals(win.getClass(), mac.getClass());
        assertTrue(win instanceof Button && mac instanceof Button);
        Checkbox c = new GtkFactory().createCheckbox();
        TextField t = new GtkFactory().createTextField();
        assertNotNull(c);
        assertNotNull(t);
    }
}