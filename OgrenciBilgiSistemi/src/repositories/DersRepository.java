package repositories;

import com.MySQL.Util.DatabaseUtil;
import dialogs.MessageBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.BolumModel;
import models.DersModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DersRepository {
	
    public static boolean insertDers(DersModel ders) {
        try {
            Connection conn = DatabaseUtil.Baglan();

            String sql = "INSERT INTO ders (bolumId, adi) VALUES (?, ?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, ders.getBolumId());
            statement.setString(2, ders.getAdi());

            statement.executeUpdate();
            statement.close();
            conn.close();

            MessageBox.showMessage("Öğrenci Bilgi Sistemi", "Ders başarıyla eklendi.");
        } catch (SQLException e) {
        	MessageBox.showMessage("Hata", "Ders eklenirken hata oluştu: " + e.getMessage(), "error");
        }
		return false;
    }

    public static void updateDers(DersModel ders) {
        try {
            Connection conn = DatabaseUtil.Baglan();

            String sql = "UPDATE ders SET bolumId = ?, adi = ? WHERE dersId = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, ders.getBolumId());
            statement.setString(2, ders.getAdi());
            statement.setInt(3, ders.getDersId());

            statement.executeUpdate();
            statement.close();
            conn.close();

            MessageBox.showMessage("Öğrenci Bilgi Sistemi", "Ders başarıyla güncellendi.");
        } catch (SQLException e) {
        	MessageBox.showMessage("Hata", "Ders güncellenirken hata oluştu: " + e.getMessage(), "error");
        }
    }

    public static void deleteDers(DersModel ders) {
        try {
            Connection conn = DatabaseUtil.Baglan();

            String sql = "DELETE FROM ders WHERE dersId = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, ders.getDersId());

            statement.executeUpdate();
            statement.close();
            conn.close();

            MessageBox.showMessage("Öğrenci Bilgi Sistemi", "Ders başarıyla silindi.");
        } catch (SQLException e) {
        	MessageBox.showMessage("Hata", "Ders silinirken hata oluştu: " + e.getMessage(), "error");
        }
    }

    public static ObservableList<DersModel> getAllDersler() {
        ObservableList<DersModel> dersList = FXCollections.observableArrayList();

        try {
            Connection conn = DatabaseUtil.Baglan();
            String sql = "SELECT * FROM ders";
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int dersId = resultSet.getInt("dersId");
                int bolumId = resultSet.getInt("bolumId");
                String adi = resultSet.getString("adi");

                BolumModel bolum = BolumRepository.getBolumById(bolumId);
                
                DersModel ders = new DersModel(dersId, bolumId, adi, bolum);
                ders.setBolum(bolum);

                dersList.add(ders);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
        	MessageBox.showMessage("Hata", "Dersleri alırken hata oluştu: " + e.getMessage(), "error");
        }

        return dersList;
    }
    
    public static String getDersAdiByDersId(int dersId) {
        String dersAdi = "";
        try {
            Connection conn = DatabaseUtil.Baglan();

            String sql = "SELECT adi FROM ders WHERE dersId = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, dersId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                dersAdi = resultSet.getString("adi");
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Ders adı alınırken hata oluştu: " + e.getMessage());
        }
        return dersAdi;
    }
    
    public static DersModel getDersById(int dersId) {
        DersModel ders = null;
        try {
            Connection conn = DatabaseUtil.Baglan();

            String sql = "SELECT * FROM ders WHERE dersId = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, dersId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int bolumId = resultSet.getInt("bolumId");
                String adi = resultSet.getString("adi");

                BolumModel bolum = BolumRepository.getBolumById(bolumId);
                ders = new DersModel(dersId, bolumId, adi, bolum);
                ders.setBolum(bolum);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Ders alınırken hata oluştu: " + e.getMessage());
        }
        return ders;
    }

    
    public static ObservableList<DersModel> getDersListByBolumId(int bolumId) {
        ObservableList<DersModel> dersList = FXCollections.observableArrayList();
        try {
            Connection conn = DatabaseUtil.Baglan();

            String sql = "SELECT * FROM ders WHERE bolumId = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, bolumId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                DersModel ders = new DersModel(bolumId, bolumId, sql, null);
                ders.setDersId(resultSet.getInt("dersId"));
                ders.setBolumId(resultSet.getInt("bolumId"));
                ders.setAdi(resultSet.getString("adi"));
                dersList.add(ders);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Ders listesi alınırken hata oluştu: " + e.getMessage());
        }
        return dersList;
    }

}
