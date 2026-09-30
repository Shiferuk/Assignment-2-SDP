package model.Mac;

import model.Checkbox;

public class MacCheckbox implements Checkbox {
    private boolean checked;
    public void render() { System.out.println("[Mac] Rendering rounded macOS check mark."); }
    public void toggle() { checked = !checked; System.out.println("[Mac] Mac checkbox " + (checked ? "checked." : "unchecked.")); }
    public boolean isChecked() { return checked; }
}
