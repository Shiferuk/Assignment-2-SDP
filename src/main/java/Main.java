import app.FactoryProvider;
import app.FormRenderer;
import app.SystemFactory;

public class Main {

    public static void main(String[] args) {
        String family;

        if (args.length > 0) {
            family = args[0];
        } else {

            family = System.getenv("APP_FAMILY");
        }

        if (family == null || family.isBlank()) {
            family = "Mac";
        }

        SystemFactory factory = FactoryProvider.createFactory(family);

        FormRenderer renderer = new FormRenderer(factory);
        renderer.displayForm();
    }
}