package database;

import java.sql.SQLException;
import java.sql.Statement;

public class Presa {
	private static final String PLUG_ID = "PLUG_ID";
	private static final String AT_ID = "AT_ID";
	private static final String TABLE_NAME = "PRESA";
	//inserimento
	public static void insertCategoria(int at){
		Statement statement = null;

		String insertTableSQL = "INSERT INTO "+TABLE_NAME
				+ "("+AT_ID+") VALUES("
				+at+")";

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
