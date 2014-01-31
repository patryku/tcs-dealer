package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLUtils {
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:postgresql://localhost:5432/dealer", "dude", "12345");
	}
	
	public static void main(String[] args) throws SQLException {
		Connection conn = SQLUtils.getConnection();
		String query = "SELECT * FROM modele;";
		try (Statement st = conn.createStatement()) {
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				System.out.println(rs.getString("producent") + " " + rs.getString("nazwa"));
			}
		}
	}
	
}
