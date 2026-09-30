package app;

import model.Button;
import model.Checkbox;
import model.TextField;
import model.Windows.WindowsButton;
import model.Windows.WindowsCheckbox;
import model.Windows.WindowsTextField;

public class WindowsFactory implements SystemFactory {

    @Override
    public Button createButton() {
        return new WindowsButton();
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