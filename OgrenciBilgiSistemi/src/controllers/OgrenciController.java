package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import dialogs.MessageBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.BolumModel;
import models.OgrenciModel;
import repositories.BolumRepository;
import repositories.OgrenciRepository;

public class OgrenciController {

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
    private ChoiceBox<BolumModel> cb_BolumDegistir;

    @FXML
    private ChoiceBox<BolumModel> cb_BolumSec;

    @FXML
    private ChoiceBox<String> cb_Cinsiyet;

    @FXML
    private DatePicker dt_DogumTarihi;

    @FXML
    private TableColumn<OgrenciModel, String> tbl_Adi;

    @FXML
    private TableColumn<OgrenciModel, String> tbl_Cinsiyet;

    @FXML
    private TableColumn<OgrenciModel, LocalDate> tbl_DogumTarihi;

    @FXML
    private TableColumn<OgrenciModel, Integer> tbl_No;

    @FXML
    private TableView<OgrenciModel> tbl_Ogrenci;

    @FXML
    private TableColumn<OgrenciModel, String> tbl_Soyadi;

    @FXML
    private TableColumn<OgrenciModel, String> tbl_TcNo;

    @FXML
    private TextField txt_Adi;

    @FXML
    private TextField txt_OgrenciNo;

    @FXML
    private TextField txt_Soyadi;

    @FXML
    private TextField txt_TcNo;
    
    @FXML
    private Button btn_TumOgr;
    
    private ObservableList<OgrenciModel> ogrenciList = FXCollections.observableArrayList();

    private ObservableList<BolumModel> bolumList;

    @FXML
    void btn_Temizle_Click(ActionEvent event) {
        clearFields();
    }
    
    @FXML
    void btn_TumOgr_Click(ActionEvent event) {
        cb_BolumSec.getSelectionModel().clearSelection();
        loadData();
    }

    @FXML
    void btn_Ekle_Click(ActionEvent event) {
        String ogrNo = txt_OgrenciNo.getText();
        String adi = txt_Adi.getText();
        String soyadi = txt_Soyadi.getText();
        String tcNo = txt_TcNo.getText();
        String cinsiyet = cb_Cinsiyet.getValue();
        LocalDate localDate = dt_DogumTarihi.getValue();
        java.sql.Date dogumTarihi = java.sql.Date.valueOf(localDate);
        BolumModel bolumDegistir = cb_BolumDegistir.getValue();

        if (ogrNo.isEmpty() || adi.isEmpty() || soyadi.isEmpty() || tcNo.isEmpty() || cinsiyet.isEmpty() || dogumTarihi == null || bolumDegistir == null) {
        	MessageBox.showMessage("Öğrenci Bilgi Sistemi", "Lütfen tüm alanları doldurunuz.", "warning");
            return;
        }

        OgrenciModel yeniOgrenci = new OgrenciModel(0, bolumDegistir.getBolumId(), ogrNo, adi, soyadi, tcNo, cinsiyet, dogumTarihi);
        OgrenciRepository.insertOgrenci(yeniOgrenci);

        ogrenciList.add(yeniOgrenci);
        tbl_Ogrenci.refresh();
        
        filterOgrencilerByBolum(cb_BolumSec.getValue());

        clearFields();
    }

    @FXML
    void btn_Guncelle_Click(ActionEvent event) {
        OgrenciModel seciliOgrenci = tbl_Ogrenci.getSelectionModel().getSelectedItem();

        if (seciliOgrenci != null) {
            String ogrNo = txt_OgrenciNo.getText();
            String adi = txt_Adi.getText();
            String soyadi = txt_Soyadi.getText();
            String tcNo = txt_TcNo.getText();
            String cinsiyet = cb_Cinsiyet.getValue();
            LocalDate dogumTarihi = dt_DogumTarihi.getValue();
            java.sql.Date sqlDogumTarihi = java.sql.Date.valueOf(dogumTarihi);
            BolumModel bolumDegistir = cb_BolumDegistir.getValue();

            if (ogrNo.isEmpty() || adi.isEmpty() || soyadi.isEmpty() || tcNo.isEmpty() || cinsiyet.isEmpty() || dogumTarihi == null || bolumDegistir == null) {
            	MessageBox.showMessage("Öğrenci Bilgi Sistemi", "Lütfen tüm alanları doldurunuz.", "warning");
                return;
            }

            seciliOgrenci.setOgrNo(ogrNo);
            seciliOgrenci.setAdi(adi);
            seciliOgrenci.setSoyadi(soyadi);
            seciliOgrenci.setTcNo(tcNo);
            seciliOgrenci.setCinsiyet(cinsiyet);
            seciliOgrenci.setDogumTarihi(sqlDogumTarihi);
            seciliOgrenci.setBolumId(bolumDegistir.getBolumId());

            OgrenciRepository.updateOgrenci(seciliOgrenci);

            tbl_Ogrenci.refresh();
            
            filterOgrencilerByBolum(cb_BolumSec.getValue());

            clearFields();
        }
        else {
        	MessageBox.showMessage("Öğrenci Bilgi Sistemi", "Güncellenmek istenen nesne seçili değil.", "warning");
        }
    }

