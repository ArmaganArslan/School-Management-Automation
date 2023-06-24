package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import dialogs.MessageBox;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.BolumModel;
import models.DersModel;
import models.NotlarModel;
import models.OgrenciModel;
import repositories.BolumRepository;
import repositories.DersRepository;
import repositories.NotRepository;
import repositories.OgrenciRepository;

public class NotController {

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
    private ChoiceBox<BolumModel> cb_BolumSec;

    @FXML
    private ChoiceBox<DersModel> cb_DersSec;

    @FXML
    private ChoiceBox<OgrenciModel> cb_OgrenciSec;

    @FXML
    private TableColumn<NotlarModel, String> tbl_DersAdi;

    @FXML
    private TableColumn<NotlarModel, String> tbl_Final;

    @FXML
    private TableView<NotlarModel> tbl_Not;

    @FXML
    private TableColumn<NotlarModel, String> tbl_OgrNo;

    @FXML
    private TableColumn<NotlarModel, String> tbl_Ort;

    @FXML
    private TableColumn<NotlarModel, String> tbl_Vize;
    
    @FXML
    private TextField txt_Vize;
    
    @FXML
    private TextField txt_Final;
    
    private ObservableList<NotlarModel> notlarList;
    
    private ObservableList<BolumModel> bolumList;
    
    private ObservableList<DersModel> dersList;
    
    private ObservableList<OgrenciModel> ogrenciList;
    
