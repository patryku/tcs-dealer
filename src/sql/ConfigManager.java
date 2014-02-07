package sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ConfigManager {

	private static String apos(String s){
		return "\'" + s + "\'";
	}
	
	public static void addConfig(String vin, String addon) throws SQLException{
		
		Connection conn = SQLUtils.getConnection();
		String query = "insert into konfiguracje (vin, nazwa) values (" + apos(vin) + ", " + apos(addon) + ");";
		try (Statement st = conn.createStatement()) {
			st.executeUpdate(query);
		}catch(SQLException e){
			System.out.println(e);
			return;
		}
	}

}
