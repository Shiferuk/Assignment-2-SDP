package app;

import model.*;
import model.Linux.GtkButton;
import model.Linux.GtkCheckbox;
import model.Linux.GtkTextField;

public class GtkFactory implements SystemFactory {
    @Override
    public Button createButton() {
        return new GtkButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new GtkCheckbox();
    }

    @Override
    public TextField createTextField() {
        return new GtkTextField();
    }
}