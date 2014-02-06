package sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PostManager {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void addPost(String nazwa, String adres, String telefon, String email) throws SQLException{
		
		Connection conn = SQLUtils.getConnection();
		String query = "insert into placowki (nazwa, adres, telefon, mail) values (\'" + 
		nazwa + "\', \'" + adres + "\', \'" + telefon + "\', \'" + email + "\');";
		try (Statement st = conn.createStatement()) {
			st.executeUpdate(query);
		}catch(SQLException e){
			System.out.println(e);
			return;
		}
		
		System.out.println("Dodano placowke do bazy: (nazwa: " + nazwa + ", adres: " + adres + ", telefon: " + telefon + ", email: " + email + ")");
		
	}

}
