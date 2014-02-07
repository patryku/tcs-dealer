package sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ConsoleManager {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void submit(String command) throws SQLException{
		Connection conn = SQLUtils.getConnection();
		try (Statement st = conn.createStatement()) {
			st.executeUpdate(command);
		}catch(SQLException e){
			System.out.println(e);
			return;
		}
	}

}
