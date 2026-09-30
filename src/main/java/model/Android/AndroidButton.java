package model.Android;

import model.*;

public class AndroidButton implements Button {
    @Override
    public void render() {
        System.out.println("[Android] Rendering Material-style action button.");
    }
    @Override
    public void click() {
        System.out.println("[Android] Android button clicked!");
    }
    public void submit(TextField field, Checkbox checkbox) {
        System.out.println("[Android] Submitting '" + field.getText() + "' with consent=" + checkbox.isChecked());
        click();
        System.out.println("[Android] Business operation completed.");
    }
}
