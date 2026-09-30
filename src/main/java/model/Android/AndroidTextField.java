package model.Android;

import model.TextField;

public class AndroidTextField implements TextField {
    private String text = "";
    @Override
    public void render() {
        System.out.println("[Android] Rendering Material text field.");
    }
    @Override
    public void type(String text) {
        this.text = text; System.out.println("[Android] Typed \"" + text + "\" into field.");
    }
    public String getText() { return text; }
}
