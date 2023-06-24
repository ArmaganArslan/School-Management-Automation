package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

public class AnaFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    public AnchorPane anchor_Ana;

    @FXML
    public AnchorPane anchor_Sag;

    @FXML
    private AnchorPane anchor_Sol;

    @FXML
    private AnchorPane anchor_Tum;
    
    @FXML
    private Image iconImage;

    private SideBarController sideBarController;

    public void setSideBarController(SideBarController sideBarController) {
        this.sideBarController = sideBarController;
    }
    
    public void setIconImage(Image iconImage) {
        this.iconImage = iconImage;
    }

    @FXML
    void initialize() {
    	
        FXMLLoader sideBarLoader = new FXMLLoader(getClass().getResource("/views/SideBar.fxml"));
        
        try {
        	
            AnchorPane sideBar = sideBarLoader.load();
            sideBarController = sideBarLoader.getController();
            sideBarController.setFormController(this);
            anchor_Sol.getChildren().add(sideBar);            
            
            FXMLLoader anasayfaLoader = new FXMLLoader(getClass().getResource("/views/AnaSayfa.fxml"));
            AnchorPane anasayfa = anasayfaLoader.load();
            anchor_Sag.getChildren().add(anasayfa);
            
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
