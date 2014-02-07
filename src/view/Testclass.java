package view;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sql.SQLUtils;

public class Testclass {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		
		Connection conn = SQLUtils.getConnection();
		try (Statement st = conn.createStatement()) {
			st.execute("begin;");
			st.executeUpdate("insert into kolory_view values(\'aaa\', \'bbb\' );");
			ResultSet rs = st.executeQuery("select * from kolory_view;");
			while(rs.next()) {
				System.out.println(rs.getString("typ_lakieru") + " " + rs.getString("kolor"));
			}
			try {
			    Thread.sleep(10000);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			st.execute("rollback;");
		}catch(SQLException e){
			System.out.println(e);
			return;
		}

	}

}
