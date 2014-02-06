package sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EngineManager {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void addEngine(String typ, String pojemnosc, String moc, String moment) throws SQLException{
		
		Connection conn = SQLUtils.getConnection();
		String query = "insert into silniki (typ, pojemnosc, moc, moment) values (\'" + 
		typ + "\', " + pojemnosc + ", " + moc + ", " + moment + ");";
		try (Statement st = conn.createStatement()) {
			st.executeUpdate(query);
		}catch(SQLException e){
			System.out.println(e);
			return;
		}
		
		System.out.println("Dodano silnik do bazy: (typ: " + typ + ", pojemnosc: " + pojemnosc + ", moc: " + moc + ", moment: " + moment + ")");
	}

}
