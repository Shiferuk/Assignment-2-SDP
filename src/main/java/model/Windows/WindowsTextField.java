package model.Windows;

import model.TextField;

public class WindowsTextField implements TextField {
    private String text = "";
    public void render() { System.out.println("[Windows] Rendering square-edged text field."); }
    public void type(String text) { this.text = text; System.out.println("[Windows] Typed \"" + text + "\" into field."); }
    public String getText() { return text; }
}
