package model.Linux;

import model.Checkbox;

public class GtkCheckbox implements Checkbox {
    private boolean checked;
    public void render() { System.out.println("[Gtk] Rendering rounded Linux Gtk check mark."); }
    public void toggle() { checked = !checked; System.out.println("[Gtk] Gtk checkbox " + (checked ? "checked." : "unchecked.")); }
    public boolean isChecked() { return checked; }
}
