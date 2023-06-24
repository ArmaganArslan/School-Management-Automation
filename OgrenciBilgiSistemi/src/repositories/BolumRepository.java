package repositories;

import com.MySQL.Util.DatabaseUtil;
import dialogs.MessageBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.BolumModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BolumRepository {

    public static ObservableList<BolumModel> getAllBolumler() {
    	ObservableList<BolumModel> bolumList = FXCollections.observableArrayList();

        try {
            Connection connection = DatabaseUtil.Baglan();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM bolum");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int bolumId = resultSet.getInt("bolumId");
                String adi = resultSet.getString("adi");
                BolumModel bolum = new BolumModel(bolumId, adi);
                bolumList.add(bolum);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            MessageBox.showMessage("Hata", "Bölümleri alırken hata oluştu: " + e.getMessage(), "error");
        }

        return bolumList;
    }

    public static void insertBolum(BolumModel bolum) {
        try {
            Connection connection = DatabaseUtil.Baglan();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO bolum (adi) VALUES (?)");
            statement.setString(1, bolum.getAdi());
            statement.executeUpdate();
            statement.close();
            connection.close();
            
            MessageBox.showMessage("Öğrenci Bilgi Sistemi", "Bölüm başarıyla eklendi.");
        } catch (SQLException e) {
            e.printStackTrace();
            MessageBox.showMessage("Hata", "Bölüm eklenirken hata oluştu: " + e.getMessage(), "error");
        }
    }

    public static void updateBolum(BolumModel bolum) {
        try {
            Connection connection = DatabaseUtil.Baglan();
            PreparedStatement statement = connection.prepareStatement("UPDATE bolum SET adi = ? WHERE bolumId = ?");
            statement.setString(1, bolum.getAdi());
            statement.setInt(2, bolum.getBolumId());
            statement.executeUpdate();
            statement.close();
            connection.close();
            
            MessageBox.showMessage("Öğrenci Bilgi Sistemi", "Bölüm başarıyla güncellendi.");
        } catch (SQLException e) {
            e.printStackTrace();
            MessageBox.showMessage("Hata", "Bölüm güncellenirken hata oluştu: " + e.getMessage(), "error");
        }
    }

    public static void deleteBolum(BolumModel bolum) {
        try {
            Connection connection = DatabaseUtil.Baglan();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM bolum WHERE bolumId = ?");
            statement.setInt(1, bolum.getBolumId());
            statement.executeUpdate();
            statement.close();
            connection.close();
            
            MessageBox.showMessage("Öğrenci Bilgi Sistemi", "Bölüm başarıyla silindi.");
        } catch (SQLException e) {
            e.printStackTrace();
            MessageBox.showMessage("Hata", "Bölüm silinirken hata oluştu: " + e.getMessage(), "error");
        }
    }
    
    public static BolumModel getBolumById(int bolumId) {
        BolumModel bolum = null;

        try (Connection conn = DatabaseUtil.Baglan();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM bolum WHERE bolumId = ?")) {

            statement.setInt(1, bolumId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("bolumId");
                String adi = resultSet.getString("adi");

                bolum = new BolumModel(id, adi);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
        	MessageBox.showMessage("Hata", "Bölüm alınırken hata oluştu: " + e.getMessage(), "error");
        }

        return bolum;
    }
}
