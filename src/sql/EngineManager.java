package sql;

public class EngineManager {

	/**
	 * @param args
	 */
	public static void addEngine(String typ, String pojemnosc, String moc, String moment){
		try{
			if(Integer.parseInt(pojemnosc) <= 0 || Integer.parseInt(moc) <= 0 || Integer.parseInt(moment) <= 0){
				System.out.println("Nieprawidlowe dane silnika");
				return;
			}
		}catch(NumberFormatException nfe){
			System.out.println("Nieprawidlowe dane silnika");
			return;
		}
		System.out.println("Dodano silnik do bazy: (typ: " + typ + ", pojemnosc: " + pojemnosc + ", moc: " + moc + ", moment: " + moment + ")");
		//TODO: dodaj silnik do bazy
	}

}
