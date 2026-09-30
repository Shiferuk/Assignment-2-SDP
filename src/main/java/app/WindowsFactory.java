package app;

import model.Mac.*;

import model.*;
import model.Windows.WindowsButton;
import model.Windows.WindowsCheckbox;
import model.Windows.WindowsTextField;

public class WindowsFactory implements SystemFactory {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }
    Button createButton(String os) {
        if (os.equals("Windows")) return new WindowsButton();
        else return new MacButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }

    @Override
    public TextField createTextField() {
        return new WindowsTextField();
    }
}