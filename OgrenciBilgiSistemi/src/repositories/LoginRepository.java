package repositories;

import com.MySQL.Util.DatabaseUtil;
import dialogs.MessageBox;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRepository {

    public static boolean checkCredentials(String kullaniciAdi, String sifre) {
        try {
            Connection conn = DatabaseUtil.Baglan();

            String sql = "SELECT COUNT(*) FROM login WHERE kullaniciAdi = ? AND sifre = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, kullaniciAdi);
            statement.setString(2, sifre);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            resultSet.close();
            statement.close();
            conn.close();

            return count > 0;
        } catch (SQLException e) {
        	MessageBox.showMessage("Hata", "Kimlik doğrulama hatası: " + e.getMessage(), "error");
            return false;
        }
    }
}

