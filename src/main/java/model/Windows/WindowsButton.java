package model.Windows;

import model.*;

public class WindowsButton implements Button {
    public void render() { System.out.println("[Windows] Rendering sharp rectangular button."); }
    public void click() { System.out.println("[Windows] Windows button clicked!"); }
    public void submit(TextField field, Checkbox checkbox) {
        System.out.println("[Windows] Submitting '" + field.getText() + "' with consent=" + checkbox.isChecked());
        click();
        System.out.println("[Windows] Business operation completed.");
    }
}
