package model.Mac;

import model.TextField;

public class MacTextField implements TextField {
    private String text = "";
    public void render() { System.out.println("[Mac] Rendering rounded macOS text field."); }
    public void type(String text) { this.text = text; System.out.println("[Mac] Typed \"" + text + "\" into field."); }
    public String getText() { return text; }
}
