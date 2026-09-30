package app;

public class FactoryProvider {

    private FactoryProvider() {

    }

    public static SystemFactory createFactory(String family) {

        if (family == null) {
            throw new IllegalArgumentException("Family cannot be null.");
        }

        switch (family.toLowerCase()) {

            case "windows":
                return new WindowsFactory();

            case "mac":
                return new MacFactory();

            case "gtk":
            case "linux":
                return new GtkFactory();

            default:
                throw new IllegalArgumentException(
                        "Unknown family: " + family +
                                ". Use Windows, Mac, or Gtk."
                );
        }
    }
}