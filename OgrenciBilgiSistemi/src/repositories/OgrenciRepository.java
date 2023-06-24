package repositories;

import com.MySQL.Util.DatabaseUtil;
import dialogs.MessageBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.OgrenciModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OgrenciRepository {
	
    public static void insertOgrenci(OgrenciModel ogrenci) {
        try {
            Connection conn = DatabaseUtil.Baglan();

            String sql = "INSERT INTO ogrenci (bolumId, ogrNo, adi, soyadi, tcNo, cinsiyet, dogumTarihi) VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, ogrenci.getBolumId());
            statement.setString(2, ogrenci.getOgrNo());
            statement.setString(3, ogrenci.getAdi());
            statement.setString(4, ogrenci.getSoyadi());
            statement.setString(5, ogrenci.getTcNo());
            statement.setString(6, ogrenci.getCinsiyet());
            statement.setDate(7, ogrenci.getDogumTarihi());

            statement.executeUpdate();
            statement.close();
            conn.close();

            MessageBox.showMessage("Öğrenci Bilgi Sistemi", "Öğrenci başarıyla eklendi.");
        } catch (SQLException e) {
        	MessageBox.showMessage("Hata", "Öğrenci eklenirken hata oluştu: " + e.getMessage(), "error");
        }
    }

    public static void updateOgrenci(OgrenciModel ogrenci) {
        try {
            Connection conn = DatabaseUtil.Baglan();

            String sql = "UPDATE ogrenci SET bolumId = ?, ogrNo = ?, adi = ?, soyadi = ?, tcNo = ?, cinsiyet = ?, dogumTarihi = ? WHERE ogrenciId = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, ogrenci.getBolumId());
            statement.setString(2, ogrenci.getOgrNo());
            statement.setString(3, ogrenci.getAdi());
            statement.setString(4, ogrenci.getSoyadi());
            statement.setString(5, ogrenci.getTcNo());
            statement.setString(6, ogrenci.getCinsiyet());
            statement.setDate(7, ogrenci.getDogumTarihi());
            statement.setInt(8, ogrenci.getOgrenciId());

            statement.executeUpdate();
            statement.close();
            conn.close();

            MessageBox.showMessage("Öğrenci Bilgi Sistemi", "Öğrenci başarıyla güncellendi.");
        } catch (SQLException e) {
        	MessageBox.showMessage("Hata", "Öğrenci güncellenirken hata oluştu: " + e.getMessage(), "error");
        }
    }

    public static void deleteOgrenci(OgrenciModel ogrenci) {
        try {
            Connection conn = DatabaseUtil.Baglan();

            String sql = "DELETE FROM ogrenci WHERE ogrenciId = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, ogrenci.getOgrenciId());

            statement.executeUpdate();
            statement.close();
            conn.close();

            MessageBox.showMessage("Öğrenci Bilgi Sistemi", "Öğrenci başarıyla silindi.");
        } catch (SQLException e) {
        	MessageBox.showMessage("Hata", "Öğrenci silinirken hata oluştu: " + e.getMessage(), "error");
        }
    }

    public static ObservableList<OgrenciModel> getAllOgrenciler() {
        ObservableList<OgrenciModel> ogrenciList = FXCollections.observableArrayList();

        try {
            Connection conn = DatabaseUtil.Baglan();
            String sql = "SELECT * FROM ogrenci";
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int ogrenciId = resultSet.getInt("ogrenciId");
                int bolumId = resultSet.getInt("bolumId");
                String ogrNo = resultSet.getString("ogrNo");
                String adi = resultSet.getString("adi");
                String soyadi = resultSet.getString("soyadi");
                String tcNo = resultSet.getString("tcNo");
                String cinsiyet = resultSet.getString("cinsiyet");
                java.sql.Date dogumTarihi = resultSet.getDate("dogumTarihi");

                OgrenciModel ogrenci = new OgrenciModel(ogrenciId, bolumId, ogrNo, adi, soyadi, tcNo, cinsiyet, dogumTarihi);
                ogrenciList.add(ogrenci);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
        	MessageBox.showMessage("Hata", "Öğrencileri alırken hata oluştu: " + e.getMessage(), "error");
        }

        return ogrenciList;
    }
    
    public static OgrenciModel getOgrenciById(int ogrenciId) {
        OgrenciModel ogrenci = null;

        try (Connection conn = DatabaseUtil.Baglan();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM ogrenci WHERE ogrenciId = ?")) {

            statement.setInt(1, ogrenciId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int bolumId = resultSet.getInt("bolumId");
                String ogrNo = resultSet.getString("ogrNo");
                String adi = resultSet.getString("adi");
                String soyadi = resultSet.getString("soyadi");
                String tcNo = resultSet.getString("tcNo");
                String cinsiyet = resultSet.getString("cinsiyet");
                java.sql.Date dogumTarihi = resultSet.getDate("dogumTarihi");

                ogrenci = new OgrenciModel(ogrenciId, bolumId, ogrNo, adi, soyadi, tcNo, cinsiyet, dogumTarihi);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Öğrenci alınırken hata oluştu: " + e.getMessage());
        }

        return ogrenci;
    }
    
    public static String getOgrenciNoByOgrenciId(int ogrenciId) {
        String ogrNo = "";

        try {
        	Connection conn = DatabaseUtil.Baglan();

            String sql = "SELECT ogrNo FROM ogrenci WHERE ogrenciId = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, ogrenciId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                ogrNo = resultSet.getString("ogrNo");
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Öğrenci no alınırken hata oluştu: " + e.getMessage());
        }
        return ogrNo;
    }
}
