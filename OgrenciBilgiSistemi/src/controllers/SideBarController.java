package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class SideBarController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_Anasayfa;

    @FXML
    private Button btn_Bolum;

    @FXML
    private Button btn_Ders;

    @FXML
    private Button btn_Not;

    @FXML
    private Button btn_OgrUyesi;

    @FXML
    private Button btn_Ogrenci;
    
    @FXML
    private Button btn_CikisYap;

    private AnaFormController formController;

    public void setFormController(AnaFormController formController) {
        this.formController = formController;
    }

    @FXML
    void btn_Anasayfa_Click(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AnaSayfa.fxml"));
        try {
            AnchorPane anasayfa = loader.load();
            formController.anchor_Sag.getChildren().clear();
            formController.anchor_Sag.getChildren().add(anasayfa);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btn_Bolum_Click(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Bolum.fxml"));
        try {
            AnchorPane bolum = loader.load();
            formController.anchor_Sag.getChildren().clear();
            formController.anchor_Sag.getChildren().add(bolum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btn_Ders_Click(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Ders.fxml"));
        try {
            AnchorPane ders = loader.load();
            formController.anchor_Sag.getChildren().clear();
            formController.anchor_Sag.getChildren().add(ders);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btn_Not_Click(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Not.fxml"));
        try {
            AnchorPane not = loader.load();
            formController.anchor_Sag.getChildren().clear();
            formController.anchor_Sag.getChildren().add(not);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btn_OgrUyesi_Click(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/OgretimUyesi.fxml"));
        try {
            AnchorPane ogretimUyesi = loader.load();
            formController.anchor_Sag.getChildren().clear();
            formController.anchor_Sag.getChildren().add(ogretimUyesi);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btn_Ogrenci_Click(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Ogrenci.fxml"));
        try {
            AnchorPane ogrenci = loader.load();
            formController.anchor_Sag.getChildren().clear();
            formController.anchor_Sag.getChildren().add(ogrenci);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void btn_CikisYap_Click(ActionEvent event) {
    	System.exit(0);
    }

    @FXML
    void initialize() {
    	
    }
}
