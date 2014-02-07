package sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class RepairManager {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void addRepair(String vin, String post, String data, String koszt, String opis) throws SQLException{
		Connection conn = SQLUtils.getConnection();
		String query = "insert into naprawy (vin, placowka, data, koszt, opis) values (\'" + 
		vin + "\', " + post + ", \'" + data + "\', " + koszt + ", \'" + opis + "\');";
		System.out.println(query);
		try (Statement st = conn.createStatement()) {
			st.executeUpdate(query);
		}catch(SQLException e){
			System.out.println(e);
			return;
		}
		
		System.out.println("Dodano naprawe do bazy: (vin: " + vin + ", post: " + post + ", data: " + data + ", koszt: " + koszt + ", opis: " + opis +  ")");
		
	}
	

}
