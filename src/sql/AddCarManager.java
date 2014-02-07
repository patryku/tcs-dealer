package sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AddCarManager {

	
	private static String apos(String s){
		return "\'" + s + "\'";
	}
	
	public static void addCar(String vin, String date, String przebieg, String ver, String col, String post) throws SQLException{
		Connection conn = SQLUtils.getConnection();
		String query = "insert into auta_na_sprzedaz values (" + apos(vin) + ", " +
		apos(date) + ", " + przebieg + ", " + ver + ", " + post + ", " + col + ");";
		try (Statement st = conn.createStatement()) {
			st.executeUpdate(query);
		}catch(SQLException e){
			System.out.println(e);
			return;
		}
		
		System.out.println("Dodano samochod do bazy: (vin: " + vin + ", placowka: " + post + ")");
	}

}
