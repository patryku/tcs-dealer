package sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import view.components.OptionDisplay;

public class CarSearchManager {
	
	public Vector<String> getEngineTypes() {
		Vector<String> res = new Vector<>();
		String query = "SELECT DISTINCT typ FROM silniki;";
		try (Statement st = SQLUtils.getConnection().createStatement()) {
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				res.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public Vector<String> getBodyTypes() {
		Vector<String> res = new Vector<>();
		String query = "SELECT DISTINCT typ FROM nadwozia;";
		try (Statement st = SQLUtils.getConnection().createStatement()) {
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				res.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public Vector<OptionDisplay> getOptions() {
		Vector<OptionDisplay> res = new Vector<>();
		String query = "SELECT DISTINCT nazwa FROM wyposazenia ORDER BY 1;";
		try (Statement st = SQLUtils.getConnection().createStatement()) {
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				res.add(new OptionDisplay(rs.getString(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
}
