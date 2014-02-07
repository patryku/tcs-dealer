package sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class WyposManager {

	private static String apos(String s){
		return "\'" + s + "\'";
	}
	
	public static void addWypos(String wersja, String addon, String cost) throws SQLException{
		
		Connection conn = SQLUtils.getConnection();
		String query = "insert into wyposazenia (id_wersji, nazwa, cena) values (" + wersja + ", " + apos(addon) + ", " + cost + ");";
		try (Statement st = conn.createStatement()) {
			st.executeUpdate(query);
		}catch(SQLException e){
			System.out.println(e);
			return;
		}
	}

}
