package com.MySQL.Util;

import java.sql.*;

import dialogs.MessageBox;

public class DatabaseUtil {
	static Connection conn = null;
	
	public static Connection Baglan() {
		try {
			conn=DriverManager.getConnection("jdbc:mysql://localhost/ogrencibilgisistemidb", "root", "");
			return conn;
		} catch (Exception e){
			MessageBox.showMessage("Hata", "Veritabanına bağlanılamadı: " + e.getMessage().toString(), "error");
			return null;
		}
	}
}
