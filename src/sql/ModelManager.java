package sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ModelManager {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void addModel(String prod, String model, String date, String warranty) throws SQLException{

		Connection conn = SQLUtils.getConnection();
		String query = "insert into modele (producent, nazwa, produkowany_od, gwarancja) values (\'" + 
		prod + "\', \'" + model + "\', \'" + date + "\', " + warranty + ");";
		try (Statement st = conn.createStatement()) {
			st.executeUpdate(query);
		}catch(SQLException e){
			System.out.println(e);
			return;
		}
		
		System.out.println("Dodano model do bazy: (producent: " + prod + ", nazwa: " + model + ", produkowany_od: " + date + ")");
	}

}