    private void setupTable() {
    	tbl_OgrNo.setCellValueFactory(cellData -> new SimpleStringProperty(OgrenciRepository.getOgrenciNoByOgrenciId(cellData.getValue().getOgrenciId())));
    	tbl_DersAdi.setCellValueFactory(cellData -> new SimpleStringProperty(DersRepository.getDersAdiByDersId(cellData.getValue().getDersId())));
    	tbl_Vize.setCellValueFactory(new PropertyValueFactory<>("vizeNotlar"));
    	tbl_Final.setCellValueFactory(new PropertyValueFactory<>("finalNotlar"));
    	tbl_Ort.setCellValueFactory(new PropertyValueFactory<>("ortNotlar"));
    	
    	tbl_Not.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
            	cb_BolumSec.setValue(BolumRepository.getBolumById(DersRepository.getDersById(newSelection.getDersId()).getBolumId()));
            	cb_OgrenciSec.setValue(OgrenciRepository.getOgrenciById(newSelection.getOgrenciId()));
                cb_DersSec.setValue(DersRepository.getDersById(newSelection.getDersId()));
                txt_Vize.setText(Double.toString(newSelection.getVizeNotlar()));
                txt_Final.setText(Double.toString(newSelection.getFinalNotlar()));
            }
        });
    }
    
    private void filterOgrencilerByBolum(BolumModel selectedBolum) {
    	if (selectedBolum == null) {
            cb_OgrenciSec.setItems(ogrenciList);
        } else {
            ObservableList<OgrenciModel> filteredList = FXCollections.observableArrayList();
            for (OgrenciModel ogrenci : ogrenciList) {
                if (ogrenci.getBolumId() == selectedBolum.getBolumId()) {
                    filteredList.add(ogrenci);
                }
            }
            cb_OgrenciSec.setItems(filteredList);
        }
    }
    
    private void updateDersSecChoiceBox(int selectedBolumId) {
        ObservableList<DersModel> filteredDersList = DersRepository.getDersListByBolumId(selectedBolumId);
        cb_DersSec.setItems(filteredDersList);
    }
    
    private void loadChoiceBox() {
    	bolumList = BolumRepository.getAllBolumler();
        dersList = DersRepository.getAllDersler();
        ogrenciList = OgrenciRepository.getAllOgrenciler();
        
        cb_BolumSec.setItems(bolumList);
        cb_OgrenciSec.setItems(ogrenciList);
        cb_DersSec.setItems(dersList);
        
        cb_BolumSec.setOnAction(event -> {
            BolumModel selectedBolum = cb_BolumSec.getValue();
            if (selectedBolum != null) {
                filterOgrencilerByBolum(selectedBolum);
                updateDersSecChoiceBox(selectedBolum.getBolumId());
            } else {
                loadData();
                cb_DersSec.setItems(dersList);
            }
        });
    }
    
    private void loadData() {
    	notlarList = NotRepository.getAllNotlar();
    	tbl_Not.setItems(notlarList);
    }

    @FXML
    void btn_Ekle_Click(ActionEvent event) {
    	OgrenciModel selectedOgrenci = cb_OgrenciSec.getValue();
        DersModel selectedDers = cb_DersSec.getValue();
        double vizeNot = 0.0;
        double finalNot = 0.0;

        if (selectedOgrenci != null && selectedDers != null) {
            if (selectedOgrenci != null && selectedDers != null && !txt_Vize.getText().isEmpty()) {
                vizeNot = Double.parseDouble(txt_Vize.getText());
            } else {
                MessageBox.showMessage("Öğrenci Bilgi Sistemi", "Lütfen Vize notunu giriniz.", "warning");
                return;
            }
            if (selectedOgrenci != null && selectedDers != null && !txt_Final.getText().isEmpty()) {
                finalNot = Double.parseDouble(txt_Final.getText());
            } else {
                MessageBox.showMessage("Öğrenci Bilgi Sistemi", "Lütfen Final notunu giriniz.", "warning");
                return;
            }
            
            double ortNot = (vizeNot * 0.4) + (finalNot * 0.6);

            NotlarModel notlar = new NotlarModel(0, selectedOgrenci.getOgrenciId(), selectedDers.getDersId(),
                    vizeNot, finalNot, ortNot);

            NotRepository.insertNot(notlar);
            loadData();
            clearFields();
        } else {
            MessageBox.showMessage("Öğrenci Bilgi Sistemi", "Lütfen tüm alanları doldurunuz.", "warning");
        }
    }

    @FXML
    void btn_Guncelle_Click(ActionEvent event) {
        NotlarModel selectedNot = tbl_Not.getSelectionModel().getSelectedItem();
        OgrenciModel selectedOgrenci = cb_OgrenciSec.getValue();
        DersModel selectedDers = cb_DersSec.getValue();
        double vizeNot = 0.0;
        double finalNot = 0.0;

        if (selectedNot != null && selectedOgrenci != null && selectedDers != null) {
            if (selectedOgrenci != null && selectedDers != null && !txt_Vize.getText().isEmpty()) {
                vizeNot = Double.parseDouble(txt_Vize.getText());
                finalNot = selectedNot.getFinalNotlar();

                // Vize notunu güncelle
                selectedNot.setVizeNotlar(vizeNot);
            }
            
            else {
            	MessageBox.showMessage("Öğrenci Bilgi Sistemi", "Lütfen Vize notunu giriniz.", "warning");
                return;
            }
            
            if (selectedOgrenci != null && selectedDers != null && !txt_Final.getText().isEmpty()) {
                vizeNot = selectedNot.getVizeNotlar();
                finalNot = Double.parseDouble(txt_Final.getText());

                // Final notunu güncelle
                selectedNot.setFinalNotlar(finalNot);
            }
            
            else {
            	MessageBox.showMessage("Öğrenci Bilgi Sistemi", "Lütfen Final notunu giriniz.", "warning");
                return;
            }
            
            double ortNot = (vizeNot * 0.4) + (finalNot * 0.6);

            NotlarModel updatedNot = new NotlarModel(selectedNot.getNotlarId(), selectedOgrenci.getOgrenciId(),
                    selectedDers.getDersId(), vizeNot, finalNot, ortNot);

            NotRepository.updateNot(updatedNot);
            loadData();
            clearFields();
        }
        
        else {
            MessageBox.showMessage("Öğrenci Bilgi Sistemi", "Lütfen tüm alanları doldurunuz.", "warning");
        }
    }


    @FXML
    void btn_Sil_Click(ActionEvent event) {
    	NotlarModel selectedNot = tbl_Not.getSelectionModel().getSelectedItem();
        if (selectedNot != null) {
            NotRepository.deleteNot(selectedNot);
            loadData();
            clearFields();
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
    	cb_BolumSec.getSelectionModel().clearSelection();
    	cb_BolumSec.setValue(null);
    	cb_OgrenciSec.setValue(null);
    	cb_DersSec.setValue(null);
    	txt_Vize.clear();
    	txt_Final.clear();
    	tbl_Not.getSelectionModel().clearSelection();
    	cb_OgrenciSec.setItems(ogrenciList);
        cb_DersSec.setItems(dersList);
    }

    @FXML
    void initialize() {
    	loadChoiceBox();
    	setupTable();
    	loadData();
    }
}
