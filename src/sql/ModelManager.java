package sql;

import java.util.Arrays;

public class ModelManager {

	/**
	 * @param args
	 */
	public static void addModel(String prod, String model, String date){
		try{
			
			String[] ss = date.split("-");
			System.out.println(Arrays.asList(ss));
			if(ss.length != 3 || Integer.parseInt(ss[0]) < 1900 || Integer.parseInt(ss[1]) > 12 || Integer.parseInt(ss[2])>31){
				System.out.println("Nieprawidloowe dane modelu");
				return;
			}
			
		}catch(Exception e){
			System.out.println("Nieprawdilowe dane modelu");
			return;
		}
		System.out.println("Dodano model do bazy: (producent: " + prod + ", model: " + model + ", produkowany_od: " + date + ")");
		//TODO: dodaj model do bazy
	}

}
