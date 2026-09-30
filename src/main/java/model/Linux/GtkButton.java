package model.Linux;

import model.*;

public class GtkButton implements Button {
    public void render() { System.out.println("[Gtk] Rendering rounded Linux Gtk button."); }
    public void click() { System.out.println("[Gtk] Gtk button clicked!"); }
    public void submit(TextField field, Checkbox checkbox) {
        System.out.println("[Gtk] Submitting '" + field.getText() + "' with consent=" + checkbox.isChecked());
        click();
        System.out.println("[Gtk] Business operation completed.");
    }
}
