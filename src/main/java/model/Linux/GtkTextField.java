package model.Linux;

import model.TextField;

public class GtkTextField implements TextField {
    private String text = "";
    public void render() { System.out.println("[Gtk] Rendering flat-style Gtk text field."); }
    public void type(String text) { this.text = text; System.out.println("[Gtk] Typed \"" + text + "\" into field."); }
    public String getText() { return text; }
}
