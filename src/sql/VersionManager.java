package sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class VersionManager {

	private static String apos(String s){
		return "\'" + s + "\'";
	}
	
	public static void addVersion(String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, String s11) throws SQLException{
		String query = "insert into wersje_view values(DEFAULT, " +
		apos(s1) + ", " +
		apos(s2) + ", " +
		apos(s3) + ", " +
		apos(s4) + ", " +
		s5 + ", " +
		s6 + ", " +
		s7 + ", " +
		apos(s8) + ", " +
		s9 + ", " +
		apos(s10) + ", " +
		s11 + ");";
		Connection conn = SQLUtils.getConnection();
		try (Statement st = conn.createStatement()) {
			st.executeUpdate(query);
		}catch(SQLException e){
			System.out.println(e);
			return;
		}
		System.out.println("Dodano wersje samochodu " + s1 + " marki " + s2);
	}

}

