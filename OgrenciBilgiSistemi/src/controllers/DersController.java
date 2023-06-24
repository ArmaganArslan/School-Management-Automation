package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import dialogs.MessageBox;
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
import repositories.DersRepository;
import repositories.BolumRepository;

public class DersController {

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
    private Button btn_TumDersler;

    @FXML
    private ChoiceBox<BolumModel> cb_BolumDegistir;

    @FXML
    private ChoiceBox<BolumModel> cb_BolumSec;

    @FXML
    private TableColumn<DersModel, BolumModel> tbl_Bolum;

    @FXML
    private TableView<DersModel> tbl_Ders;

    @FXML
    private TableColumn<DersModel, String> tbl_DersAdi;

    @FXML
    private TextField txt_DersAdi;
    
    private ObservableList<BolumModel> bolumList;
    
    private ObservableList<DersModel> dersList;

    @FXML
    void btn_Ekle_Click(ActionEvent event) {
        String adi = txt_DersAdi.getText();
        BolumModel bolumDegistir = cb_BolumDegistir.getValue();

        if (adi.isEmpty() || bolumDegistir == null) {
        	MessageBox.showMessage("Öğrenci Bilgi Sistemi", "Lütfen tüm alanları doldurunuz.", "warning");
            return;
        }

        DersModel yeniDers = new DersModel(0, bolumDegistir.getBolumId(), adi, bolumDegistir);
        DersRepository.insertDers(yeniDers);

        dersList.add(yeniDers);
        
        setupTable();
        loadData();
        
        tbl_Ders.refresh();
        
        filterDerslerByBolum(cb_BolumSec.getValue());

        clearFields();
    }
    
    private void filterDerslerByBolum(BolumModel selectedBolum) {
    	if (selectedBolum == null) {
            tbl_Ders.setItems(dersList);
        } else {
            ObservableList<DersModel> filteredList = FXCollections.observableArrayList();
            for (DersModel ders : dersList) {
                if (ders.getBolumId() == selectedBolum.getBolumId()) {
                    filteredList.add(ders);
                }
            }
            tbl_Ders.setItems(filteredList);
        }
    }
    
    private void loadData() {
    	dersList = DersRepository.getAllDersler();
    	tbl_Ders.setItems(dersList);
    }
    
    private void loadChoiceBox() {
    	bolumList = BolumRepository.getAllBolumler();
        cb_BolumSec.setItems(bolumList);
        cb_BolumDegistir.setItems(bolumList);
        
        cb_BolumSec.setOnAction(event -> {
            BolumModel selectedBolum = cb_BolumSec.getValue();
            if (selectedBolum != null) {
                filterDerslerByBolum(selectedBolum);
            } else {
                loadData();
            }
        });
    }
    
    private void setupTable() {
   	 tbl_DersAdi.setCellValueFactory(new PropertyValueFactory<>("adi"));
   	 
   	 tbl_Bolum.setCellValueFactory(new PropertyValueFactory<>("bolum"));
   	 
     tbl_Ders.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txt_DersAdi.setText(newSelection.getAdi());
                cb_BolumDegistir.setValue(getBolumById(newSelection.getBolumId()));
            }
        });
   }

    
    private BolumModel getBolumById(int bolumId) {
        for (BolumModel bolum : bolumList) {
            if (bolum.getBolumId() == bolumId) {
                return bolum;
            }
        }
        return null;
    }

    @FXML
    void btn_Guncelle_Click(ActionEvent event) {
    	DersModel seciliDers = tbl_Ders.getSelectionModel().getSelectedItem();
    	
    	if (seciliDers != null) {
    		String adi = txt_DersAdi.getText();
    		BolumModel bolumDegistir = cb_BolumDegistir.getValue();
    		
    		if (adi.isEmpty() || bolumDegistir == null) {
    			MessageBox.showMessage("Öğrenci Bilgi Sistemi", "Lütfen tüm alanları doldurunuz.", "warning");
                return;
    		}
    		
    		seciliDers.setAdi(adi);
    		seciliDers.setBolumId(bolumDegistir.getBolumId());
    		
    		DersRepository.updateDers(seciliDers);
    		
    		setupTable();
            loadData();
    		
    		tbl_Ders.refresh();
    		
    		filterDerslerByBolum(cb_BolumSec.getValue());
    		
    		clearFields();
    	}
    	else {
    		MessageBox.showMessage("Öğrenci Bilgi Sistemi", "Güncellenmek istenen nesne seçili değil.", "warning");
    	}
    }

    @FXML
    void btn_Sil_Click(ActionEvent event) {
    	DersModel seciliDers = tbl_Ders.getSelectionModel().getSelectedItem();
    	
    	if (seciliDers != null) {
    		DersRepository.deleteDers(seciliDers);
    		
    		dersList.remove(seciliDers);
    		tbl_Ders.refresh();
    		
    		filterDerslerByBolum(cb_BolumSec.getValue());
    		
    		clearFields();
    	}
    	else {
    		MessageBox.showMessage("Öğrenci Bilgi Sistemi", "Silinmek istenen nesne seçili değil.", "warning");
    	}
    }
    
    @FXML
    void btn_TumDersler_Click(ActionEvent event) {
    	cb_BolumSec.getSelectionModel().clearSelection();
    	loadData();
    }
    
    private void clearFields() {
        txt_DersAdi.clear();
        cb_BolumDegistir.getSelectionModel().clearSelection();
        tbl_Ders.getSelectionModel().clearSelection();
    }
    
    @FXML
    void btn_Temizle_Click(ActionEvent event) {
    	clearFields();
    }

    @FXML
    void initialize() {
    	dersList = FXCollections.observableArrayList();
    	loadChoiceBox();
    	setupTable();
    	loadData();
    	filterDerslerByBolum(cb_BolumSec.getValue());
    }

}

