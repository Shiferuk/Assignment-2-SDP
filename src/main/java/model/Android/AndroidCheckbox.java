package model.Android;

import model.Checkbox;

public class AndroidCheckbox implements Checkbox {
    private boolean checked;
    @Override
    public void render() {
        System.out.println("[Android] Rendering Material.");
    }
    @Override
    public void toggle() {
        checked = !checked; System.out.println("[Android] Android checkbox " + (checked ? "checked." : "unchecked."));
    }
    public boolean isChecked() { return checked; }
}
