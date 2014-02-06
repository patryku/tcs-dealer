package sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SuspensionManager {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void addSuspension(String typ, String ildrzwi) throws SQLException{
		
		Connection conn = SQLUtils.getConnection();
		String query = "insert into nadwozia (typ, liczba_drzwi) values (\'" + 
		typ + "\', " + ildrzwi + ");";
		try (Statement st = conn.createStatement()) {
			st.executeUpdate(query);
		}catch(SQLException e){
			System.out.println(e);
			return;
		}
		
		System.out.println("Dodano zawieszenie do bazy: (typ: " + typ + ", il_drzwi: " + ildrzwi + ")");
	}

}
