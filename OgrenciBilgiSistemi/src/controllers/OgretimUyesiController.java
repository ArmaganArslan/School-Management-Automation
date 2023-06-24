package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import dialogs.MessageBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;
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
import models.BolumModel;
import models.DersModel;
import models.OgretimUyesiModel;
import repositories.BolumRepository;
import repositories.DersRepository;
import repositories.OgretimUyesiRepository;

public class OgretimUyesiController {

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
    private Button btn_TumOgrtUye;

    @FXML
    private ChoiceBox<BolumModel> cb_BolumDegistir;
    
    @FXML
    private ChoiceBox<DersModel> cb_DersDegistir;

    @FXML
    private ChoiceBox<BolumModel> cb_BolumSec;

    @FXML
    private ChoiceBox<String> cb_Cinsiyet;

    @FXML
    private ChoiceBox<DersModel> cb_DersSec;

    @FXML
    private DatePicker dt_DogumTarihi;

    @FXML
    private TableColumn<OgretimUyesiModel, String> tbl_Adi;

    @FXML
    private TableColumn<OgretimUyesiModel, String> tbl_Cinsiyet;

    @FXML
    private TableColumn<OgretimUyesiModel, String> tbl_Ders;

    @FXML
    private TableColumn<OgretimUyesiModel, String> tbl_No;

    @FXML
    private TableView<OgretimUyesiModel> tbl_OgretimUyesi;

    @FXML
    private TableColumn<OgretimUyesiModel, String> tbl_Soyadi;

    @FXML
    private TableColumn<OgretimUyesiModel, String> tbl_TcNo;

    @FXML
    private TextField txt_Adi;

    @FXML
    private TextField txt_OgrtUyeNo;

    @FXML
    private TextField txt_Soyadi;

    @FXML
    private TextField txt_TcNo;
    
    private ObservableList<BolumModel> bolumList;
    
    private ObservableList<DersModel> dersList;
    
    private ObservableList<OgretimUyesiModel> ogretimUyesiList;
    
    private void loadData() {
    	ogretimUyesiList = OgretimUyesiRepository.getAllOgretimUyeleri();
    	tbl_OgretimUyesi.setItems(ogretimUyesiList);
    }
    
    private void filterOgretimUyeleriByBolum(BolumModel selectedBolum) {
    	if (selectedBolum == null) {
            tbl_OgretimUyesi.setItems(ogretimUyesiList);
        } else {
            ObservableList<OgretimUyesiModel> filteredList = FXCollections.observableArrayList();
            for (OgretimUyesiModel ogretimUyesi : ogretimUyesiList) {
                if (ogretimUyesi.getBolumId() == selectedBolum.getBolumId()) {
                    filteredList.add(ogretimUyesi);
                }
            }
            tbl_OgretimUyesi.setItems(filteredList);
        }
    }
    
