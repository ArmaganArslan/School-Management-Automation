package dialogs;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MessageBox {
	
    public static void showMessage(String title, String content) {
        showMessage(title, content, "info");
    }

    public static void showMessage(String title, String content, String type) {
        AlertType alertType;

        if (type.equals("warning")) {
            alertType = AlertType.WARNING;
        } else if (type.equals("error")) {
            alertType = AlertType.ERROR;
        } else {
            alertType = AlertType.INFORMATION;
        }

        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("icons/study.png"));
        
        //CSS stilini ayarlama
        alert.getDialogPane().setStyle(ALERT_DIALOG_STYLE);

        alert.showAndWait();
    }
    
    private static final String ALERT_DIALOG_STYLE = "-fx-background-color: linear-gradient(#A0A0A0, #CCCCCC);";
    
}



