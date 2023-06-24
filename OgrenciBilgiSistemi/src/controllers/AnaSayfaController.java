package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import repositories.BolumRepository;
import repositories.DersRepository;
import repositories.OgrenciRepository;
import repositories.OgretimUyesiRepository;
import models.BolumModel;
import models.DersModel;
import models.OgrenciModel;
import models.OgretimUyesiModel;

public class AnaSayfaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lbl_Bolum;

    @FXML
    private Label lbl_Ders;

    @FXML
    private Label lbl_Ogr;

    @FXML
    private Label lbl_OgrtUye;
    
    private void toplamSayilar() {
    	ObservableList<BolumModel> bolumler = BolumRepository.getAllBolumler();
        int bolumSayisi = bolumler.size();
        lbl_Bolum.setText(String.valueOf(bolumSayisi));

        ObservableList<DersModel> dersler = DersRepository.getAllDersler();
        int dersSayisi = dersler.size();
        lbl_Ders.setText(String.valueOf(dersSayisi));

        ObservableList<OgrenciModel> ogrenciler = OgrenciRepository.getAllOgrenciler();
        int ogrenciSayisi = ogrenciler.size();
        lbl_Ogr.setText(String.valueOf(ogrenciSayisi));

        ObservableList<OgretimUyesiModel> ogretimUyeleri = OgretimUyesiRepository.getAllOgretimUyeleri();
        int ogretimUyesiSayisi = ogretimUyeleri.size();
        lbl_OgrtUye.setText(String.valueOf(ogretimUyesiSayisi));
    }

    @FXML
    void initialize() {
    	toplamSayilar();
    }

}
