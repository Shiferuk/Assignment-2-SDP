package app;

import model.Button;
import model.Checkbox;
import model.TextField;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientAbstractionTest {

    @SuppressWarnings("unchecked")
    private static <T> T recorder(Class<T> type, String prefix, List<String> events) {
        return (T) Proxy.newProxyInstance(
                type.getClassLoader(), new Class<?>[]{type},
                (proxy, method, args) -> {
                    String arg = (args != null && args.length == 1 && args[0] instanceof String)
                            ? ":" + args[0] : "";
                    events.add(prefix + "." + method.getName() + arg);
                    return null; // fine for void methods
                });
    }

    private static class RecordingFactory implements SystemFactory {
        final List<String> events = new ArrayList<>();

        @Override
        public Button createButton() {
            events.add("createButton");
            return recorder(Button.class, "button", events);
        }

        @Override
        public Checkbox createCheckbox() {
            events.add("createCheckbox");
            return recorder(Checkbox.class, "checkbox", events);
        }

        @Override
        public TextField createTextField() {
            events.add("createTextField");
            return recorder(TextField.class, "field", events);
        }
    }

    private static void assertInOrder(List<String> events, String... expected) {
        int last = -1;
        for (String e : expected) {
            int idx = events.indexOf(e);
            assertTrue(idx >= 0, "Missing event: " + e + " in " + events);
            assertTrue(idx > last, "Event out of order: " + e + " in " + events);
            last = idx;
        }
    }

    @Test
    void clientWorksWithACustomFactoryItHasNeverSeen() {
        RecordingFactory factory = new RecordingFactory();
        ConsoleCapture.capture(() -> new FormRenderer(factory).displayForm());

        assertInOrder(factory.events,
                "field.render", "checkbox.render", "button.render",
                "field.type:Jane Doe", "checkbox.toggle", "button.click");
    }

    @Test
    void clientRequestsEachProductExactlyOnce() {
        RecordingFactory factory = new RecordingFactory();
        ConsoleCapture.capture(() -> new FormRenderer(factory).displayForm());
        assertEquals(1, factory.events.stream().filter("createButton"::equals).count());
        assertEquals(1, factory.events.stream().filter("createCheckbox"::equals).count());
        assertEquals(1, factory.events.stream().filter("createTextField"::equals).count());
    }

    @Test
    void clientDependsOnlyOnTheSystemFactoryInterfaceStructurally() {
        Constructor<?>[] ctors = FormRenderer.class.getDeclaredConstructors();
        assertEquals(1, ctors.length);
        assertArrayEquals(new Class<?>[]{SystemFactory.class}, ctors[0].getParameterTypes());
        assertTrue(SystemFactory.class.isInterface());

        for (Field field : FormRenderer.class.getDeclaredFields()) {
            assertTrue(field.getType().isInterface(),
                    "Field " + field.getName() + " must be typed by an interface");
            String pkg = field.getType().getPackageName();
            assertFalse(pkg.startsWith("model.Windows") || pkg.startsWith("model.Mac")
                            || pkg.startsWith("model.Linux") || pkg.startsWith("model.Android"),
                    "Client must not reference concrete product classes");
        }
    }

    @Test
    void everyConcreteFactoryIsASystemFactory() {
        assertTrue(SystemFactory.class.isAssignableFrom(WindowsFactory.class));
        assertTrue(SystemFactory.class.isAssignableFrom(MacFactory.class));
        assertTrue(SystemFactory.class.isAssignableFrom(GtkFactory.class));
        assertTrue(SystemFactory.class.isAssignableFrom(AndroidFactory.class));
    }
}