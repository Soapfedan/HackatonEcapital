package database;

import java.sql.SQLException;
import java.sql.Statement;

public class Presa {
	public static final String PLUG_ID = "PLUG_ID";
	public static final String AT_ID = "AT_ID";
	public static final String TABLE_NAME = "PRESA";
	
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
			statement.close();
		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} 
	}
	
	//Modifica dell'attuatore collegato
	public static void updateAttuatore(int id, int at) {
		Statement statement = null;

		String alterTableSQL = "UPDATE "+TABLE_NAME+" SET "
				+AT_ID+" = "+at
				+" WHERE "+PLUG_ID+" = "+id;

		try {
			
			statement = ConnectionHandler.getConn().createStatement();

			System.out.println(alterTableSQL);

			// execute insert SQL statement
			statement.executeUpdate(alterTableSQL);
			statement.close();
		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} 
	}
	
	//cancellazione
}
