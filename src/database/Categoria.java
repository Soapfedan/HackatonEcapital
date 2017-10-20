package database;

import java.sql.SQLException;
import java.sql.Statement;

public class Categoria {
	public static final String CAT_ID = "CAT_ID";
	public static final String NOME = "NOME";
	public static final String PRIORITA = "PRIORITA";
	public static final String TABLE_NAME = "CATEGORIA";
	
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

	//Modifica priorita
	public static void updatePriorita(int id, int pr) {
		Statement statement = null;

		String alterTableSQL = "UPDATE "+TABLE_NAME+" SET "
				+PRIORITA+" = "+pr
				+" WHERE "+CAT_ID+" = "+id;

		try {
			
			statement = ConnectionHandler.getConn().createStatement();

			System.out.println(alterTableSQL);

			// execute insert SQL statement
			statement.executeUpdate(alterTableSQL);

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} 
	}
	
	//cancellazione
}
