package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JList;

import view.components.OptionDisplay;

public class ConfiguratorManager {
	
	public Vector<String> getModels() {
		Vector<String> res = new Vector<>();
		String query = "SELECT producent, nazwa FROM modele ORDER BY 1, 2;";
		try (Statement st = SQLUtils.getConnection().createStatement()) {
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				res.add(rs.getString("producent") + " " + rs.getString("nazwa"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public Vector<String> getBodies(String car) {
		Vector<String> res = new Vector<>();
		String query = "SELECT DISTINCT typ_nadwozia, liczba_drzwi " + 
						"FROM wersje_view WHERE producent = ? AND model = ?;";
		String producent = car.split(" ")[0];
		String model = car.split(" ")[1];
		try (PreparedStatement st = SQLUtils.getConnection().prepareStatement(query)) {
			st.setString(1, producent);
			st.setString(2, model);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				res.add(rs.getString(1) + " " + rs.getString(2) + "d");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public Vector<String> getVersions(String car, String body) {
		Vector<String> res = new Vector<>();
		String query = "SELECT wersja, min(cena) FROM wersje_view WHERE producent = ? AND model = ? " +
						"AND typ_nadwozia = ? AND liczba_drzwi = ? GROUP BY wersja ORDER BY min(cena);";
		String producent = car.split(" ")[0];
		String model = car.split(" ")[1];
		String typ_nadwozia = body.split(" ")[0];
		int liczba_drzwi = Integer.parseInt(body.split(" ")[1].substring(0, 1));
		try (PreparedStatement st = SQLUtils.getConnection().prepareStatement(query)) {
			st.setString(1, producent);
			st.setString(2, model);
			st.setString(3, typ_nadwozia);
			st.setInt(4, liczba_drzwi);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				res.add(rs.getString(1) + " od " + rs.getDouble(2) + " zł");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public Vector<String> getPaints(String car, String body, String version) {
		Vector<String> res = new Vector<>();
		String query = "SELECT DISTINCT typ_lakieru FROM wersje_view " +
						"WHERE producent = ? AND model = ? AND typ_nadwozia = ? AND liczba_drzwi = ? AND wersja = ?;";
		String query2 = "SELECT typ_lakieru, kolor FROM kolory_view WHERE typ_lakieru in(?);";
		String producent = car.split(" ")[0];
		String model = car.split(" ")[1];
		String typ_nadwozia = body.split(" ")[0];
		int liczba_drzwi = Integer.parseInt(body.split(" ")[1].substring(0, 1));
		
		Connection con = null;
		try {
			con = SQLUtils.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return res;
		}
		
		try (PreparedStatement st = con.prepareStatement(query);
				PreparedStatement st2 = con.prepareStatement(query2)) {
			st.setString(1, producent);
			st.setString(2, model);
			st.setString(3, typ_nadwozia);
			st.setInt(4, liczba_drzwi);
			st.setString(5, version);
			ResultSet rs = st.executeQuery();
			StringBuilder types = new StringBuilder();
			while(rs.next()) {
				types.append(rs.getString(1) + ", ");
			}
			st2.setString(1, types.substring(0, types.length()-2));
			rs = st2.executeQuery();
			while(rs.next()) {
				res.add(rs.getString(2) + " " + rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public Vector<String> getEngines(String car, String body, String version, String paint) {
		Vector<String> res = new Vector<>();
		String query = "SELECT DISTINCT typ_silnika, pojemnosc, moc, moment_obr, cena FROM wersje_view " +
						"WHERE producent = ? AND model = ? AND typ_nadwozia = ? " + 
						"AND liczba_drzwi = ? AND wersja = ? AND typ_lakieru = ? " +
						"ORDER BY 1, 2, 3, 4, 5;";
		String producent = car.split(" ")[0];
		String model = car.split(" ")[1];
		String typ_nadwozia = body.split(" ")[0];
		int liczba_drzwi = Integer.parseInt(body.split(" ")[1].substring(0, 1));
		try (PreparedStatement st = SQLUtils.getConnection().prepareStatement(query)) {
			st.setString(1, producent);
			st.setString(2, model);
			st.setString(3, typ_nadwozia);
			st.setInt(4, liczba_drzwi);
			st.setString(5, version);
			st.setString(6, paint);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				res.add(rs.getString(1) + ", " + rs.getInt(2) + " ccm, " + rs.getInt(3) + " KM, " + 
						rs.getInt(4) + " Nm, " + rs.getDouble(5) + " zł");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public Vector<OptionDisplay> getOptions(JLabel lblPrice, JList<String> optionList, 
			String car, String body, String version, String paint, String[] engine) {
		
		Vector<OptionDisplay> res = new Vector<>();
		
		String modelQuery = "SELECT id_modelu FROM modele WHERE producent = ? AND nazwa = ?;";
		String bodyQuery = "SELECT id_nadwozia FROM nadwozia WHERE typ = ? AND liczba_drzwi = ?;";
		String paintQuery = "SELECT id_lakieru FROM lakiery WHERE typ = ?;";
		String engineQuery = "SELECT id_silnika FROM silniki WHERE typ = ? AND pojemnosc = ? AND moc = ? AND moment = ?;";
		String versionQuery = "SELECT id_wersji FROM wersje WHERE id_modelu = ? AND id_silnika = ? " + 
								"AND id_nadwozia = ? AND id_lakieru = ? AND nazwa_wersji = ?;";
		String optionQuery = "SELECT nazwa, cena FROM wyposazenia WHERE id_wersji = ? AND cena > 0;";
		
		String producent = car.split(" ")[0];
		String model = car.split(" ")[1];
		String typ_nadwozia = body.split(" ")[0];
		int liczba_drzwi = Integer.parseInt(body.split(" ")[1].substring(0, 1));
		String lakier = paint.split(" ")[1];
		
		Connection con = null;
		try {
			con = SQLUtils.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return res;
		}
		
		try (PreparedStatement modelSt = con.prepareStatement(modelQuery);
				PreparedStatement bodySt = con.prepareStatement(bodyQuery);
				PreparedStatement paintSt = con.prepareStatement(paintQuery);
				PreparedStatement engineSt = con.prepareStatement(engineQuery);
				PreparedStatement versionSt = con.prepareStatement(versionQuery);
				PreparedStatement optionSt = con.prepareStatement(optionQuery)) {
			
			modelSt.setString(1, producent);
			modelSt.setString(2, model);
			ResultSet rs = modelSt.executeQuery();
			rs.next();
			int id_modelu = rs.getInt(1);
			
			bodySt.setString(1, typ_nadwozia);
			bodySt.setInt(2, liczba_drzwi);
			rs = bodySt.executeQuery();
			rs.next();
			int id_nadwozia = rs.getInt(1);
			
			paintSt.setString(1, lakier);
			rs = paintSt.executeQuery();
			rs.next();
			int id_lakieru = rs.getInt(1);
			
			engineSt.setString(1, engine[0]);
			engineSt.setInt(2, Integer.parseInt(engine[1].split(" ")[0]));
			engineSt.setInt(3, Integer.parseInt(engine[2].split(" ")[0]));
			engineSt.setInt(4, Integer.parseInt(engine[3].split(" ")[0]));
			rs = engineSt.executeQuery();
			rs.next();
			int id_silnika = rs.getInt(1);
			
			versionSt.setInt(1, id_modelu);
			versionSt.setInt(2, id_silnika);
			versionSt.setInt(3, id_nadwozia);
			versionSt.setInt(4, id_lakieru);
			versionSt.setString(5, version);
			rs = versionSt.executeQuery();
			rs.next();
			int id_wersji = rs.getInt(1);
			
			optionSt.setInt(1, id_wersji);
			rs = optionSt.executeQuery();
			while(rs.next()) {
				res.add(new OptionDisplay(rs.getString(1), rs.getDouble(2), lblPrice, optionList));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
}
