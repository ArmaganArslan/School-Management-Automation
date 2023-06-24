package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import dialogs.MessageBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.BolumModel;
import repositories.BolumRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BolumController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_Ekle;

    @FXML
    private Button btn_Guncelle;

    @FXML
    private Button btn_Sil;
    
    @FXML
    private Button btn_Temizle;

    @FXML
    private TableView<BolumModel> tbl_Bolum;

    @FXML
    private TableColumn<BolumModel, String> tbl_BolumAdi;

    @FXML
    private TextField txt_BolumAdi;

    private ObservableList<BolumModel> bolumList;
    
    @FXML
    void tbl_Bolum_Click(MouseEvent event) {
    	if (event.getClickCount() == 1) {
            BolumModel seciliBolum = tbl_Bolum.getSelectionModel().getSelectedItem();
            if (seciliBolum != null) {
                txt_BolumAdi.setText(seciliBolum.getAdi());}}
    }

    @FXML
    void btn_Ekle_Click(ActionEvent event) {
    	String bolumAdi = txt_BolumAdi.getText();
    	
    	if (bolumAdi.isEmpty()) {
    		MessageBox.showMessage("Öğrenci Bilgi Sistemi", "Lütfen tüm alanları doldurunuz.", "warning");
    		return;
    	}

        BolumModel yeniBolum = new BolumModel(0, bolumAdi);
        BolumRepository.insertBolum(yeniBolum);

        bolumList.add(yeniBolum);
        txt_BolumAdi.clear();
    }

    @FXML
    void btn_Guncelle_Click(ActionEvent event) {
    	BolumModel seciliBolum = tbl_Bolum.getSelectionModel().getSelectedItem();

        if (seciliBolum != null) {
            String yeniAdi = txt_BolumAdi.getText();
            seciliBolum.setAdi(yeniAdi);
            BolumRepository.updateBolum(seciliBolum);

            txt_BolumAdi.clear();
            tbl_Bolum.refresh();
        }
        else {
        	MessageBox.showMessage("Öğrenci Bilgi Sistemi", "Güncellenmek istenen nesne seçili değil.", "warning");
        }
    }

    @FXML
    void btn_Sil_Click(ActionEvent event) {
    	BolumModel seciliBolum = tbl_Bolum.getSelectionModel().getSelectedItem();

        if (seciliBolum != null) {
            BolumRepository.deleteBolum(seciliBolum);

            bolumList.remove(seciliBolum);
            txt_BolumAdi.clear();
            tbl_Bolum.refresh();
            
        }
        else {
        	MessageBox.showMessage("Öğrenci Bilgi Sistemi", "Silinmek istenen nesne seçili değil.", "warning");
        }
    }
    
    @FXML
    void btn_Temizle_Click(ActionEvent event) {
    	clearFields();
    }
    
    private void clearFields() {
    	tbl_Bolum.getSelectionModel().clearSelection();
    	txt_BolumAdi.clear();
    }
    
    private void setupTable() {
    	tbl_BolumAdi.setCellValueFactory(new PropertyValueFactory<>("adi"));
    }
    
    private void loadData() {
    	bolumList = FXCollections.observableArrayList(BolumRepository.getAllBolumler());
        tbl_Bolum.setItems(bolumList);
    }

    @FXML
    void initialize() {
    	setupTable();
    	loadData();
    }
}
