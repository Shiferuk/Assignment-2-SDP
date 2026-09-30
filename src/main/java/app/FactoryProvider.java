package app;

public class FactoryProvider {
    private FactoryProvider() {}
    public static SystemFactory createFactory(String family) {
        if (family == null || family.isBlank()) return new MacFactory();
        switch (family.toLowerCase()) {
            case "windows": return new WindowsFactory();
            case "mac": return new MacFactory();
            case "gtk":
            case "linux":
                return new GtkFactory();

            case "android":
                return new AndroidFactory();
            default:
                throw new IllegalArgumentException(
                        "Unknown family: " + family + ". Use Windows, Mac, Gtk, or Android.");
        }
    }
}
