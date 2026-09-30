package app;

import model.*;

public interface SystemFactory {
    Button createButton();
    Checkbox createCheckbox();
    TextField createTextField();
}