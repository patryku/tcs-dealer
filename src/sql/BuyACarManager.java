package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BuyACarManager {

	private static String apos(String s){
		return "\'" + s + "\'";
	}
	
	public static void perform(String vin, String rej, String date, String price, String client) throws SQLException{
		Connection conn = SQLUtils.getConnection();
		try (Statement st = conn.createStatement()) {
			st.execute("begin;");
			String query1 = "select * from auta_na_sprzedaz where vin = " + apos(vin) + ";";
			//System.out.println("q1: " + query1);
			ResultSet rs = st.executeQuery(query1);
			String rok, przebieg, wersja, plac, id_kol;
			if(rs.next()){
				rok = rs.getString("rok_produkcji");
				przebieg = rs.getString("przebieg");
				wersja = rs.getString("wersja");
				plac = rs.getString("placowka");
				id_kol = rs.getString("id_koloru");
			}else{
				throw new Exception("Samochod z danym numerem windykacyjnym nie istnieje, CBS juz zostalo powiadomione.");
			}
			String query2 = "insert into auta_klientow values(" + apos(vin) + ", " + apos(rej) + ", " + apos(rok) + ", "
					+ przebieg + ", " + wersja + ", " + apos(date) + ", " + price + ", " + plac + ", " + client + ", " + id_kol + ");";
			//System.out.println("q2: " + query2);
			st.executeUpdate(query2);
			
			String query3 = "delete from auta_na_sprzedaz where vin = " + apos(vin) + ";" ;
			//System.out.println("query3: " + query3);
			st.executeUpdate(query3);
			st.execute("commit;");
		}catch(Exception e){
			System.out.println(e);
			return;
		}
	}

}
