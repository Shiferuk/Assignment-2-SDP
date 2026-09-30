import app.FactoryProvider;
import app.FormRenderer;
import app.SystemFactory;

public class Main {
    public static void main(String[] args) {
        String family = args.length > 0 ? args[0] : System.getenv("APP_FAMILY");
        if (family == null || family.isBlank()) family = "Mac";
        SystemFactory factory = FactoryProvider.createFactory(family);
        new FormRenderer(factory).displayForm();
    }
}
