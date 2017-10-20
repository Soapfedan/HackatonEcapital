package database;

import java.sql.SQLException;
import java.sql.Statement;

public class Utente {
	private static final String TABLE_NAME = "UTENTE";
	private static final String UTENTE = "USERNAME";
	private static final String NOME = "NOME";
	private static final String COGNOME = "COGNOME";
	private static final String PASSWORD = "PASSWORD";
	
	//Registrazione
	public static void insertUtente(String user, String password, String nome, String cognome){
		Statement statement = null;

		String insertTableSQL = "INSERT INTO "+TABLE_NAME
				+" VALUES('"
				+user+"','"+nome+"','"+cognome+"','"+password+"')";

		try {
			
			statement = ConnectionHandler.getConn().createStatement();

			System.out.println(insertTableSQL);

			// execute insert SQL statement
			statement.executeUpdate(insertTableSQL);

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} 
	}
	
	//login
	
	
}
