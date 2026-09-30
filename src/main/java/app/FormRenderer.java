package app;

import model.*;

public class FormRenderer {
    private final SystemFactory factory;
    public FormRenderer(SystemFactory factory) { this.factory = factory; }

    public void displayForm() {
        System.out.println("========================================");
        System.out.println("OS-native account management system");
        System.out.println("========================================");
        registerAccount("Jane Doe");
        updateProfile("Jane Smith");
        confirmOrder("Order #1042");
    }

    public void registerAccount(String name) {
        System.out.println("\n[Business Operation 1: Register Account]");
        Button submitButton = factory.createButton();
        Checkbox termsCheckbox = factory.createCheckbox();
        TextField nameField = factory.createTextField();
        nameField.type(name);
        termsCheckbox.toggle();
        submitButton.submit(nameField, termsCheckbox);
    }

    public void updateProfile(String newName) {
        System.out.println("\n[Business Operation 2: Update Profile]");
        Button saveButton = factory.createButton();
        Checkbox confirmationCheckbox = factory.createCheckbox();
        TextField nameField = factory.createTextField();
        nameField.type(newName);
        confirmationCheckbox.toggle();
        saveButton.submit(nameField, confirmationCheckbox);
    }

    public void confirmOrder(String orderNumber) {
        System.out.println("\n[Business Operation 3: Confirm Order]");
        Button confirmButton = factory.createButton();
        Checkbox termsCheckbox = factory.createCheckbox();
        TextField orderField = factory.createTextField();
        orderField.type(orderNumber);
        termsCheckbox.toggle();
        confirmButton.submit(orderField, termsCheckbox);
    }
}
