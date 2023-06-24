package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.MySQL.Util.DatabaseUtil;
import dialogs.MessageBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.OgretimUyesiModel;

public class OgretimUyesiRepository {
	
    public static void insertOgretimUyesi(OgretimUyesiModel ogretimUyesi) {
        try {
            Connection conn = DatabaseUtil.Baglan();

            String sql = "INSERT INTO ogretim_uyesi (bolumId, dersId, ogrtUyeNo, adi, soyadi, dogumTarihi, cinsiyet, tcNo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, ogretimUyesi.getBolumId());
            statement.setInt(2, ogretimUyesi.getDersId());
            statement.setString(3, ogretimUyesi.getOgrtUyeNo());
            statement.setString(4, ogretimUyesi.getAdi());
            statement.setString(5, ogretimUyesi.getSoyadi());
            statement.setObject(6, ogretimUyesi.getDogumTarihi());
            statement.setString(7, ogretimUyesi.getCinsiyet());
            statement.setString(8, ogretimUyesi.getTcNo());

            statement.executeUpdate();
            statement.close();
            conn.close();

            MessageBox.showMessage("Öğrenci Bilgi Sistemi", "Öğretim Üyesi başarıyla eklendi.");
        } catch (SQLException e) {
        	MessageBox.showMessage("Hata", "Öğretim Üyesi eklenirken hata oluştu: " + e.getMessage(), "error");
        }
    }

    public static void updateOgretimUyesi(OgretimUyesiModel ogretimUyesi) {
        try {
            Connection conn = DatabaseUtil.Baglan();

            String sql = "UPDATE ogretim_uyesi SET bolumId = ?, dersId = ?, ogrtUyeNo = ?, adi = ?, soyadi = ?, dogumTarihi = ?, cinsiyet = ?, tcNo = ? WHERE ogretimUyesiId = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, ogretimUyesi.getBolumId());
            statement.setInt(2, ogretimUyesi.getDersId());
            statement.setString(3, ogretimUyesi.getOgrtUyeNo());
            statement.setString(4, ogretimUyesi.getAdi());
            statement.setString(5, ogretimUyesi.getSoyadi());
            statement.setObject(6, ogretimUyesi.getDogumTarihi());
            statement.setString(7, ogretimUyesi.getCinsiyet());
            statement.setString(8, ogretimUyesi.getTcNo());
            statement.setInt(9, ogretimUyesi.getOgretimUyesiId());

            statement.executeUpdate();
            statement.close();
            conn.close();

            MessageBox.showMessage("Öğrenci Bilgi Sistemi", "Öğretim Üyesi başarıyla güncellendi.");
        } catch (SQLException e) {
        	MessageBox.showMessage("Hata", "Öğretim Üyesi güncellenirken hata oluştu: " + e.getMessage(), "error");
        }
    }
    
    public static void deleteOgretimUyesi(OgretimUyesiModel ogretimUyesi) {
        try {
            Connection conn = DatabaseUtil.Baglan();

            String sql = "DELETE FROM ogretim_uyesi WHERE ogretimUyesiId = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, ogretimUyesi.getOgretimUyesiId());

            statement.executeUpdate();
            statement.close();
            conn.close();

            MessageBox.showMessage("Öğrenci Bilgi Sistemi", "Öğretim Üyesi başarıyla silindi.");
        } catch (SQLException e) {
        	MessageBox.showMessage("Hata", "Öğretim Üyesi silinirken hata oluştu: " + e.getMessage(), "error");
        }
    }

    public static ObservableList<OgretimUyesiModel> getAllOgretimUyeleri() {
        ObservableList<OgretimUyesiModel> ogretimUyesiList = FXCollections.observableArrayList();
        try {
            Connection conn = DatabaseUtil.Baglan();

            String sql = "SELECT * FROM ogretim_uyesi";

            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int ogretimUyesiId = resultSet.getInt("ogretimUyesiId");
                int bolumId = resultSet.getInt("bolumId");
                int dersId = resultSet.getInt("dersId");
                String ogrtUyeNo = resultSet.getString("ogrtUyeNo");
                String adi = resultSet.getString("adi");
                String soyadi = resultSet.getString("soyadi");
                java.sql.Date dogumTarihi = resultSet.getDate("dogumTarihi");
                String cinsiyet = resultSet.getString("cinsiyet");
                String tcNo = resultSet.getString("tcNo");

                OgretimUyesiModel ogretimUyesi = new OgretimUyesiModel(ogretimUyesiId, bolumId, dersId, ogrtUyeNo, adi, soyadi, dogumTarihi, cinsiyet, tcNo);
                ogretimUyesiList.add(ogretimUyesi);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
        	MessageBox.showMessage("Hata", "Öğretim Üyeleri alınırken hata oluştu: " + e.getMessage(), "error");
        }
        return ogretimUyesiList;
    }
}
