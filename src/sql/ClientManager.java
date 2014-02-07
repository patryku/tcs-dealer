package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientManager {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void addClient(String nazwa, String adres, String telefon, String email) throws SQLException{
		
		Connection conn = SQLUtils.getConnection();
		String query = "insert into klienci (nazwa, adres, telefon, mail) values (\'" + 
		nazwa + "\', \'" + adres + "\', \'" + telefon + "\', \'" + email + "\');";
		try (Statement st = conn.createStatement()) {
			st.executeUpdate(query);
		}catch(SQLException e){
			System.out.println(e);
			return;
		}
		
		System.out.println("Dodano klienta do bazy: (nazwa: " + nazwa + ", adres: " + adres + ", telefon: " + telefon + ", email: " + email + ")");
		
	}
	
	
	public static ResultSet getClients() throws SQLException{
		Connection conn = SQLUtils.getConnection();
		String query = "SELECT * FROM klienci;";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		System.out.println("GOT QUERY");
		return rs;
	}

}
