package app;

import model.Button;
import model.Checkbox;
import model.TextField;

public class FormRenderer {

    private final SystemFactory factory;

    public FormRenderer(SystemFactory factory) {
        this.factory = factory;
    }

    public void displayForm() {

        System.out.println("========================================");
        System.out.println("App: Initializing OS-native form window...");

        Button submitButton = factory.createButton();
        Checkbox termsCheckbox = factory.createCheckbox();
        TextField nameField = factory.createTextField();

        nameField.render();
        termsCheckbox.render();
        submitButton.render();

        System.out.println("App: Form successfully rendered on screen.");

        System.out.println("\n[User interaction simulated]");

        nameField.type("Jane Doe");
        termsCheckbox.toggle();
        submitButton.click();

        System.out.println("App: Form submission complete. Saved to database.");
        System.out.println("========================================\n");
    }
}