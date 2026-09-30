package model;
public interface Button {
    void render();
    void click();
    void submit(TextField field, Checkbox checkbox);
}
