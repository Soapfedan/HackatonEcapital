package database;

import java.sql.SQLException;
import java.sql.Statement;

public class Attuatore {

	private static final String TABLE_NAME = "ATTUATORE";
	private static final String AT_ID = "AT_ID"; 
	private static final String NOME = "NOME";
	private static final String DESCRIZIONE = "DESCRIZIONE";
	private static final String CAT_ID = "CAT_ID";
	private static final String PRIORITA = "PRIORITA";
	
	//Inserimento dell'attuatore
	public static void insertAttuatore(String nm, String desc, int cat, int pr){
		Statement statement = null;

		String insertTableSQL = "INSERT INTO "+TABLE_NAME
				+ "("+NOME+","+DESCRIZIONE+","+CAT_ID+","+PRIORITA+") VALUES('"
				+nm+"','"+desc+"',"+cat+","+pr+")";

		try {
			
			statement = ConnectionHandler.getConn().createStatement();

			System.out.println(insertTableSQL);

			// execute insert SQL statement
			statement.executeUpdate(insertTableSQL);

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} 
	}
	
	//modifica
	
	//cancellazione
}
