package org.eleron.lris.niokr.util;

import javafx.scene.control.Alert;

public class AlertUtil {

    public static void getAlert(String message){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Предупреждение!");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    public static void getInformation(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Внимание");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
}
