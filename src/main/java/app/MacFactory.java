package app;

import model.Button;
import model.Checkbox;
import model.TextField;
import model.Mac.MacButton;
import model.Mac.MacCheckbox;
import model.Mac.MacTextField;

public class MacFactory implements SystemFactory {

    @Override
    public Button createButton() {
        return new MacButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }

    @Override
    public TextField createTextField() {
        return new MacTextField();
    }
}