package database;

import java.sql.SQLException;
import java.sql.Statement;

public class Categoria {
	private static final String CAT_ID = "CAT_ID";
	private static final String NOME = "NOME";
	private static final String PRIORITA = "PRIORITA";
	private static final String TABLE_NAME = "CATEGORIA";
	
	//Inserimento nella tabella categoria
	public static void insertCategoria(String nm, int pr){
		Statement statement = null;

		String insertTableSQL = "INSERT INTO "+TABLE_NAME
				+ "("+NOME+","+PRIORITA+") VALUES('"
				+nm+"',"+pr+")";

		try {
			
			statement = ConnectionHandler.getConn().createStatement();

			System.out.println(insertTableSQL);

			// execute insert SQL statement
			statement.executeUpdate(insertTableSQL);

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} 
	}
	//modifica categoria
	
	//cancellazione
}
