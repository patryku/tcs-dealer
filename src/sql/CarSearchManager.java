package sql;

import java.sql.Connection;
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
	
	
	public Vector<String> getVersions(Vector<String> vals, Vector<String> opts) {
		Vector<String> res = new Vector<>();
		
		StringBuilder sb = new StringBuilder("SELECT id FROM wersje_view WHERE ");
		for(String s : vals)
			sb.append(s + " AND ");
		sb.setLength(sb.length()-4);
		sb.append("INTERSECT SELECT id_wersji FROM wyposazenia GROUP BY id_wersji HAVING array_agg(nazwa) @> ARRAY[");
		for(String s : opts)
			sb.append("'" + s + "', ");
		if(opts.size() > 0)
			sb.setLength(sb.length()-2);
		sb.append("]::varchar[];");
		
		StringBuilder query = new StringBuilder("SELECT producent, model, wersja, typ_silnika, pojemnosc, " + 
				"moc, moment_obr, typ_nadwozia, liczba_drzwi, typ_lakieru, cena FROM wersje_view WHERE id in(");
		
		Connection conn = null;
		try {
			conn = SQLUtils.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return res;
		}
		
		try (Statement st = conn.createStatement();
				Statement st2 = conn.createStatement()) {
			ResultSet rs = st.executeQuery(sb.toString());
			while(rs.next())
				query.append(rs.getInt(1) + ", ");
			query.setLength(query.length()-2);
			query.append(");");
			rs = st2.executeQuery(query.toString());
			while(rs.next())
				res.add(rs.getString(1) + "," + rs.getString(2) + "," + rs.getString(3) + "," + rs.getString(4)
						+ "," + rs.getInt(5) + "," + rs.getInt(6) + "," + rs.getInt(7) + "," + rs.getString(8)
						+ "," + rs.getInt(9) + "," + rs.getString(10) + "," + rs.getDouble(11));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
}