    @FXML
    void btn_Sil_Click(ActionEvent event) {
        OgrenciModel seciliOgrenci = tbl_Ogrenci.getSelectionModel().getSelectedItem();

        if (seciliOgrenci != null) {
            OgrenciRepository.deleteOgrenci(seciliOgrenci);

            ogrenciList.remove(seciliOgrenci);
            tbl_Ogrenci.refresh();
            
            filterOgrencilerByBolum(cb_BolumSec.getValue());

            clearFields();
        }
        else {
        	MessageBox.showMessage("Öğrenci Bilgi Sistemi", "Silinmek istenen nesne seçili değil.", "warning");
        }
    }

    @FXML
    void initialize() {
    	ogrenciList = FXCollections.observableArrayList();
        loadChoiceBox();
        setupTable();
        loadData();
    }
    
    private void filterOgrencilerByBolum(BolumModel selectedBolum) {
    	if (selectedBolum == null) {
            // Bölüm seçilmemiş, tüm öğrencileri göster
            tbl_Ogrenci.setItems(ogrenciList);
        } else {
            // Bölüm filtresi uygula
            ObservableList<OgrenciModel> filteredList = FXCollections.observableArrayList();
            for (OgrenciModel ogrenci : ogrenciList) {
                if (ogrenci.getBolumId() == selectedBolum.getBolumId()) {
                    filteredList.add(ogrenci);
                }
            }
            tbl_Ogrenci.setItems(filteredList);
        }
    }


    private void loadChoiceBox() {
    	bolumList = BolumRepository.getAllBolumler();
        cb_BolumSec.setItems(bolumList);
        cb_BolumDegistir.setItems(bolumList);

        ObservableList<String> cinsiyetList = FXCollections.observableArrayList("E", "K");
        cb_Cinsiyet.setItems(cinsiyetList);
        
        cb_BolumSec.setOnAction(event -> {
            BolumModel selectedBolum = cb_BolumSec.getValue();
            if (selectedBolum != null) {
                filterOgrencilerByBolum(selectedBolum);
            } else {
                loadData();
            }
        });
        dt_DogumTarihi.setValue(LocalDate.of(2000, Month.JANUARY, 1));
    }

    private void setupTable() {
        tbl_No.setCellValueFactory(new PropertyValueFactory<>("ogrNo"));
        tbl_Adi.setCellValueFactory(new PropertyValueFactory<>("adi"));
        tbl_Soyadi.setCellValueFactory(new PropertyValueFactory<>("soyadi"));
        tbl_TcNo.setCellValueFactory(new PropertyValueFactory<>("tcNo"));
        tbl_Cinsiyet.setCellValueFactory(new PropertyValueFactory<>("cinsiyet"));
        tbl_DogumTarihi.setCellValueFactory(new PropertyValueFactory<>("dogumTarihi"));

        tbl_Ogrenci.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txt_OgrenciNo.setText(newSelection.getOgrNo());
                txt_Adi.setText(newSelection.getAdi());
                txt_Soyadi.setText(newSelection.getSoyadi());
                txt_TcNo.setText(newSelection.getTcNo());
                cb_Cinsiyet.setValue(newSelection.getCinsiyet());
                LocalDate dogumTarihi = newSelection.getDogumTarihi().toLocalDate();
                dt_DogumTarihi.setValue(dogumTarihi);
                cb_BolumDegistir.setValue(getBolumById(newSelection.getBolumId()));
            }
        });
    }

    private void loadData() {
        ogrenciList = OgrenciRepository.getAllOgrenciler();
        tbl_Ogrenci.setItems(ogrenciList);
    }

    private BolumModel getBolumById(int bolumId) {
        for (BolumModel bolum : bolumList) {
            if (bolum.getBolumId() == bolumId) {
                return bolum;
            }
        }
        return null;
    }

    private void clearFields() {
        txt_OgrenciNo.clear();
        txt_Adi.clear();
        txt_Soyadi.clear();
        txt_TcNo.clear();
        cb_Cinsiyet.getSelectionModel().clearSelection();
        dt_DogumTarihi.setValue(LocalDate.of(2000, Month.JANUARY, 1));
        cb_BolumDegistir.getSelectionModel().clearSelection();
        tbl_Ogrenci.getSelectionModel().clearSelection();
    }
}

