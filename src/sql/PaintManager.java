package sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PaintManager {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void addPaint(String typ, String kolor) throws SQLException{
		
		Connection conn = SQLUtils.getConnection();
		String query = "insert into kolory_view (typ_lakieru, kolor) values (\'" + 
		typ + "\', \'" + kolor + "\');";
		try (Statement st = conn.createStatement()) {
			st.executeUpdate(query);
		}catch(SQLException e){
			System.out.println(e);
			return;
		}
		
		System.out.println("Dodano lakier do bazy: (typ: " + typ + ", kolor: " + kolor + ")");
		
	}

}
