package sql;

public class PostManager {

	/**
	 * @param args
	 */
	public static void addPost(String nazwa, String adres, String telefon, String email){
		
		try{
			String[] ss = email.split("@");
			if(ss.length != 2)
				throw new Exception();
		}catch(Exception e){
			System.out.println("Nieprawidlowe dane placowki");
			return;
		}
		
		System.out.println("Dodano placowke do bazy: (nazwa: " + nazwa + ", adres: " + adres + ", telefon: " + telefon + ", email: " + email + ")");
		//TODO: dodaj placowke do bazy
	}

}
