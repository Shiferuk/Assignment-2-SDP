package model.Windows;

import model.Checkbox;

public class WindowsCheckbox implements Checkbox {
    private boolean checked;
    public void render() { System.out.println("[Windows] Rendering square check box."); }
    public void toggle() { checked = !checked; System.out.println("[Windows] Windows checkbox " + (checked ? "checked." : "unchecked.")); }
    public boolean isChecked() { return checked; }
}
