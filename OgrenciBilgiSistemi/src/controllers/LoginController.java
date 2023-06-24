package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import dialogs.MessageBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import repositories.LoginRepository;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private AnchorPane anchor_Login;

    @FXML
    private Button btn_Giris;

    @FXML
    private ToggleButton btn_SifreGoster;

    @FXML
    private TextField txt_KullaniciAd;

    @FXML
    private PasswordField passField_Sifre;

    @FXML
    void btn_Giris_Click(ActionEvent event) {
    	
    	String kullaniciAdi = txt_KullaniciAd.getText();
        String sifre = passField_Sifre.getText();
        
        if (kullaniciAdi.isEmpty() || sifre.isEmpty()) {
        	MessageBox.showMessage("Öğrenci Bilgi Sistemi", "Lütfen tüm alanları doldurunuz.", "warning");
        }
        
        else {
        	if (LoginRepository.checkCredentials(kullaniciAdi, sifre)) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AnaForm.fxml"));
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));

                    Image iconImage = new Image(getClass().getResourceAsStream("/icons/study.png"));

                    stage.getIcons().add(iconImage);

                    AnaFormController anaFormController = loader.getController();
                    anaFormController.setIconImage(iconImage);

                    stage.setTitle("Öğrenci Bilgi Sistemi");

                    stage.setResizable(false);

                    stage.show();
                    
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
            	MessageBox.showMessage("Hata", "Kullanıcı adı veya şifre hatalı.", "error");
            }
        }
    }
    
    private void sifreGosterToggle(PasswordField passwordField) {
    	if (btn_SifreGoster.isSelected()) {
    	String sifre = passwordField.getText();
    	passwordField.setText("");
    	passwordField.setPromptText(sifre);
    	} else {
    	String promptText = passwordField.getPromptText();
    	passwordField.setText(promptText);
    	passwordField.setPromptText("Şifre Giriniz...");
    	}
    }

    @FXML
    void initialize() {    	
    	btn_SifreGoster.setOnAction(event -> sifreGosterToggle(passField_Sifre));
    }

}
