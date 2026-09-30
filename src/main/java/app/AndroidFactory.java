package app;

import model.*;
import model.Android.AndroidButton;
import model.Android.AndroidCheckbox;
import model.Android.AndroidTextField;

public class AndroidFactory implements SystemFactory {
    @Override
    public Button createButton() {
        return new AndroidButton();
    }
    @Override
    public Checkbox createCheckbox() {
        return new AndroidCheckbox();
    }
    @Override
    public TextField createTextField() {
        return new AndroidTextField();
    }
}
