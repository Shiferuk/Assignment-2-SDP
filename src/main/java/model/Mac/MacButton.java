package model.Mac;

import model.*;

public class MacButton implements Button {
    public void render() { System.out.println("[Mac] Rendering rounded macOS button."); }
    public void click() { System.out.println("[Mac] Mac button clicked!"); }
    public void submit(TextField field, Checkbox checkbox) {
        System.out.println("[Mac] Submitting '" + field.getText() + "' with consent=" + checkbox.isChecked());
        click();
        System.out.println("[Mac] Business operation completed.");
    }
}
