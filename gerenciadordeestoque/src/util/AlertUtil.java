package util;

import javafx.scene.control.Alert;

public class AlertUtil {
    
    public static void show(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

}
