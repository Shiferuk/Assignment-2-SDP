package app;

import model.Button;
import model.Checkbox;
import model.TextField;

public interface SystemFactory {

    Button createButton();

    Checkbox createCheckbox();

    TextField createTextField();
}