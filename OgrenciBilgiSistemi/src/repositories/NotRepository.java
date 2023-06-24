package repositories;

import com.MySQL.Util.DatabaseUtil;
import dialogs.MessageBox;
import models.NotlarModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NotRepository {
	
	public static ObservableList<NotlarModel> getAllNotlar() {
	    ObservableList<NotlarModel> notlarList = FXCollections.observableArrayList();

	    try (Connection conn = DatabaseUtil.Baglan();
	         PreparedStatement statement = conn.prepareStatement("SELECT * FROM notlar")) {

	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            int notlarId = resultSet.getInt("notlarId");
	            int ogrenciId = resultSet.getInt("ogrenciId");
	            int dersId = resultSet.getInt("dersId");
	            double vizeNotlar = resultSet.getDouble("vizeNotlar");
	            double finalNotlar = resultSet.getDouble("finalNotlar");
	            double ortNotlar = resultSet.getDouble("ortNotlar");

	            NotlarModel notlar = new NotlarModel(notlarId, ogrenciId, dersId, vizeNotlar, finalNotlar, ortNotlar);
	            notlarList.add(notlar);
	        }

	        resultSet.close();
	    } catch (SQLException e) {
	    	MessageBox.showMessage("Hata", "Notlar alınırken hata oluştu: " + e.getMessage(), "error");
	    }

	    return notlarList;
	}
	
	public static void insertNot(NotlarModel not) {
        try (Connection conn = DatabaseUtil.Baglan();
             PreparedStatement statement = conn.prepareStatement(
                     "INSERT INTO notlar (ogrenciId, dersId, vizeNotlar, finalNotlar, ortNotlar) VALUES (?, ?, ?, ?, ?)")) {

            statement.setInt(1, not.getOgrenciId());
            statement.setInt(2, not.getDersId());
            statement.setDouble(3, not.getVizeNotlar());
            statement.setDouble(4, not.getFinalNotlar());
            statement.setDouble(5, not.getOrtNotlar());

            statement.executeUpdate();

            MessageBox.showMessage("Öğrenci Bilgi Sistemi", "Not ekleme işlemi başarılı.");
        } catch (SQLException e) {
        	MessageBox.showMessage("Hata", "Not ekleme işlemi sırasında hata oluştu: " + e.getMessage(), "error");
        }
    }

    public static void updateNot(NotlarModel not) {
        try (Connection conn = DatabaseUtil.Baglan();
             PreparedStatement statement = conn.prepareStatement(
                     "UPDATE notlar SET ogrenciId = ?, dersId = ?, vizeNotlar = ?, finalNotlar = ?, ortNotlar = ? WHERE notlarId = ?")) {

            statement.setInt(1, not.getOgrenciId());
            statement.setInt(2, not.getDersId());
            statement.setDouble(3, not.getVizeNotlar());
            statement.setDouble(4, not.getFinalNotlar());
            statement.setDouble(5, not.getOrtNotlar());
            statement.setInt(6, not.getNotlarId());

            statement.executeUpdate();

            MessageBox.showMessage("Öğrenci Bilgi Sistemi", "Not güncelleme işlemi başarılı.");
        } catch (SQLException e) {
        	MessageBox.showMessage("Hata", "Not güncelleme işlemi sırasında hata oluştu: " + e.getMessage(), "error");
        }
    }

    public static void deleteNot(NotlarModel not) {
        try (Connection conn = DatabaseUtil.Baglan();
             PreparedStatement statement = conn.prepareStatement(
                     "DELETE FROM notlar WHERE notlarId = ?")) {

            statement.setInt(1, not.getNotlarId());

            statement.executeUpdate();

            MessageBox.showMessage("Öğrenci Bilgi Sistemi", "Not silme işlemi başarılı.");
        } catch (SQLException e) {
        	MessageBox.showMessage("Hata", "Not silme işlemi sırasında hata oluştu: " + e.getMessage(), "error");
        }
    }
}

