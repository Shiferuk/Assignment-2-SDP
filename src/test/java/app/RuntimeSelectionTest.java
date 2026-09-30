package app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RuntimeSelectionTest {

    @Test
    void selectsWindowsFactory() {
        assertInstanceOf(WindowsFactory.class, FactoryProvider.createFactory("Windows"));
    }

    @Test
    void selectionIsCaseInsensitive() {
        assertInstanceOf(MacFactory.class, FactoryProvider.createFactory("MAC"));
        assertInstanceOf(MacFactory.class, FactoryProvider.createFactory("mAc"));
    }

    @Test
    void linuxAndGtkBothMapToGtkFactory() {
        assertInstanceOf(GtkFactory.class, FactoryProvider.createFactory("linux"));
        assertInstanceOf(GtkFactory.class, FactoryProvider.createFactory("gtk"));
    }

    @Test
    void sameClientCodeProducesDifferentOutputDependingOnRuntimeString() {
        String win = ConsoleCapture.capture(
                () -> new FormRenderer(FactoryProvider.createFactory("windows")).displayForm());
        String mac = ConsoleCapture.capture(
                () -> new FormRenderer(FactoryProvider.createFactory("mac")).displayForm());
        assertTrue(win.contains("[Windows]"));
        assertFalse(win.contains("[Mac]"));
        assertTrue(mac.contains("[Mac]"));
        assertFalse(mac.contains("[Windows]"));
    }
}