    private void filterOgretimUyeleriByDers(DersModel selectedDers) {
        if (selectedDers == null) {
            tbl_OgretimUyesi.setItems(ogretimUyesiList);
        } else {
            ObservableList<OgretimUyesiModel> filteredList = FXCollections.observableArrayList();
            for (OgretimUyesiModel ogretimUyesi : ogretimUyesiList) {
                if (ogretimUyesi.getDersId() == selectedDers.getDersId()) {
                    filteredList.add(ogretimUyesi);
                }
            }
            tbl_OgretimUyesi.setItems(filteredList);
        }
    }

    
    private void updateDersSecChoiceBox(int selectedBolumId) {
        ObservableList<DersModel> filteredDersList = DersRepository.getDersListByBolumId(selectedBolumId);
        cb_DersSec.setItems(filteredDersList);
    }

    
    private void setupTable() {
        tbl_Adi.setCellValueFactory(new PropertyValueFactory<>("adi"));
        tbl_Soyadi.setCellValueFactory(new PropertyValueFactory<>("soyadi"));
        tbl_Ders.setCellValueFactory(cellData -> new SimpleStringProperty(DersRepository.getDersAdiByDersId(cellData.getValue().getDersId())));
        tbl_No.setCellValueFactory(new PropertyValueFactory<>("ogrtUyeNo"));
        tbl_Cinsiyet.setCellValueFactory(new PropertyValueFactory<>("cinsiyet"));
        tbl_TcNo.setCellValueFactory(new PropertyValueFactory<>("tcNo"));
        
        tbl_OgretimUyesi.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txt_Adi.setText(newSelection.getAdi());
                txt_Soyadi.setText(newSelection.getSoyadi());
                LocalDate dogumTarihi = newSelection.getDogumTarihi().toLocalDate();
                dt_DogumTarihi.setValue(dogumTarihi);
                txt_OgrtUyeNo.setText(newSelection.getOgrtUyeNo());
                cb_Cinsiyet.setValue(newSelection.getCinsiyet());
                txt_TcNo.setText(newSelection.getTcNo());
                cb_BolumDegistir.setValue(BolumRepository.getBolumById(newSelection.getBolumId()));
                cb_DersDegistir.setValue(DersRepository.getDersById(newSelection.getDersId()));
            }
        });
    }
    
    @FXML
    void initialize() {
        loadChoiceBox();
        setupTable();
        loadData();
    }
    
    private void filterDersDegistirByBolumDegistir(ChoiceBox<BolumModel> bolumChoiceBox, ChoiceBox<DersModel> dersChoiceBox) {
        BolumModel selectedBolum = bolumChoiceBox.getValue();
        if (selectedBolum == null) {
            dersChoiceBox.setItems(dersList);
        } else {
            ObservableList<DersModel> filteredList = FXCollections.observableArrayList();
            for (DersModel ders : dersList) {
                if (ders.getBolumId() == selectedBolum.getBolumId()) {
                    filteredList.add(ders);
                }
            }
            dersChoiceBox.setItems(filteredList);
        }
    }

    
    private void loadChoiceBox() {
        bolumList = BolumRepository.getAllBolumler();
        dersList = DersRepository.getAllDersler();
        
        ObservableList<String> cinsiyetList = FXCollections.observableArrayList("E", "K");
        cb_Cinsiyet.setItems(cinsiyetList);
        
        cb_BolumSec.setItems(bolumList);
        cb_BolumDegistir.setItems(bolumList);
        cb_DersSec.setItems(dersList);
        cb_DersDegistir.setItems(dersList);
        
        cb_BolumSec.setOnAction(event -> {
            BolumModel selectedBolum = cb_BolumSec.getValue();
            if (selectedBolum != null) {
                filterOgretimUyeleriByBolum(selectedBolum);
                updateDersSecChoiceBox(selectedBolum.getBolumId());
            } else {
                loadData();
                cb_DersSec.setItems(dersList);
            }
        });
        
        cb_DersSec.setOnAction(event -> {
            DersModel selectedDers = cb_DersSec.getValue();
            if (selectedDers != null) {
                filterOgretimUyeleriByDers(selectedDers);
            } else {
                loadData();
            }
        });
        
        cb_BolumDegistir.setOnAction(event -> {
            filterDersDegistirByBolumDegistir(cb_BolumDegistir, cb_DersDegistir);
        });
        
        dt_DogumTarihi.setValue(LocalDate.of(2000, Month.JANUARY, 1));

    }

    
    @FXML
    void btn_Ekle_Click(ActionEvent event) {
    	String ogrtUyeNo = txt_OgrtUyeNo.getText();
        String adi = txt_Adi.getText();
        String soyadi = txt_Soyadi.getText();
        String tcNo = txt_TcNo.getText();
        String cinsiyet = cb_Cinsiyet.getValue();
        LocalDate localDate = dt_DogumTarihi.getValue();
        java.sql.Date dogumTarihi = java.sql.Date.valueOf(localDate);
        BolumModel bolumDegistir = cb_BolumDegistir.getValue();
        DersModel dersDegistir = cb_DersDegistir.getValue();

        if (ogrtUyeNo.isEmpty() || adi.isEmpty() || soyadi.isEmpty() || tcNo.isEmpty() || cinsiyet.isEmpty() || dogumTarihi == null || bolumDegistir == null) {
        	MessageBox.showMessage("Öğrenci Bilgi Sistemi", "Lütfen tüm alanları doldurunuz.", "warning");
            return;
        }
        
        OgretimUyesiModel yeniOgretimUyesi = new OgretimUyesiModel(0, bolumDegistir.getBolumId(), dersDegistir.getDersId(), ogrtUyeNo, adi, soyadi, dogumTarihi, cinsiyet, tcNo);

        OgretimUyesiRepository.insertOgretimUyesi(yeniOgretimUyesi);

        ogretimUyesiList.add(yeniOgretimUyesi);
        tbl_OgretimUyesi.refresh();
        
        filterOgretimUyeleriByBolum(cb_BolumSec.getValue());
        filterOgretimUyeleriByDers(cb_DersSec.getValue());

        clearFields();
    }

    @FXML
    void btn_Guncelle_Click(ActionEvent event) {
    	OgretimUyesiModel seciliOgretimUyesi = tbl_OgretimUyesi.getSelectionModel().getSelectedItem();

        if (seciliOgretimUyesi != null) {
            String ogrtUyeNo = txt_OgrtUyeNo.getText();
            String adi = txt_Adi.getText();
            String soyadi = txt_Soyadi.getText();
            String tcNo = txt_TcNo.getText();
            String cinsiyet = cb_Cinsiyet.getValue();
            LocalDate dogumTarihi = dt_DogumTarihi.getValue();
            java.sql.Date sqlDogumTarihi = java.sql.Date.valueOf(dogumTarihi);
            BolumModel bolumDegistir = cb_BolumDegistir.getValue();

            if (ogrtUyeNo.isEmpty() || adi.isEmpty() || soyadi.isEmpty() || tcNo.isEmpty() || cinsiyet.isEmpty() || dogumTarihi == null || bolumDegistir == null) {
                System.out.println("Lütfen tüm alanları doldurun.");
                return;
            }

            seciliOgretimUyesi.setOgrtUyeNo(ogrtUyeNo);
            seciliOgretimUyesi.setAdi(adi);
            seciliOgretimUyesi.setSoyadi(soyadi);
            seciliOgretimUyesi.setTcNo(tcNo);
            seciliOgretimUyesi.setCinsiyet(cinsiyet);
            seciliOgretimUyesi.setDogumTarihi(sqlDogumTarihi);
            seciliOgretimUyesi.setBolumId(bolumDegistir.getBolumId());

            OgretimUyesiRepository.updateOgretimUyesi(seciliOgretimUyesi);

            tbl_OgretimUyesi.refresh();
            
            filterOgretimUyeleriByBolum(cb_BolumSec.getValue());
            filterOgretimUyeleriByDers(cb_DersSec.getValue());

            clearFields();
        } else {
        	MessageBox.showMessage("Öğrenci Bilgi Sistemi", "Güncellenmek istenen nesne seçili değil", "warning");
        }
    }

    @FXML
    void btn_Sil_Click(ActionEvent event) {
        OgretimUyesiModel seciliOgretimUyesi = tbl_OgretimUyesi.getSelectionModel().getSelectedItem();
        
        if (seciliOgretimUyesi != null) {
        	OgretimUyesiRepository.deleteOgretimUyesi(seciliOgretimUyesi);
        	
        	ogretimUyesiList.remove(seciliOgretimUyesi);
        	tbl_OgretimUyesi.refresh();
        	
        	filterOgretimUyeleriByBolum(cb_BolumSec.getValue());
        	filterOgretimUyeleriByDers(cb_DersSec.getValue());
        	
        	clearFields();
        } else {
        	MessageBox.showMessage("Öğrenci Bilgi Sistemi", "Silinmek istenen nesne seçili değil", "warning");
        }
    }

    @FXML
    void btn_Temizle_Click(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btn_TumOgrtUye_Click(ActionEvent event) {
        cb_BolumSec.getSelectionModel().clearSelection();
        loadData();
    }
    
    private void clearFields() {
        txt_OgrtUyeNo.clear();
        txt_Adi.clear();
        txt_Soyadi.clear();
        txt_TcNo.clear();
        cb_Cinsiyet.getSelectionModel().clearSelection();
        dt_DogumTarihi.setValue(LocalDate.of(2000, Month.JANUARY, 1));
        cb_BolumDegistir.setValue(null);
        cb_DersDegistir.setValue(null);
        tbl_OgretimUyesi.getSelectionModel().clearSelection();
    }
}

